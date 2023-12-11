import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Spaceman {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        System.out.print("Please enter a string using only the alphabet and numbers: ");
        String sentence = br.readLine();
        String conversion = conversionAlphabet(sentence);
        System.out.println("\n" + "Your string: " + sentence);
        System.out.println("\n" + "Converted string: " + conversion);
        System.out.println("\n" + "SHA-256 Hash: " + toHexString(getSHA(conversion)));
        String encryptedText = caesar(sentence, 5);
        System.out.println("\n" + "Caesar Cipher string: " + encryptedText);
        System.out.println("\n" + "Brute force shifts plus new string: ");
        bruteForceDecrypt(sentence);

    }

    public static String conversionAlphabet(String text) {
        HashMap<Character, String> newAlphabet = new HashMap<>();
        newAlphabet.put('A', "~#"); newAlphabet.put('B', "!$"); newAlphabet.put('C', "@%");
        newAlphabet.put('D', "#^"); newAlphabet.put('E', "$&"); newAlphabet.put('F', "%*");
        newAlphabet.put('G', "^~"); newAlphabet.put('H', "&!"); newAlphabet.put('I', "*@");
        newAlphabet.put('J', "~!"); newAlphabet.put('K', "@#"); newAlphabet.put('L', "$%");
        newAlphabet.put('M', "^&"); newAlphabet.put('N', "*~"); newAlphabet.put('O', "!@");
        newAlphabet.put('P', "#$"); newAlphabet.put('Q', "%^"); newAlphabet.put('R', "&*");
        newAlphabet.put('S', "~@"); newAlphabet.put('T', "!#"); newAlphabet.put('U', "@$");
        newAlphabet.put('V', "#%"); newAlphabet.put('W', "$^"); newAlphabet.put('X', "%&");
        newAlphabet.put('Y', "^*"); newAlphabet.put('Z', "&~"); newAlphabet.put('0', "~$");
        newAlphabet.put('1', "!%"); newAlphabet.put('2', "@^"); newAlphabet.put('3', "#&");
        newAlphabet.put('4', "$*"); newAlphabet.put('5', "%~"); newAlphabet.put('6', "^!");
        newAlphabet.put('7', "&@"); newAlphabet.put('8', "*#"); newAlphabet.put('9', "~%");

        StringBuilder conversion = new StringBuilder();
        text = text.toUpperCase();
        for (char c : text.toCharArray()) {
            if (c == ' ') {
                conversion.append(" ");
            } else if (newAlphabet.containsKey(c)) {
                conversion.append((newAlphabet.get(c)));
                conversion.append(" ");
            }
        }
        return conversion.toString().trim();
    }

    public static byte[] getSHA(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        return md.digest(text.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);

        StringBuilder hexString = new StringBuilder(number.toString(16));

        while (hexString.length() < 64) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }

    public static String caesar(String plaintext, int shift) {

        StringBuilder encryptedText = new StringBuilder();

        for (char character : plaintext.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isLowerCase(character) ? 'a' : 'A';
                int originalAlphabetPosition = character - base;
                int newAlphabetPosition = (originalAlphabetPosition + shift) % 26;
                char newCharacter = (char) (base + newAlphabetPosition);
                encryptedText.append(newCharacter);
            } else {
                encryptedText.append(character);
            }
        }
        return encryptedText.toString();
    }

    public static void bruteForceDecrypt(String encryptedText) {
        for (int shift = 0; shift < 26; shift++) {
            StringBuilder decryptedText = new StringBuilder();

            for (char character : encryptedText.toCharArray()) {
                if (character != ' ' && character != '!' && character != ',') {
                    int originalPosition = character - 'A';
                    int newPosition = (originalPosition - shift % 26);
                    newPosition = newPosition < 0 ? newPosition + 26 : newPosition;
                    char newCharacter = (char) ('A' + newPosition);
                    decryptedText.append(newCharacter);
                } else {
                    decryptedText.append(character);
                }
            }

            System.out.println(shift + ": " + decryptedText);
        }

    }

}
