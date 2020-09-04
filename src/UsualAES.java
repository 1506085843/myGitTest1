

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.Base64;

/**
 * AES 是一种可逆加密算法，对用户的敏感信息加密处理 对原始数据进行AES加密后，在进行Base64编码转化；
 */
public  class UsualAES {

    /*
     * 加密解密用的Key，即AES_KEY，可以用26个字母和数字组成的16位，此处使用AES-128-CBC加密模式。
     * 注意：普通加密解密key需要为16位。如果使用增强加密和增强解密AES_KEY要为12位，因为增加加密解密中会加4位随机数。
     */
    public static final String AES_KEY = "NX%TB*19!@#H";
    public static final String AES_VECTOR = "12345678901234ad";

    // 加密
    public static String encrypt(String sSrc, String key, String vector) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = key.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec iv = new IvParameterSpec(vector.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
        return new String(Base64.getEncoder().encode(encrypted));// 此处使用BASE64做转码。
    }

    public static String decrypt(String sSrc, String key, String ivs) throws Exception {
        try {
            byte[] raw = key.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivs.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = Base64.getDecoder().decode(sSrc);// 先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }



    public static String encodeBytes(byte[] bytes) {
        StringBuffer strBuf = new StringBuffer();

        for (int i = 0; i < bytes.length; i++) {
            strBuf.append((char) (((bytes[i] >> 4) & 0xF) + ((int) 'a')));
            strBuf.append((char) (((bytes[i]) & 0xF) + ((int) 'a')));
        }

        return strBuf.toString();
    }

    /*
     * 增强加密 密码为原始12位+4位随机数，随机数带入密文中
     */
    public static String encryptAd(String content, String password, String iv) {
        int num = (int) (Math.random() * 9000 + 1000);
        String newPassword = password + num;
        String tt4 = null;
        try {
            tt4 = encrypt(content, newPassword, iv);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        tt4 = tt4.replace("=", "!");
        tt4 = tt4.replace('+', '[');
        tt4 = tt4.replace('/', ']');
        String result = tt4 + num;
        return result;
    }

    /*
     * 增强解密 明文[0,15] 位：密文为24 +4=28 位 明文[16,31] 位：密文为44 +4=48 位
     */
    public static String decryptAd(String data, String skey, String iv) throws IOException {
        data = data.replace("!", "=");
        data = data.replace('[', '+');
        data = data.replace(']', '/');
        int len = data.length();

        String realkey = skey + data.substring(len - 4, len);
        String realcontent = data.substring(0, len - 4);
        String result = null;
        try {
            result = decrypt(realcontent, realkey, iv);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

}