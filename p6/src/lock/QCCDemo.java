//package lock;
//
//import com.alibaba.fastjson.JSON;
//import org.apache.commons.codec.binary.Base64;
//import org.apache.commons.lang3.StringUtils;
//
//import javax.crypto.Cipher;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
//import java.security.Key;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author iris
// * @date 2022/6/17
// */
//public class QCCDemo {
//    public static void main(String[] args) throws Exception {
//        String token = generateToken("15913153139", "admin", null, null);
//        System.out.println("token:" + token);
//    }
//
//    public static String generateToken(String loginName, String role, String name, String email) throws Exception {
//        if (StringUtils.isBlank(loginName)) {
//            return "用户名或ID不能为空";
//        }
//        Map<String, String> result = new HashMap<String, String>();
//        result.put("loginName", loginName);
//        if (StringUtils.isBlank(name)) {
//            result.put("name", loginName);
//        } else {
//            result.put("name", name);
//        }
//        if (StringUtils.isNotBlank(role)) {
//            if (!("admin".equals(role) || "staff".equals(role))) {
//                return "角色不正确";
//            }
//        } else {
//            role = "staff";
//        }
//        result.put("role", role);
//        result.put("email", email == null ? "" : email);
//        result.put("timespan", String.valueOf(System.currentTimeMillis() / 1000));
//
//        String content = JSON.toJSONString(result);
//        return encrypt(content, "jIMSui6A6zsTu2m00qHGEQ==", "67bmxhc1w2flhaob");
//    }
//
//    /**
//     * 加密 * admin: 15913153139
//     * 	 * companyKey: 7027b483ed6211ec990b0c42a106ce72
//     * 	 * aesKey: jIMSui6A6zsTu2m00qHGEQ==
//     * 	 * aesIv: 67bmxhc1w2flhaob
//     */
//    public static String encrypt(String content, String secretKey, String ivParameterSpec) throws Exception {
//        if (content == null) {
//            content = "";
//        }
//        byte[] secretKeyB = Base64.decodeBase64(secretKey);
//        Key key = new SecretKeySpec(secretKeyB, ALGORITHM);
//        Cipher cipher = Cipher.getInstance(IV_ALGORITHM);// 创建密码器
//        IvParameterSpec iv = new IvParameterSpec(ivParameterSpec.getBytes());
//        cipher.init(Cipher.ENCRYPT_MODE, key, iv);// 初始化
//        byte[] result = cipher.doFinal(content.getBytes(DEFAULT_CHARSET));
//        return Base64.encodeBase64String(result);
//    }
//
//}
