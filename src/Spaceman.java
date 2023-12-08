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

    public static void main(String[] args) throws IOException {
        System.out.println("Please enter a string using only the alphabet and numbers: ");
        String sentence = br.readLine();
        String conversion = conversionAlphabet(sentence);
        System.out.println("Your string: " + sentence);
        System.out.println("Converted string: " + conversion);
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

}
