package morseCode;

/**
 * The TreeNode class represents a node in a binary tree.
 * Each node contains data of type T and references to its left and right children.
 *
 * @param <T> the type of data stored in the node
 * @author Moises Merlos
 * @date 11/12/2023
 * 
 */
public class TreeNode<T> {
    protected T data;
    protected TreeNode<T> left, right;

    /**
     * Constructs a new TreeNode with the given data and no children.
     *
     * @param dataNode the data to be stored in the node
     */
    public TreeNode(T dataNode) {
        this.data = dataNode;
        this.left = null;
        this.right = null;
    }

    /**
     * Constructs a new TreeNode by copying the data and children from another node.
     *
     * @param node the node to be copied
     */
    public TreeNode(TreeNode<T> node) {
        this.data = node.data;
        this.left = node.left;
        this.right = node.right;
    }

    /**
     * Gets the data stored in the node.
     *
     * @return the data stored in the node
     */
    public T getData() {
        return this.data;
    }
}