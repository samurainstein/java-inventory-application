/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class creates an inventory management application. 
 * 
 * <p>
 * RUNTIME ERROR :
 * When I was trying to figure out how to create a popup notification when a user 
 * put an incorrect value in the "Inv" field of the "Add Part Form", I first tried 
 * setting it up with a "while" loop. When I ran the program, and tested an
 * incorrect value, the popup appeared, but because it was stuck in a while loop,
 * whenever I hit "OK", the notification would just pop up again, and not let me 
 * actually change the value that I needed to.  After some thought, I realized that
 * an "If" statement that generated a popup and returned to the form after the user 
 * pressed ok would work better. 
 * </p>
 * <p>
 * FUTURE ENHANCEMENT:
 * Enhancing the search function might be a useful feature for this program.  For 
 * example, being able to filter parts based on a "created on" date.  Another example 
 * might be, for the products table, filtering out products that include a specific 
 * associated part.
 * 
 * </p>
 * @author Eric
 */
public class InventoryApplication extends Application {

    /**This is the main method. 
     * This is the first method that runs when you run the program.
     * <p>
     * Javadoc Comments are located in the 
     * </p>
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**Method for displaying the main window. 
     * This loads the Main Page FXML file, and sets the title.
     * @param stage the command line arguments
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Inventory Application");
        stage.show();
    }
    
}
