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
import model.Part;
import model.Product;

/**
 * FXML Controller class
 *
 * @author Eric
 */
public class ModifyPartFormController implements Initializable {

    @FXML
    private RadioButton radioInHouse;
    @FXML
    private ToggleGroup sourceToggle;
    @FXML
    private RadioButton radioOutsourced;
    @FXML
    private TextField modPartIDTF;
    @FXML
    private TextField modPartNameTF;
    @FXML
    private TextField modPartInvTF;
    @FXML
    private TextField modPartPriceTF;
    @FXML
    private TextField modPartMaxTF;
    @FXML
    private Label machineOrCompany;
    @FXML
    private TextField modPartMinTF;
    @FXML
    private Button modPartSaveBu;
    @FXML
    private Button modPartCancelBu;
    @FXML
    private TextField modPartMachCompTF;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    //Members
    private Part passPart;
    
    //Methods
    public void passPartData(Part part) {
        passPart = part;
        modPartIDTF.setText(Integer.toString(part.getId()));
        modPartNameTF.setText(part.getName());
        modPartInvTF.setText(Integer.toString(part.getStock()));
        modPartPriceTF.setText(Double.toString(part.getPrice()));
        modPartMaxTF.setText(Integer.toString(part.getMax()));
        modPartMinTF.setText(Integer.toString(part.getMin()));
        
        if(passPart instanceof InHouse) {
            radioInHouse.setSelected(true);
            modPartMachCompTF.setText(String.valueOf(((InHouse)part).getMachineID()));           
        }
        else if(passPart instanceof Outsourced) {
            machineOrCompany.setText("Company");
            radioOutsourced.setSelected(true);
            modPartMachCompTF.setText(String.valueOf(((Outsourced)part).getCompanyName()));
        }  
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
            
            int partID = Integer.parseInt(modPartIDTF.getText());
            String name = modPartNameTF.getText();
            int stock = Integer.parseInt(modPartInvTF.getText());
            double price = Double.parseDouble(modPartPriceTF.getText());
            int max = Integer.parseInt(modPartMaxTF.getText());
            int min = Integer.parseInt(modPartMinTF.getText());
            int machineID;
            String companyName;
            
            int index = Inventory.getAllParts().indexOf(passPart);

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
                machineID = (Integer.parseInt(modPartMachCompTF.getText()));
                Part updatePart = new InHouse(partID, name, price, stock, min, max, machineID);
                Inventory.updatePart(index, updatePart);         
            }
            else if(radioOutsourced.isSelected()) {
                companyName = modPartMachCompTF.getText();
                Part updatePart = new Outsourced(partID, name, price, stock, min, max, companyName);
                Inventory.updatePart(index, updatePart);            
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
