package com.cqu.game.datastructure;

/**
 * @author Madahapola Kankanamalage Kasun Eranda - 12216898
 *         This is the node class of the decision tree
 */
public class Node {
    protected String data; // name of the animal(leaf Node) / question to be asked(non-leaf Node)
    protected int label; // used when reading or writing from the decision tree to file
    protected Node left; // left node reference of the decision tree
    protected Node right; // right node reference of the decision tree

    /**
     * Node class constructor with data parameter
     * 
     * @param data
     */
    public Node(String data) {
        this.data = data;
    }

    /**
     * Node class constructor with two parameters
     * 
     * @param data
     * @param label
     */
    public Node(String data, int label) {
        this(data);
        this.label = label;
    }

    /**
     * Node class constructor with three parameters
     * 
     * @param data
     * @param left
     * @param right
     */
    public Node(String data, Node left, Node right) {
        this(data);
        this.left = left;
        this.right = right;
    }

    /**
     * Method only be invoked in processLeafNode method 
     * Ask from the player that the animal stored in a node is player guessed animal
     * 
     * @return String - Question
     */
    public String getQuestion() {
        return String.format("Is your animal a(n) %s", this.data);
    }

    /**
     * Extend the tree by creating new node of animal or question
     * 
     * @param data        - New question asked by the program about the
     *                    animal(stored in left subtree)
     * @param leftAnimal  - old animal stored in new leaf node and attached to left
     *                    subtree node(com incorrect guess)
     * @param rightAnimal - new animal stored in new leaf node
     */
    public void extend(String data, Node leftAnimal, Node rightAnimal) {
        this.left = new Node(leftAnimal.getData()); // create left node
        this.right = new Node(rightAnimal.getData()); // create right node
        this.data = data; // New Question
        System.out.printf("\nQ: %s L.N: %s R.N: %s\n", this.data, this.left.getData(), this.right.getData());
    }

    /**
     * Check the node whether leaf node or not
     * 
     * @return boolean
     */
    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    /**
     * Return the reference to left sub tree
     * 
     * @return Node object
     */
    public Node getLeft() {
        return this.left;
    }

    /**
     * Return the reference to right sub tree
     * 
     * @return Node() object
     */
    public Node getRight() {
        return this.right;
    }

    /**
     * Return the data stored in the node
     * 
     * @return String
     */
    public String getData() {
        return this.data;
    }

    /**
     * Retrieve the node label value
     * 
     * @return int
     */
    public int getLabel() {
        return this.label;
    }
}
