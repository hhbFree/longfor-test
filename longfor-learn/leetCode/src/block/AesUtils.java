package block;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;


public class AesUtils {


    public static String aes_Decode_Hex(String encryptHex) {
    	String passwordHex="A4E094C2C932928FF0E205CC05490517";
        encryptHex=encryptHex.replaceAll("%7B%22", "").replaceAll("result", "").replaceAll("succeed", "").replaceAll("error", "").replaceAll("%22%3A%22", "").replaceAll("%22%7D=", "");
        encryptHex=encryptHex.replaceAll("\\{\"", "").replaceAll("result", "").replaceAll("succeed", "").replaceAll("error", "").replaceAll("\":\"", "").replaceAll("\"\\}", "");
    try {

            //encrypt
            byte[] encrypt = hexStringToByte(encryptHex);

            //key
            byte[] key = hexStringToByte(passwordHex);

            //iv
            String ivStr = "4F14EE27C6E593B8E0CD7994C0882C8E";
            byte[] iv = hexStringToByte(ivStr);

            //de
            byte[] decrypt = AES_CBC_Decrypt(encrypt, key, iv);

            //result
            assert decrypt != null;
            String decryptStr = new String(decrypt).trim();
            if (decryptStr.length() <= 6) return "";
            if (!decryptStr.substring(0, 3).equals("xfs")) return "";
            if (!Objects.equals(decryptStr.substring(decryptStr.length() - 3), "erv")) return "";

            return decryptStr.substring(3, decryptStr.length() - 3);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return "";
    }


    public static byte[] AES_CBC_Encrypt(byte[] content, byte[] keyBytes, byte[] ivBytes) throws InvalidAlgorithmParameterException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException {

        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec iv = new IvParameterSpec(ivBytes);
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        return cipher.doFinal(content);

    }

    public static byte[] AES_CBC_Decrypt(byte[] content, byte[] keyBytes, byte[] ivBytes) {

        try {

            SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
            IvParameterSpec iv = new IvParameterSpec(ivBytes);
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            return cipher.doFinal(content);

        } catch (Exception e) {

            System.out.println("exception:" + e.toString());

        }
        return null;
    }

    public static String byteToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer(bytes.length);
        String sTemp;
        for (int i = 0; i < bytes.length; i++) {
            sTemp = Integer.toHexString(0xFF & bytes[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    public static byte[] hexStringToByte(String str) {

        if (str == null || str.trim().equals("")) {
            return new byte[0];
        }

        byte[] bytes = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }

        return bytes;
    }


    public static String aes_Decode_Hex_Auto(String encryptHex) {
        String passwordHex="A4E094C2C932928FF0E205CC05490517";
        encryptHex=encryptHex.replaceAll("%7B%22", "").replaceAll("result", "").replaceAll("succeed", "").replaceAll("error", "").replaceAll("%22%3A%22", "").replaceAll("%22%7D=", "");
        encryptHex=encryptHex.replaceAll("\\{\"", "").replaceAll("result", "").replaceAll("succeed", "").replaceAll("error", "").replaceAll("\":\"", "").replaceAll("\"\\}", "");
        try {

            //encrypt
            byte[] encrypt = hexStringToByte(encryptHex);

            //key
            byte[] key = hexStringToByte(passwordHex);
            //iv
            String ivStr = "4F14EE27C6E593B8E0CD7994C0882C8E";
            byte[] iv = hexStringToByte(ivStr);
            //de
            byte[] decrypt = AES_CBC_Decrypt(encrypt, key, iv);

            //result
            assert decrypt != null;
            //String decryptStr = new String(decrypt).trim();
            String decryptStr=new String(decrypt, "UTF-8").trim();
            return decryptStr;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return "";
    }

    public static void main(String[] args) {
        String asd="787117757EFEFB11D45BDD865FB776994FB13A7C9CAACB4215A7F7BC81C27943EF1D7A8A43CFF2DEF455947930D585375A26D4FD83814165CE6410977D1126354146C5D0F62274DF4A80065642C17AA40DFBE6ED0134C43C70FD7E6101C3C50EB8C5E2265EC726DA1BD0B3FB142F8C2F";
        String s = aes_Decode_Hex(asd);
        System.out.println(s);
    }
}
