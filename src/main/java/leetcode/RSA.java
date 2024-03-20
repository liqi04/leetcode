package leetcode;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSA {
    public static String encrypt(String data) throws Exception {
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDLmcWraAL0H6qovZjul7YKCWcNJfGctAKj/Xasy4jnPNEvYpUsKEM8lFSYooTGasiyVry09aLARDG1TTixHQUyTV6qTnHbaWfuSFwg2TKbzGYYmwMdK/svrKTisN+eypXntqtBmItVvrSmXg8ua8aEnPASwJhLV6wNvAPKn1ae3QIDAQAB";
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.getDecoder().decode(publicKey.getBytes());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, keyFactory.generatePublic(keySpec));
        int inputLen = data.getBytes("utf-8").length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        while (inputLen - offset > 0) {
            if (inputLen - offset > 117) {
                cache = cipher.doFinal(data.getBytes("utf-8"), offset, 117);
            } else {
                cache = cipher.doFinal(data.getBytes("utf-8"), offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * 117;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return new String(Base64.getEncoder().encode(encryptedData));
    }

    public static void main(String[] args) throws Exception {
        System.out.println(encrypt("伍海莹"));
        System.out.println(encrypt("440782200111174726"));
    }
}
