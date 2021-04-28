package com.iris.network;

import org.bouncycastle.util.encoders.Base64;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @Author: liujun
 * @Description: Aes 加解密算法
 * @Date Create in 上午 9:38$ 2017/12/26 0026$
 * @Modify By:
 */
public class AesUtils {
    /**
     * @Author liujun
     * @Description:
     * @params: * @param content 需要加密的内容
     * @param password  加密密码
     * @Date 上午 9:41 2017/12/26 0026
     */

    public static String encrypt(String content, String password) {
        if(password.length()<16) {
            password = password + "0000000000000000".substring(0, 16-password.length());
        }
        else if(password.length()>16) {
            password = password.substring(0, 16);
        }

        return bytes2HexString(encryptAES(content.getBytes(), password.getBytes()));
    }


    /**
     * @Author liujun
     * @Description:
     * @params: * @param content 待解密内容
     * @param password 解密密钥
     * @Date 上午 9:40 2017/12/26 0026
     */
    public static String decrypt(String content, String password) {
        System.out.println(password.length());
        if(password.length()<16) {
            password = password + "0000000000000000".substring(0, 16-password.length());
        }
        else if(password.length()>16) {
            password = password.substring(0, 16);
        }

        return new String(decryptAES(hexString2Bytes(content), password.getBytes()));
    }



    ///////////////////////////////////////////////////////////////////////////
    // AES 加密相关
    ///////////////////////////////////////////////////////////////////////////


    /**
     * AES 加密
     *
     * @param data 明文
     * @param key  16、24、32 字节秘钥
     * @return 密文
     */
    public static byte[] encryptAES(final byte[] data,
                                    final byte[] key) {

        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = data;
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return result; // 加密
//        } catch (NoSuchAlgorithmException e) {
//            log.error(e);
//        } catch (NoSuchPaddingException e) {
//            log.error(e);
//        } catch (InvalidKeyException e) {
//            log.error(e);
//        } catch (IllegalBlockSizeException e) {
//            log.error(e);
//        } catch (BadPaddingException e) {
//            log.error(e);
        } catch (Exception e) {
//            log.error(e);
        }
        return null;
    }

    /**
     * AES 解密
     *
     * @param data 密文
     * @param key  16、24、32 字节秘钥
     * @return 明文
     */
    public static byte[] decryptAES(final byte[] data,
                                    final byte[] key) {

        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);// 初始化
            byte[] result = cipher.doFinal(data);
            return result; // 加密
//        } catch (NoSuchAlgorithmException e) {
//            log.error(e);
//        } catch (NoSuchPaddingException e) {
//            log.error(e);
//        } catch (InvalidKeyException e) {
//            log.error(e);
//        } catch (IllegalBlockSizeException e) {
//            log.error(e);
//        } catch (BadPaddingException e) {
//            log.error(e);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }

    /**
     * AES 转变
     * <p>法算法名称/加密模式/填充方式</p>
     * <p>加密模式有：电子密码本模式 ECB、加密块链模式 CBC、加密反馈模式 CFB、输出反馈模式 OFB</p>
     * <p>填充方式有：NoPadding、ZerosPadding、PKCS5Padding</p>
     */
    private static final String AES_Algorithm = "AES";


    /**
     * AES 解密 Base64 编码密文
     *
     * @param data           Base64 编码密文
     * @param keyString      16、24、32 字节秘钥
     //* @param transformation 转变
     //* @param iv             初始化向量
     * @return 明文
     */
    public static byte[] decryptBase64AES(final byte[] data,
                                          final String keyString) {
        byte[] key = hexString2Bytes(keyString);
        return decryptAES(base64Decode(data), key);
    }


    /**
     * AES 加密后转为 Base64 编码
     *
     * @param data           明文
     * @param keyString      16、24、32 字节秘钥
     //* @param transformation 转变
    // * @param iv             初始化向量
     * @return Base64 密文
     */
    public static byte[] encryptAES2Base64(final byte[] data,
                                           final String keyString) {
        byte[] key = hexString2Bytes(keyString);
        return base64Encode(encryptAES(data, key));
    }

    public static String bytes2HexString(final byte[] bytes) {
        if (bytes == null) return null;
        int len = bytes.length;
        if (len <= 0) return null;
        char[] ret = new char[len << 1];
        for (int i = 0, j = 0; i < len; i++) {
            ret[j++] = hexDigits[bytes[i] >>> 4 & 0x0f];
            ret[j++] = hexDigits[bytes[i] & 0x0f];
        }
        return new String(ret);
    }

    private static final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static byte[] hexString2Bytes(String hexString) {
        if (isSpace(hexString)) return null;
        int len = hexString.length();
        if (len % 2 != 0) {
            hexString = "0" + hexString;
            len = len + 1;
        }
        char[] hexBytes = hexString.toUpperCase().toCharArray();
        byte[] ret = new byte[len >> 1];
        for (int i = 0; i < len; i += 2) {
            ret[i >> 1] = (byte) (hex2Dec(hexBytes[i]) << 4 | hex2Dec(hexBytes[i + 1]));
        }
        return ret;
    }

    private static int hex2Dec(final char hexChar) {
        if (hexChar >= '0' && hexChar <= '9') {
            return hexChar - '0';
        } else if (hexChar >= 'A' && hexChar <= 'F') {
            return hexChar - 'A' + 10;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static byte[] base64Encode(final byte[] input) {
        return Base64.encode(input);
    }

    public static byte[] base64Decode(final byte[] input) {

        return Base64.decode(input);
    }

    private static boolean isSpace(final String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    /**
     * 解密
     * @return 解密后的字符串
     */
    public static String Decrypt256(String src, String key) {
        try {
            // 判断Key是否正确
            if (key == null) {
                System.out.print("Key为空null");
                return null;
            }

            // 密钥补位
            int plus= 32-key.length();
            byte[] data = key.getBytes("utf-8");
            byte[] raw = new byte[32];

            byte[] plusbyte={ 0x08, 0x08, 0x04, 0x0b, 0x02, 0x0f, 0x0b, 0x0c,0x01, 0x03, 0x09, 0x07, 0x0c, 0x03, 0x07, 0x0a, 0x04, 0x0f,0x06, 0x0f, 0x0e, 0x09, 0x05, 0x01, 0x0a, 0x0a, 0x01, 0x09,0x06, 0x07, 0x09, 0x0d };
            for(int i=0;i<32;i++)
            {
                if (data.length > i)
                    raw[i] = data[i];
                else
                    raw[i] = plusbyte[plus];
            }

            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);

            //byte[] encrypted1 = new Base64().decode(src);//base64
            byte[] encrypted1 = toByteArray(src);//十六进制

            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    /**
     * 16进制的字符串表示转成字节数组
     *
     * @param hexString 16进制格式的字符串
     * @return 转换后的字节数组
     **/
    public static byte[] toByteArray(String hexString) {
        if (hexString.isEmpty())
            throw new IllegalArgumentException("this hexString must not be empty");

        hexString = hexString.toLowerCase();
        final byte[] byteArray = new byte[hexString.length() / 2];
        int k = 0;
        for (int i = 0; i < byteArray.length; i++) {//因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
            byteArray[i] = (byte) (high << 4 | low);
            k += 2;
        }
        return byteArray;
    }

    public static void main(String[] args) {


    }
}
