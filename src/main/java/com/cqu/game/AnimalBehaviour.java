package com.cqu.game;

import com.cqu.game.interfaces.IBehaviour;
import com.cqu.game.interfaces.IView;
import com.cqu.game.datastructure.Node;

/**
 * @author Madahapola Kankanamalage Kasun Eranda - 12216898
 * 
 * Animal behaviour class
 */

public class AnimalBehaviour implements IBehaviour {

    private IView view;

    /**
     * Parameterized class constructor
     * 
     * @param view
     */
    public AnimalBehaviour(IView view) {
        this.view = view;
    }

    /**
     * If decision tree empty, ask player for animal and create new node and return the node reference
     */
    public Node emptyTree() {
        return new Node(this.view.ask("What is your animal?"));
    }

    /**
     * Process None leaf node recursively
     * 
     * @param n Node - non leaf node
     * @return boolean True - If user response Yes, vice versa
     */
    public boolean processNonLeafNode(Node n) {
        System.out.println("Process non leaf node");
        return this.view.choose(n.getData()); // user answer Yes or No
    }

    /**
     * Process leaf node
     * 
     * @param n Node - leaf node
     * @return boolean
     */
    public boolean processLeafNode(Node n) {
        System.out.println("Process leaf node");
        // Get user response by showing the program guessed animal. true if the program
        // guess correct
        boolean isGuessCorrect = this.view.choose(n.getQuestion());
        if (!isGuessCorrect) {
            // If the program guess is wrong, ask questions about the animal
            String newAnimal = this.view.ask("You win!,What is your animal?"); // ask the animal

            String question = this.view
                    .ask(String.format(
                            "Provide a yes/no question that distinguishes between %s and %s.\nYes = %s; No= %s",
                            newAnimal, n.getData(), newAnimal, n.getData())); // new question about the animal

            n.extend(question, n, new Node(newAnimal)); // extend the node
            return !isGuessCorrect;
        }
        return !isGuessCorrect; // return false if player loose

    }

}
