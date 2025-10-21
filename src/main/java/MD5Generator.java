import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Generator {

    public static String generateMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("sha-256");

            byte[] messageDigest = md.digest(input.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);
            StringBuilder haste = new StringBuilder(no.toString(16));

            while (haste.length() < 32) {
                haste.insert(0, "0");
            }

            return haste.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String texto = "0000";
        String hashMD5 = generateMD5(texto);
        if (generateMD5(texto).equals(generateMD5(texto))){
            System.out.println("Texto: " + texto);
            System.out.println("MD5: " + hashMD5);
        }
    }
}