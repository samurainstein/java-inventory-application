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
import javafx.scene.control.Alert;
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
public class AddProductFormController implements Initializable {

    @FXML
    private TextField addProductIDTF;
    @FXML
    private TextField addProductNameTF;
    @FXML
    private TextField addProductInvTF;
    @FXML
    private TextField addProductPriceTF;
    @FXML
    private TextField addProductMaxTF;
    @FXML
    private Label machineOrCompany;
    @FXML
    private TextField addProductMinTF;
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
    private TextField partSearchText;
    @FXML
    private TableColumn<Part, Integer> assocPartsIDCol;
    @FXML
    private TableColumn<Part, String> assocPartsNameCol;
    @FXML
    private TableColumn<Part, Integer> assocPartsStockCol;
    @FXML
    private TableColumn<Part, Double> assocPartsPriceCol;
    @FXML
    private TableView<Part> assocPartsTable;

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
    private void onPartSearch(KeyEvent event) {
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
        //FIX THIS
        //Product.addAssociatedPart(allPartsTable.getSelectionModel().getSelectedItem());
        //assocPartsTable.getItems().add(allPartsTable.getSelectionModel().getSelectedItem());    
    }

    @FXML
    private void onRemove(ActionEvent event) {
        //FIX THIS
        //Product.deleteAssociatedPart(assocPartsTable.getSelectionModel().getSelectedItem());
        //assocPartsTable.getItems().remove(assocPartsTable.getSelectionModel().getSelectedItem());    
    }

    @FXML
    private void onSave(ActionEvent event) throws IOException {
        try {
            int ID = Inventory.getNextProductID();
            String name = addProductNameTF.getText();
            double price = Double.parseDouble(addProductPriceTF.getText());
            int stock = Integer.parseInt(addProductInvTF.getText());
            int min = Integer.parseInt(addProductMinTF.getText());
            int max = Integer.parseInt(addProductMaxTF.getText()); 

            Inventory.addProduct(new Product(ID, name, price, stock, min, max));
            
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
