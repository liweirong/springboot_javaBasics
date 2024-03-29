package com.iris.network.estateLock;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import javax.crypto.Cipher;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.apache.http.client.ClientProtocolException;
import org.springframework.util.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import sun.misc.BASE64Encoder;

/**
 * 依赖的jar包有：commons-lang-2.6.jar、httpclient-4.3.2.jar、httpcore-4.3.1.jar、commons-io-2.4.jar
 *
 * @author iris
 * @date 2021/4/20
 */
public class HttpClientUtils {

    private static final int connTimeout = 10000;
    private static final int readTimeout = 10000;
    private static final String charset = "UTF-8";
    private static HttpClient client;

    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(128);
        cm.setDefaultMaxPerRoute(128);
        client = HttpClients.custom().setConnectionManager(cm).build();
    }

    public static JSONObject postParameters(String url, String parameterStr) throws Exception {
        return post(url, parameterStr, "application/x-www-form-urlencoded", charset, connTimeout, readTimeout);
    }

    public static JSONObject postParameters(String url, String parameterStr, String charset, Integer connTimeout, Integer readTimeout) throws ConnectTimeoutException, SocketTimeoutException, Exception {
        return post(url, parameterStr, "application/x-www-form-urlencoded", charset, connTimeout, readTimeout);
    }

    public static String postParameters(String url, Map<String, String> params) throws
            Exception {
        return postForm(url, params, null, connTimeout, readTimeout);
    }

    public static String postParameters(String url, Map<String, String> params, Integer connTimeout, Integer readTimeout) throws ConnectTimeoutException,
            Exception {
        return postForm(url, params, null, connTimeout, readTimeout);
    }

    public static String get(String url) throws Exception {
        return get(url, charset, null, null);
    }

    public static String get(String url, String charset) throws Exception {
        return get(url, charset, connTimeout, readTimeout);
    }

    /**
     * 发送一个 Post 请求, 使用指定的字符集编码.
     *
     * @param url
     * @param body        RequestBody
     * @param mimeType    例如 application/xml "application/x-www-form-urlencoded" a=1&b=2&c=3
     * @param charset     编码
     * @param connTimeout 建立链接超时时间,毫秒.
     * @param readTimeout 响应超时时间,毫秒.
     * @return ResponseBody, 使用指定的字符集编码.
     * @throws Exception
     */
    public static JSONObject post(String url, String body, String mimeType,
                                  String charset, Integer connTimeout, Integer readTimeout) {
        HttpClient client = null;
        HttpPost post = new HttpPost(url);
        String result = null;
        try {
            if (StringUtils.isNotBlank(body)) {
                HttpEntity entity = new StringEntity(body, ContentType.create(mimeType, charset));
                post.setEntity(entity);
            }
            // 设置参数
            Builder customReqConf = RequestConfig.custom();
            if (connTimeout != null) {
                customReqConf.setConnectTimeout(connTimeout);
            }
            if (readTimeout != null) {
                customReqConf.setSocketTimeout(readTimeout);
            }
            post.setConfig(customReqConf.build());
            post.setHeader("t", System.currentTimeMillis() + "");
            post.setHeader("key", masterKey);
            post.setHeader("sign", DigestUtils.md5DigestAsHex((appid + secret).getBytes()));
            post.setHeader("Content-Type", "application/x-www-form-urlencoded");

            HttpResponse res;
            if (url.startsWith("https")) {
                // 执行 Https 请求.
                client = createSSLInsecureClient();
                res = client.execute(post);
            } else {
                // 执行 Http 请求.
                client = HttpClientUtils.client;
                res = client.execute(post);
            }

            System.out.println("调用:" + post.getURI());
            result = IOUtils.toString(res.getEntity().getContent(), charset);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            post.releaseConnection();
            if (url.startsWith("https") && client instanceof CloseableHttpClient) {
                try {
                    ((CloseableHttpClient) client).close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return JSON.parseObject(result);
    }


    /**
     * 提交form表单
     *
     * @param url
     * @param params
     * @param connTimeout
     * @param readTimeout
     * @return
     * @throws ConnectTimeoutException
     * @throws SocketTimeoutException
     * @throws Exception
     */
    public static String postForm(String url, Map<String, String> params, Map<String, String> headers, Integer connTimeout, Integer readTimeout) throws ConnectTimeoutException,
            SocketTimeoutException, Exception {

        HttpClient client = null;
        HttpPost post = new HttpPost(url);
        try {
            if (params != null && !params.isEmpty()) {
                List<NameValuePair> formParams = new ArrayList<org.apache.http.NameValuePair>();
                Set<Entry<String, String>> entrySet = params.entrySet();
                for (Entry<String, String> entry : entrySet) {
                    formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
                post.setEntity(entity);
            }

            if (headers != null && !headers.isEmpty()) {
                for (Entry<String, String> entry : headers.entrySet()) {
                    post.addHeader(entry.getKey(), entry.getValue());
                }
            }
            // 设置参数
            Builder customReqConf = RequestConfig.custom();
            if (connTimeout != null) {
                customReqConf.setConnectTimeout(connTimeout);
            }
            if (readTimeout != null) {
                customReqConf.setSocketTimeout(readTimeout);
            }
            post.setConfig(customReqConf.build());
            HttpResponse res;
            if (url.startsWith("https")) {
                // 执行 Https 请求.
                client = createSSLInsecureClient();
                res = client.execute(post);
            } else {
                // 执行 Http 请求.
                client = HttpClientUtils.client;
                res = client.execute(post);
            }
            return IOUtils.toString(res.getEntity().getContent(), "UTF-8");
        } finally {
            post.releaseConnection();
            if (url.startsWith("https") && client instanceof CloseableHttpClient) {
                ((CloseableHttpClient) client).close();
            }
        }
    }


    /**
     * 发送一个 GET 请求
     *
     * @param url
     * @param charset
     * @param connTimeout 建立链接超时时间,毫秒.
     * @param readTimeout 响应超时时间,毫秒.
     * @return
     * @throws ConnectTimeoutException 建立链接超时
     * @throws SocketTimeoutException  响应超时
     * @throws Exception
     */
    public static String get(String url, String charset, Integer connTimeout, Integer readTimeout)
            throws ConnectTimeoutException, SocketTimeoutException, Exception {
        HttpClient client = null;
        HttpGet get = new HttpGet(url);
        String result;
        try {
            // 设置参数
            Builder customReqConf = RequestConfig.custom();
            if (connTimeout != null) {
                customReqConf.setConnectTimeout(connTimeout);
            }
            if (readTimeout != null) {
                customReqConf.setSocketTimeout(readTimeout);
            }
            get.setConfig(customReqConf.build());
            HttpResponse res;
            if (url.startsWith("https")) {
                // 执行 Https 请求.
                client = createSSLInsecureClient();
                res = client.execute(get);
            } else {
                // 执行 Http 请求.
                client = HttpClientUtils.client;
                res = client.execute(get);
            }

            result = IOUtils.toString(res.getEntity().getContent(), charset);
        } finally {
            get.releaseConnection();
            if (url.startsWith("https") && client instanceof CloseableHttpClient) {
                ((CloseableHttpClient) client).close();
            }
        }
        return result;
    }


    /**
     * 从 response 里获取 charset
     *
     * @param response
     * @return
     */
    @SuppressWarnings("unused")
    private static String getCharsetFromResponse(HttpResponse response) {
        // Content-Type:text/html; charset=GBK
        if (response.getEntity() != null && response.getEntity().getContentType() != null && response.getEntity().getContentType().getValue() != null) {
            String contentType = response.getEntity().getContentType().getValue();
            if (contentType.contains("charset=")) {
                return contentType.substring(contentType.indexOf("charset=") + 8);
            }
        }
        return null;
    }


    /**
     * 创建 SSL连接
     *
     * @return
     * @throws GeneralSecurityException
     */
    private static CloseableHttpClient createSSLInsecureClient() throws GeneralSecurityException {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {

                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }

                @Override
                public void verify(String host, SSLSocket ssl) {
                }

                @Override
                public void verify(String host, X509Certificate cert) {
                }

                @Override
                public void verify(String host, String[] cns, String[] subjectAlts) {
                }

            });

            return HttpClients.custom().setSSLSocketFactory(sslsf).build();

        } catch (GeneralSecurityException e) {
            throw e;
        }
    }

    /**
     * 生产环境账号
     */
    public static String appid = "a22704a7c855471eb782b8ee3bacebc7";
    private static String secret = "9ba1465075e34cdb80d945f5d6f5b38a";
    private static String masterKey = "77a34f924ad54453a3d34c5a14b3c9fa";
    private static String categoryId = "998c6c1be5374bb18b8a967e90b5af52";

    // 07号楼	3层	302
    private static String deviceUUID = "c44e01518194bc65a7385b0a9f08998f";
    private static String lockUserId = "c44e01518194bc65a7385b0a9f08998f";
    private static String mac = "04:78:63:D7:1F:A1".replaceAll(":", "");
//    private static String mac2 = "B0:F8:93:70:E0:92".replaceAll(":", "");
    // 1602室 B0F89370F237
    // 7栋1601室。B0F89370FA6A
    /**
     * appid: 9262b5c5381b425ab50023d1db1e8f13
     * secret: 0dfdf065d17844f6b51720385c7c8167
     * master_key: 4e95530d9f144690af778cb08b7736d2
     * categoryId: b215fa53021f427b9ce78303438e53c4
     *
     * @param args
     */
//    private static String testAppid = "9262b5c5381b425ab50023d1db1e8f13";
//    private static String testSecret = "0dfdf065d17844f6b51720385c7c8167";
//    private static String testMasterKey = "4e95530d9f144690af778cb08b7736d2";
//    private static String testCategoryId = "b215fa53021f427b9ce78303438e53c4";
//    private static String testDeviceUUid = "427444ca51ea5cc9222fa04aa28a2b00";
//    private static String testDeviceUUid1 = "427444ca51ea5cc9";
    // 测试环境域名
//    private static String testDomain = "https://test2.fcsmartlock.com:3003";

// 正式环境域名：
    private static String shengchaDomain = "https://open.fcsmartlock.com";
    private static final String modifyUrl = "/open/api/wifi_device/modifyPassword/v1";
    // 删除门锁用户
    private static final String delLockUser = "/open/api/wifi_device/delLockUser/v1";
    private static final String GET_LOCK_USER_LIST = "/open/api/device/lockUsers/getLockUserList/v1";
    private static Boolean re = true;

    static Map<String, String> map = Maps.newHashMap();

    static {
//        tempPassword/ lockUserId
        map.put("AB2AA0BB3BFF159D0820AE1B5154A831", "0d2d9540dd244bd69d9c86bf489ebfac");
    }

    public static void main(String[] args) throws Exception {
//        String pass = "1014BF8DBD632A33A3617309AA24A273";
        map.forEach((temp, locksUserId) -> {
            System.out.println("locksUserId" + locksUserId + " : " + AesUtils.decrypt(temp, deviceUUID));
        });

//        System.out.println("sign:" + DigestUtils.md5DigestAsHex((appid + secret).getBytes()));

        String GET_LOCK_USER_LIS = shengchaDomain + GET_LOCK_USER_LIST + "?appId=" + appid + "&deviceUUID=" + deviceUUID + "&userType=2&rows=100";
        JSONObject userList = post(GET_LOCK_USER_LIS, null, "application/x-www-form-urlencoded", charset, 10000, 10000);
        // https://open.fcsmartlock.com/open/api/device/lockUsers/getLockUserList/v1?appId=a22704a7c855471eb782b8ee3bacebc7&deviceUUID=c44e01518194bc65a7385b0a9f08998f&userType=2&rows=100
        System.out.println(GET_LOCK_USER_LIS + "\n userList:------" + userList);
        if (re) {
            return;
        }

        String addDeviceParam = "/appId=" + appid + "&deviceUUID=" + deviceUUID + "&id=" + mac;
//        String testAddDeviceParam = "appId=" + testAppid + "&categoryId=" + testCategoryId + "&mac=" + mac;
        String deleteLockUser = shengchaDomain + delLockUser + "?" + addDeviceParam;
        JSONObject str = post(deleteLockUser, null, "application/x-www-form-urlencoded", charset, 10000, 10000);
//            String str2 = post(testDomain + addDevice, testAddDeviceParam, "application/x-www-form-urlencoded", charset, 10000, 10000);
        System.out.println(str);
//            System.out.println(str2);
    }

    //data为要加密的数据，secret为加密的密码（解密时也需要用到该密码）。
    public static String encryptAES(String data, String secret) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        if (secret == null) {
            System.out.print("Key为空null");
            return null;
        }

        byte[] raw = secret.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(data.getBytes("utf-8"));
        return new BASE64Encoder().encode(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }

}
