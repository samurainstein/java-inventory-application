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
 *This class creates an inventory management application.
 * @author Eric
 */
public class InventoryApplication extends Application {

    /**This is the main method. 
     * This is the first method that runs when you run the program.
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
