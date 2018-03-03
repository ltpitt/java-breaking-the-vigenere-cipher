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
        for (String line : fr.lines()) {
            dictionary.add(line.toLowerCase());
        }
        return dictionary;
    }
    
    /*
    In the VigenereBreaker class, write the public method
    countWords, which has two parameters—a String message,
    and a HashSet of Strings dictionary.
    This method should split the message into words (use 
    .split(“\\W+”), which returns a String array), 
    iterate over those words, and see how many of them are 
    “real words”—that is, how many appear in the dictionary.
    Recall that the words in dictionary are lowercase.
    This method should return the integer count of how
    many valid words it found.
    */
    public int countWords(String message, HashSet<String> dictionary) {
        int foundWordsCount = 0;
        String[] wordArray = new String[message.split("\\W+").length];
        wordArray = message.split("\\W+");
        for (String word : wordArray) {
             if (dictionary.contains(word)){
                 foundWordsCount ++;
             }
        }
        return foundWordsCount;
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
        System.out.println("readDictionary:");        
        HashSet<String> dictionary = readDictionary(new FileResource());
        System.out.println(countWords("Buono giornata",dictionary));
        //System.out.println(readDictionary(new FileResource()));
        //VigenereCipher newvigenere = new VigenereCipher(key);
        //System.out.println(newvigenere.decrypt(frr));        
    }
}
