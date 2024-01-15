/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.cqu.game;

import com.cqu.game.interfaces.IView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;

/**
 * FXML Controller class has methods to handle UI button actions
 *
 * @author kasun Eranda
 */
public class HomeController implements Initializable, IView {

    private Game game;

    private final static String UNDER_DEV = "Under Development";

    @FXML
    private Button playGame;
    @FXML
    private TextArea textArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Inject the game object reference
     * 
     * @param game
     */
    public void inject(Game game) {
        this.game = game;
    }

    /**
     * Handle UI play button action
     * 
     * @param event
     * @throws IOException
     */
    @FXML
    private void playGameBtn(ActionEvent event) throws IOException {
        this.game.play();
    }

    /**
     * Handle UI load button action
     * 
     * @param event
     */
    @FXML
    private void loadGameBtn(ActionEvent event) {
        try {
            // Load the animal.txt data file
            this.game.load("animal.txt");
            this.append("the tree has been loaded");
        } catch (Exception e) {
            // display the error message if there any exception occurs
            this.append("the tree was not able to be loaded from the filename provided");
        }
    }

    /**
     * Handle UI save button action
     * 
     * @param event
     */
    @FXML
    private void saveGameBtn(ActionEvent event) {
        try {
            //save the game data on animal.txt file
            this.game.save("animal.txt");
            this.append("Game has been saved!");
        } catch (Exception e) {
            this.append("the tree was not able to be saved in the filename provided");
        }
    }

    /**
     * Handle UI display tree button action
     * 
     * @param event
     */
    @FXML
    private void displayTreeBtn(ActionEvent event) {
        this.append(this.game.display());
    }

    /**
     * Handle UI exit button action
     * 
     * @param event
     */
    @FXML
    private void exitBtn(ActionEvent event) {
        System.exit(0); // exit from the game
    }

    // For yes/no responses to a question
    @Override
    public boolean choose(String q) {
        String r = choose(q, "Yes", "No");
        if (r.equals("Yes")) {
            return true; // response - Yes
        }
        return false; // response - No
    }

    // more general version of choose - used to implement yes/no
    // version above
    @Override
    public String choose(String q, String a1, String a2) {

        ButtonType b1 = new ButtonType(a1);
        ButtonType b2 = new ButtonType(a2);
        Alert alert = new Alert(Alert.AlertType.NONE, q, b1, b2);
        alert.setTitle("Choose");

        // Block execution until the user responds
        java.util.Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == b1) {
            return a1;
        }
        return a2;
    }

    // minimal validation - ensure that some non-whitespace is
    // entered
    private boolean validate(String s) {
        // check non empty string or just whitespace
        // (should never be null but check anyway)
        if ((s == null) || (s == "") || (s.matches("\\s*"))) {
            return false;
        }
        return true;
    }

    /**
     * Display as dialog box with input text field
     * 
     * @param any question
     */
    @Override
    public String ask(String q) {
        String r = q;
        String s = "";
        boolean valid = false;
        while (!valid) {
            TextInputDialog tid = new TextInputDialog("");
            tid.setHeaderText(q);
            // Disable the cancel button
            Button cancel = (Button) tid.getDialogPane().lookupButton(ButtonType.CANCEL);
            cancel.setDisable(true);
            // Block execution until the user responds
            tid.showAndWait();
            s = tid.getEditor().getText();
            valid = validate(s);
            if (!valid) {
                q = r;
            }
        }
        // remove leading and/or trailing whitespace
        return s.trim();
    }

    /**
     * Display given message on UI Text area
     * 
     * @param s - display message
     */
    @Override
    public void display(String s) {
        textArea.clear();
        textArea.appendText(s);
    }

    /**
     * Append Message on text area
     * 
     * @param s - append message
     */
    @Override
    public void append(String s) {
        textArea.clear();
        textArea.appendText(s);
    }

    /**
     * Bind Game reference
     * 
     * @param game
     */
    public void bind(Game game) {
        this.game = game;
    }

}
