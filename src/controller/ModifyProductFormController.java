/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
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
    @FXML
    private TableView<Part> allPartsTable;
    @FXML
    private TableColumn<Part, Integer> partsIDCol;
    @FXML
    private TableColumn<Part, String> partsNameCol;
    @FXML
    private TableColumn<Part, Integer> partsStockCol;
    @FXML
    private TableColumn<Part, Double> partsPriceCol;
    @FXML
    private TableView<Part> assocPartsTable;
    @FXML
    private TableColumn<Part, Integer> assocPartsIDCol;
    @FXML
    private TableColumn<Part, String> assocPartsNameCol;
    @FXML
    private TableColumn<Part, Integer> assocPartsStockCol;
    @FXML
    private TableColumn<Part, Double> assocPartsPriceCol;
    @FXML
    private TextField partSearchText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partsIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        assocPartsIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocPartsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPartsStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assocPartsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        allPartsTable.setItems(Inventory.getAllParts());
        //FIX THIS
        //assocPartsTable.setItems(Product.getAllAssociatedParts());
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

    @FXML
    private void onSearch(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
            String searchText = partSearchText.getText();
            ObservableList<Part> searchParts = Inventory.lookupPart(searchText);
            allPartsTable.setItems(searchParts);
            
            if(searchParts.size() == 0) {
                int partID = Integer.parseInt(searchText);
                Part partIDSearch = Inventory.lookupPart(partID);
                if(partIDSearch != null) {
                    searchParts.add(partIDSearch);
                }
            }
        }
    }

    @FXML
    private void onAdd(ActionEvent event) {
        assocPartsTable.getItems().add(allPartsTable.getSelectionModel().getSelectedItem());    }

    @FXML
    private void onRemove(ActionEvent event) {
        assocPartsTable.getItems().remove(assocPartsTable.getSelectionModel().getSelectedItem());    
    }
    
}
