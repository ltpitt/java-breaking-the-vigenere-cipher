import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        if (totalSlices == 0) {
            return "totalSlices cannot be 0";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] decryptedKeys = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++) {
            String encryptedSlice = sliceString(encrypted, i, klength);
            int decryptedKey = cc.getKey(encryptedSlice);
            decryptedKeys[i] = decryptedKey;
        }        
        return decryptedKeys;
    }

    public void breakVigenere() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        int [] keys = new int[5];
        keys = tryKeyLength(encrypted, 4, 'e');
        VigenereCipher vc = new VigenereCipher(keys);
        System.out.println(vc.decrypt(encrypted));        
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dictionary = new HashSet<String>();
        String dictionaryString = fr.asString();
        for (String word : dictionaryString.split("\\W")) {
            dictionary.add(word);
        }
        return dictionary;
        //.add() .contains()
    }
    
    public void tester() {
        System.out.println("Testing sliceString");
        String message;
        message = sliceString("abcdefghijklm", 0, 3);
        System.out.println(message);
        message = sliceString("abcdefghijklm", 1, 3);
        System.out.println(message);
        message = sliceString("abcdefghijklm", 2, 3);
        System.out.println(message);
        message = sliceString("abcdefghijklm", 0, 4);
        System.out.println(message);
        message = sliceString("abcdefghijklm", 1, 4);
        System.out.println(message);
        message = sliceString("abcdefghijklm", 2, 4);
        System.out.println(message);
        message = sliceString("abcdefghijklm", 3, 4);
        System.out.println(message);
        message = sliceString("abcdefghijklm", 0, 5);
        System.out.println(message);
        message = sliceString("abcdefghijklm", 1, 5);
        System.out.println(message);
        message = sliceString("abcdefghijklm", 2, 5);
        System.out.println(message);
        message = sliceString("abcdefghijklm", 3, 5);
        System.out.println(message);
        message = sliceString("abcdefghijklm", 4, 5);
        System.out.println(message);
        System.out.println("Testing tryKeyLength");
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        int [] keys = new int[4];
        keys = tryKeyLength(encrypted, 4, 'e');
        System.out.println(Arrays.toString(keys));
        System.out.println("readDictionary "
        //VigenereCipher newvigenere = new VigenereCipher(key);
        //System.out.println(newvigenere.decrypt(frr));        

    }
}
