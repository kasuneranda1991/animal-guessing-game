/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cqu.game.datastructure;

import com.cqu.game.interfaces.IBehaviour;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

/**
 * Game Decision tree
 * 
 * Tree includes all the leaf nodes and non leaf nodes
 * 
 * @author Madahapola Kankanamalage Kasun Eranda - 12216898
 * 
 */
public class DecisionTree {
    private Node root; // root node of the tree
    private IBehaviour behaviour; // object reference implemented by interface

    /**
     * Class default constructor
     * Assign null values to class variables
     */
    public DecisionTree() {
        this.root = null;
        this.behaviour = null;
    }

    /**
     * Parameterized class constructor
     * 
     * @param behaviour
     */
    public DecisionTree(IBehaviour behaviour) {
        this.behaviour = behaviour;
    }

    /**
     * execute
     * return true if player wins return true otherwise false
     * method to be invoked by the Game class’s play() method inside its “play
     * again” loop
     * 
     * @return boolean
     */
    public boolean execute() {
        if (this.root == null) { // tree is empty
            this.root = behaviour.emptyTree();
            System.out.println("Create root node: " + this.root.getData());
            return true;
        } else {
            return execute(root);
        }
        // return false;
    }

    /**
     * This method should executed if tree not empty
     * return true if player wins otherwise false
     * 
     * @param n Node - Take the root node of a Tree or Subtree
     * @return boolean
     */
    private boolean execute(Node n) {
        n.getQuestion();
        if (n.isLeaf()) {
            /**
             * Leaf node processing
             */
            return this.behaviour.processLeafNode(n);
        } else {
            /**
             * None leaf node processing
             * 
             * userResponse = True - If user answer as yes for the question
             * userResponse = False - If user answer as No for the question
             */
            boolean userResponse = this.behaviour.processNonLeafNode(n);
            if (userResponse) {
                return this.execute(n.getRight()); // proceed to right branch node
            }
            return this.execute(n.getLeft()); // proceed to left branch node
        }
    }

    /**
     * Display the current Decision tree data structure
     * 
     * @return string - tree structure
     */
    public String display() {
        StringBuilder sb = new StringBuilder();
        display(sb, 0, "root:", root);
        return sb.toString();
    }

    /**
     * Stringify nodes
     * 
     * @param sb
     * @param level
     * @param direction
     * @param node
     */
    private void display(StringBuilder sb, int level, String direction, Node node) {
        if (node == null) {
            return;
        }
        level = level + 2;
        for (int i = 0; i < level; i++) {
            sb.append(" ");
        }
        sb.append(String.format("%s %s %d\n", direction, node.data, node.label));
        display(sb, level, "left: ", node.left);
        display(sb, level, "right:", node.right);
    }

    /**
     * Labeling the tree nodes using preorder traversal
     * 
     * @param n     - node
     * @param count -label
     * @return
     */
    private int label(Node n, int count) {
        if (n != null) {
            int next = label(n.left, count);
            n.label = next++;
            next = label(n.right, next);
            return next;
        }
        return count;
    }

    /**
     * Save tree data to a file
     * 
     * @param name - file name
     * @throws Exception
     */
    public void save(String name) throws Exception {

        label(root, 1); // Label all the tree nodes

        // File file = new File(name);
        try (Formatter formatter = new Formatter(name)) {
            save(root, formatter); // Start saving the tree
        } catch (FileNotFoundException e) {
            throw new Exception("File not found: " + name);
        }
    }

    /**
     * Write node data into file
     * 
     * @param node      - node to be write
     * @param formatter
     */
    private void save(Node node, Formatter formatter) {
        if (node == null) {
            return;
        }
        formatter.format("%d\n", node.getLabel()); // Write label
        formatter.format("%s\n", node.getData()); // Write data
        save(node.getLeft(), formatter); // save left node
        save(node.getRight(), formatter); // save write node
    }

    /**
     * Load the game file
     * 
     * @param fname -- Saved Game data file
     * @throws Exception
     */
    public void load(String fname) throws Exception {

        // Create a Scanner object to read from the file
        Scanner scanner = new Scanner(new File(fname));

        // Read data from the file using the Scanner
        while (scanner.hasNextLine()) {

            int label = Integer.parseInt(scanner.nextLine()); // read the label value
            String data = scanner.nextLine(); // read the node value

            // create nodes using file data
            this.root = this.insert(this.root, new Node(data, label));
        }

        // Close the Scanner
        scanner.close();

    }

    /**
     * Create new node based on the game file data
     * 
     * @param n - root node
     * @param t - next node
     * @return Node object
     */
    private Node insert(Node n, Node t) {

        if (n == null) {
            return t;// Create the root node
        }

        // assign the left and right nodes
        if (t.getLabel() < n.getLabel()) {
            n.left = insert(n.left, t); // assign left branch node
        } else if (t.getLabel() > n.getLabel()) {
            n.right = insert(n.right, t); // assign right branch node
        }

        return n; // return a node
    }

}
