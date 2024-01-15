/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cqu.game;

import com.cqu.game.interfaces.IView;
import com.cqu.game.datastructure.DecisionTree;

/**
 * Game class
 * @author Madahapola Kankanamalage Kasu Eranda - 12216898
 */
public class Game {

    private DecisionTree tree; // decision tree reference
    private IView view; // view reference

    /**
     * Game parameterized constructor
     * @param view
     */
    public Game(IView view) {
        this.view = view;
        
        // Create new animal behaviour class object
        AnimalBehaviour animalBehaviour = new AnimalBehaviour(this.view);
        
        // create new decision tree using animal behaviour class object
        this.tree = new DecisionTree(animalBehaviour);
    }

    /**
     * Start to pay the game
     */
    public void play() {
        
        help();// display the help message
        boolean again = true; 
        while (again) {
            
            if (this.tree.execute()) {
                again = view.choose("You won! Play again?");
            } else {
                again = view.choose("I won! Play again?");
            }
        }

    }

    /**
     * Load a save game
     * @param fname
     * @throws Exception
     */
    public void load(String fname) throws Exception {
        this.tree.load(fname);
    }

    /**
     * Save game data on a file path given by the user
     * @param fname - file path
     * @throws Exception
     */
    public void save(String fname) throws Exception {
        this.tree.save(fname);
    }

    /**
     * Display game details
     * @return
     */
    public String display() {
        return this.tree.display();
    }

    /**
     * Game Helps
     */
    private void help() {
        view.display("Think of an animal.If my tree is non-empty, I will ask\n"
                + "some yes/no questions to try to determine what it is.");
    }

}
