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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Product;

/**
 * FXML Controller class
 *
 * @author Eric
 */
public class ModifyProductFormController implements Initializable {

    @FXML
    private TextField modProductIDTF;
    @FXML
    private TextField modProductNameTF;
    @FXML
    private TextField modProductInvTF;
    @FXML
    private TextField modProductPriceTF;
    @FXML
    private TextField modProductMaxTF;
    @FXML
    private TextField modProductMinTF;
    @FXML
    private Label machineOrCompany;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    //Members
    private Product product;
    
    //Methods
    
    public void passProductData(Product product) {
        this.product = product;
            modProductIDTF.setText(Integer.toString(product.getId()));
            modProductNameTF.setText(product.getName());
            modProductInvTF.setText(Integer.toString(product.getStock()));
            modProductPriceTF.setText(Double.toString(product.getPrice()));
            modProductMaxTF.setText(Integer.toString(product.getMax()));
            modProductMinTF.setText(Integer.toString(product.getMin()));
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
