package morseCode;

import java.util.ArrayList;

/**
 * The MorseCodeTree class implements LinkedConverterTreeInterface to represent a binary tree
 * structure for Morse code conversion.
 *
 * @author Moises Merlos
 * @date 11/12/2023
 */
public class MorseCodeTree implements LinkedConverterTreeInterface<String> {
    private TreeNode<String> root;

    /**
     * Constructor for MorseCodeTree. Initializes the root and builds the Morse code tree.
     */
    public MorseCodeTree() {
        root = new TreeNode<String>("");
        buildTree();
    }

    /**
     * Get the root of the tree.
     *
     * @return the root of the tree
     */
    @Override
    public TreeNode<String> getRoot() {
        return root;
    }

    /**
     * Set the root of the tree.
     *
     * @param newNode the new root node
     */
    @Override
    public void setRoot(TreeNode<String> newNode) {
        root = newNode;
    }

    /**
     * Insert a new node into the tree.
     *
     * @param code   the Morse code to be inserted
     * @param result the corresponding letter for the Morse code
     */
    @Override
    public void insert(String code, String result) {
        addNode(root, code, result);
    }

    /**
     * Add a new node to the tree.
     *
     * @param root   the root node to start adding from
     * @param code   the Morse code to be inserted
     * @param letter the corresponding letter for the Morse code
     */
    @Override
    public void addNode(TreeNode<String> root, String code, String letter) {
        if (code.length() == 1) {
            if (code.charAt(0) == '.') {
                root.left = new TreeNode<String>(letter);
            } else if (code.equals("-")) {
                root.right = new TreeNode<String>(letter);
            }
        } else {
            if (code.charAt(0) == '.') {
                addNode(root.left, code.substring(1), letter);
            } else if (code.charAt(0) == '-') {
                addNode(root.right, code.substring(1), letter);
            }
        }
    }

    /**
     * Fetch the corresponding letter for the given Morse code.
     *
     * @param code the Morse code to be fetched
     * @return the corresponding letter for the Morse code
     */
    @Override
    public String fetch(String code) {
        return fetchNode(root, code);
    }

    /**
     * Fetch the corresponding letter for the given Morse code from a specific root.
     *
     * @param root the root node to start fetching from
     * @param code the Morse code to be fetched
     * @return the corresponding letter for the Morse code
     */
    @Override
    public String fetchNode(TreeNode<String> root, String code) {
        if (code.length() == 1) {
            if (code.charAt(0) == '.') {
                return root.left.getData();
            } else {
                return root.right.getData();
            }
        } else {
            if (code.charAt(0) != '.') {
                return fetchNode(root.right, code.substring(1));
            } else {
                return fetchNode(root.left, code.substring(1));
            }
        }
    }

    /**
     * Delete operation is not supported in this implementation.
     *
     * @param data the data to be deleted
     * @return UnsupportedOperationException
     * @throws UnsupportedOperationException always thrown for this method
     */
    @Override
    public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /**
     * Update operation is not supported in this implementation.
     *
     * @return UnsupportedOperationException
     * @throws UnsupportedOperationException always thrown for this method
     */
    @Override
    public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /**
     * Build the Morse code tree by inserting predefined Morse code and letters.
     */
    @Override
    public void buildTree() {
        // Predefined Morse code and letters
        insert(".", "e");
        insert("-", "t");
        insert("..", "i");
        insert(".-", "a");
        insert("-.", "n");
        insert("--", "m");
        insert("...", "s");
        insert("..-", "u");
        insert(".-.", "r");
        insert(".--", "w");
        insert("-..", "d");
        insert("-.-", "k");
        insert("--.", "g");
        insert("---", "o");
        insert("....", "h");
        insert("...-", "v");
        insert("..-.", "f");
        insert(".-..", "l");
        insert(".--.", "p");
        insert(".---", "j");
        insert("-...", "b");
        insert("-..-", "x");
        insert("-.-.", "c");
        insert("-.--", "y");
        insert("--..", "z");
        insert("--.-", "q");
    }

    /**
     * Convert the tree to an ArrayList using in-order traversal.
     *
     * @return an ArrayList containing the data in in-order traversal
     */
    @Override
    public ArrayList<String> toArrayList() {
        ArrayList<String> newList = new ArrayList<String>();
        LNRoutputTraversal(root, newList);
        return newList;
    }

    /**
     * Perform in-order traversal of the tree and populate the ArrayList.
     *
     * @param root the root node to start traversal from
     * @param list the ArrayList to store the traversal result
     */
    @Override
    public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
        if (root != null) {
            LNRoutputTraversal(root.left, list);
            list.add(root.getData());
            LNRoutputTraversal(root.right, list);
        }
    }
}
