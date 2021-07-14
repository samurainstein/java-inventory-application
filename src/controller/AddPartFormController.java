/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;

/**
 * FXML Controller class
 *
 * @author Eric
 */
public class AddPartFormController implements Initializable {

    @FXML
    private RadioButton radioInHouse;
    @FXML
    private ToggleGroup sourceToggle;
    @FXML
    private RadioButton radioOutsourced;
    @FXML
    private TextField addPartIDTF;
    @FXML
    private TextField addPartNameTF;
    @FXML
    private TextField addPartInvTF;
    @FXML
    private TextField addPartPriceTF;
    @FXML
    private TextField addPartMaxTF;
    @FXML
    private TextField addPartMinTF;
    @FXML
    private Button addPartSaveBu;
    @FXML
    private Button addPartCancelBu;
    @FXML
    private Label machineOrCompany;
    @FXML
    private TextField addPartMachOrCompTF;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onRadioInHouse(ActionEvent event) {
        machineOrCompany.setText("Machine ID");
    }

    @FXML
    private void onRadioOutsourced(ActionEvent event) {
        machineOrCompany.setText("Company Name");
    }

    @FXML
    private void onCancel(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Inventory Application");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onSave(ActionEvent event) throws IOException {
        try {
            int ID = Inventory.getNextPartID();
            String name = addPartNameTF.getText();
            double price = Double.parseDouble(addPartPriceTF.getText());
            int stock = Integer.parseInt(addPartInvTF.getText());
            int min = Integer.parseInt(addPartMinTF.getText());
            int max = Integer.parseInt(addPartMaxTF.getText()); 
            
            if(max < min) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Entry");
                alert.setContentText("Min should be less than max");
                alert.showAndWait();
                return;
            }
            if(stock > max || stock < min) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Entry");
                alert.setContentText("Inventory amount must be between max and min");
                alert.showAndWait();
                return;
            }
            
            if(radioInHouse.isSelected()) {
                int machID = Integer.parseInt(addPartMachOrCompTF.getText());
                Inventory.addPart(new InHouse(ID, name, price, stock, min, max, machID));
            }
            else {
                String company = addPartMachOrCompTF.getText();
                Inventory.addPart(new Outsourced (ID, name, price, stock, max, min, company));    
            }
            
            Parent root = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Inventory Application");
            stage.setScene(scene);
            stage.show();
        }

        catch(NumberFormatException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Entry");
            alert.setContentText("Please enter a valid value for each field");
            alert.showAndWait();
        }
    }
    
}
