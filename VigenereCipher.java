/******************************************************************************
 *  Michelle Lu    Cybersecurity PD 5
 *
 *  Compilation:  javac VigenereCipher.java
 *  Execution:    java VigenereCipher encode plaintext key
 *                java Vigenereipher decode plaintext key
 *
 *  Dependencies: none
 *
 *  Encodes a plain text given a Vigenere cipher key, decodes a given encoded text using
 *  the given Vigenere cipher key.
 *
 *  % java VigenereCipher ENCODE HOTDOGSTAND BOAR
 *    ICTUPUSKBBD
 *
 *  % java VigenereCipher DECODE ICTUPUSKBBD BOAR
 *    HOTDOGSTAND
 *
 *  % java VigenereCipher ENCODE INEEDMYMORNINGCOFFEE COTTONSWAB
 *    KBXXRZQIOSPWGZQBXBEF
 *
 *  % java VigenereCipher DECODE KBXXRZQIOSPWGZQBXBEF COTTONSWAB
 *    INEEDMYMORNINGCOFFEE
 *
 ******************************************************************************/

public class VigenereCipher{

    // The English Alphabet in a string array
    public static String[] alphabet() {
        String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        return alphabet;
    }

    // Locating the index of the specified letter in the given array
    public static int locate(String letter, String[] alphabet) {
        int location = 0;
        for (int i = 0; i < alphabet.length; i++){
            if (alphabet[i].equals(letter)) location = i;
        }
        return location;
    }

    // returns a string of the key, repeating (as needed) as long as the plaintext
    public static String matchedKey (String plaintext, String key) {
        String matchedKey = "";
        int keyLength = key.length();
        int plaintextLength = plaintext.length();
        for (int i = 0; i < (plaintextLength/keyLength); i++){
            matchedKey += key;
        }
        for (int j = 0; j < (plaintextLength%keyLength);j++){
            matchedKey += key.substring(j,j+1);
        }
        return matchedKey;
    }

    // encodes or decodes the given plain text (or enciphered text)nand returns the encoded or decoded string
    public static String choice (String choice, String plaintext, String matchedKey, String[] alphabet){
        String cipherText = "";
        for (int i = 0; i < plaintext.length(); i++){
            int plaintextLocation = 0;
            int keyLocation = 0;
            if (i == plaintext.length() - 1) {
                plaintextLocation = locate(plaintext.substring(i), alphabet);
                keyLocation = locate(matchedKey.substring(i), alphabet);
            }
            else {
                plaintextLocation = locate(plaintext.substring(i, i + 1), alphabet);
                keyLocation = locate(matchedKey.substring(i, i + 1), alphabet);
            }
            if (choice.equals("ENCODE")) {
                cipherText += alphabet[(plaintextLocation + keyLocation) % 26];
            }
            if (choice.equals("DECODE")){
                int shift = 0;
                if ((plaintextLocation - keyLocation) >= 0) shift = (plaintextLocation - keyLocation) % 26;
                if ((plaintextLocation - keyLocation) < 0) shift = (26 - (plaintextLocation - keyLocation)* -1) % 26;
                cipherText += alphabet[shift];
            }
        }
        return cipherText;
    }

    public static void main(String [] args){
        String encodeOrDecode = (args[0]).toUpperCase();
        String text = (args[1]).toUpperCase();
        String key = (args[2]).toUpperCase();
        String matchedKey = matchedKey(text, key);
        System.out.println(choice(encodeOrDecode, text, matchedKey, alphabet()));
    }
}
