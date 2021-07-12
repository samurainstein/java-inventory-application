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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
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
    private TextField modPartMachineIDTF;
    @FXML
    private TextField modPartMinTF;
    @FXML
    private Button modPartSaveBu;
    @FXML
    private Button modPartCancelBu;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //modPartNameTF.setText(part.getName());
    }    
    
    //Members
    private Part part;
    
    //Methods
    public void passPartData(Part part) {
        //FIX THIS
        this.part = part;
        modPartIDTF.setText(Integer.toString(part.getId()));
        modPartNameTF.setText(part.getName());
        modPartInvTF.setText(Integer.toString(part.getStock()));
        modPartPriceTF.setText(Double.toString(part.getPrice()));
        modPartMaxTF.setText(Integer.toString(part.getMax()));
        modPartMinTF.setText(Integer.toString(part.getMin()));
        
        if(part instanceof InHouse) {
            radioInHouse.setSelected(true);
            //modPartMachineIDTF.setText(Integer.toString(InHouse.getMachineID()));   

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
    
}
