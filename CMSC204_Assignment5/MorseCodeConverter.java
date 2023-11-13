package morseCode;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The MorseCodeConverter class provides methods for converting Morse code to English,
 * reading Morse code from a file, and printing the Morse code tree.
 *
 * @author Moises Merlos
 * @date 11/12/2023
 */
public class MorseCodeConverter {
    private static MorseCodeTree morseCodeTree = new MorseCodeTree();

    /**
     * Converts Morse code into English.
     *
     * @param code the Morse code to be converted
     * @return the English translation of the Morse code
     */
    public static String convertToEnglish(String code) {
        StringBuilder textBuilder = new StringBuilder();

        String[] words = code.split(" / ");
        for (String word : words) {
            String[] letters = word.split(" ");
            for (String letter : letters) {
                String translation = morseCodeTree.fetch(letter);
                if (translation != null) {
                    textBuilder.append(translation);
                }
            }
            textBuilder.append(" ");
        }

        return textBuilder.toString().trim();
    }

    /**
     * Converts a file containing Morse code into English.
     *
     * @param codeFile the file containing Morse code
     * @return the English translation of the Morse code in the file
     * @throws FileNotFoundException if the specified file is not found
     */
    public static String convertToEnglish(File codeFile) throws FileNotFoundException {
        String code = "how do i love thee let me count the ways";
        try (Scanner scanner = new Scanner(codeFile)) {
            code = scanner.nextLine();
            code = convertToEnglish(code);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.print(code);
        return code;
    }

    /**
     * Prints the Morse code tree in a specific order.
     *
     * @return the data in the tree in the specified order
     */
    public static String printTree() {
        StringBuilder dataTree = new StringBuilder();
        ArrayList<String> treeList = morseCodeTree.toArrayList();

        for (String treeElement : treeList) {
            dataTree.append(treeElement).append(" ");
        }

        return dataTree.toString().trim();
    }
}