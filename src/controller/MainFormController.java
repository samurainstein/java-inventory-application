/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
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
public class MainFormController implements Initializable {

    @FXML
    private TableView<Part> allPartsTable;
    @FXML
    private TableColumn<Part, Integer> partsIDCol;
    @FXML
    private TableColumn<?, ?> partsNameCol;
    @FXML
    private TableColumn<?, ?> partsStockCol;
    @FXML
    private TableColumn<?, ?> partsPriceCol;
    @FXML
    private Button partsAddButton;
    @FXML
    private TableView<Product> allProductsTable;
    @FXML
    private TableColumn<Product, Integer> productsIDCol;
    @FXML
    private TableColumn<Product, String> productsNameCol;
    @FXML
    private TableColumn<Product, Integer> productsStockCol;
    @FXML
    private TableColumn<Product, Double> productsPriceCol;
    @FXML
    private Button partsDeleteButton;
    @FXML
    private TextField partSearchText;
    @FXML
    private TextField productSearchText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //"id" tied to getId() method in Part class
        partsIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        productsIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productsStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        allPartsTable.setItems(Inventory.getAllParts());
        allProductsTable.setItems(Inventory.getAllProducts());
        
        //Test Data
        Inventory.addPart(new InHouse(Inventory.getNextPartID(), "dart", .50, 5, 0, 10, 101));
        Inventory.addPart(new InHouse(Inventory.getNextPartID(), "mart", .50, 5, 0, 10, 101));
        Inventory.addPart(new Outsourced(Inventory.getNextPartID(), "cart", .50, 5, 0, 10, "company 1"));
        Inventory.addPart(new Outsourced(Inventory.getNextPartID(), "starter", .50, 5, 0, 10, "company 2"));
        
        Inventory.addProduct(new Product(Inventory.getNextProductID(), "snare", .50, 5, 0, 10));
        Inventory.addProduct(new Product(Inventory.getNextProductID(), "dare", .50, 5, 0, 10));
        Inventory.addProduct(new Product(Inventory.getNextProductID(), "hare", .50, 5, 0, 10));
        Inventory.addProduct(new Product(Inventory.getNextProductID(), "rare", .50, 5, 0, 10));
    }    

    @FXML
    public void onPartsAdd(ActionEvent event) throws IOException {
        System.out.println("Add Parts Button Clicked");
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddPartForm.fxml"));
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Part Form");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void onPartsModify(ActionEvent event) throws IOException {
        System.out.println("Modify Parts Button Clicked");
        Parent root = FXMLLoader.load(getClass().getResource("/view/ModifyPartForm.fxml"));
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Modify Part Form");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void onPartsDelete(ActionEvent event) {
        System.out.println("Delete button clicked");
        
        Part selectedPart = allPartsTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null){
            //JOptionPane.showMessageDialog(null, "Please Select a Part");
            return;
        }
        
        boolean deleteConfirm = Inventory.deletePart(selectedPart);
        if(deleteConfirm)
            JOptionPane.showMessageDialog(null, "Part was deleted");
        
        else if(!deleteConfirm)
            return;
    }
    
    @FXML
    private void onProductsAdd(ActionEvent event) throws IOException {
        System.out.println("Add Products Button Clicked");
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddProductForm.fxml"));
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Products Form");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void onProductsModify(ActionEvent event) throws IOException {
        System.out.println("Modify Products Button Clicked");
        Parent root = FXMLLoader.load(getClass().getResource("/view/ModifyProductForm.fxml"));
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Modify Products Form");
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
    private void onProductSearch(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
            String searchText = productSearchText.getText();
            ObservableList<Product> searchProduct = Inventory.lookupProduct(searchText);
            allProductsTable.setItems(searchProduct);
            
            if(searchProduct.size() == 0) {
                int productID = Integer.parseInt(searchText);
                Product productIDSearch = Inventory.lookupProduct(productID);
                if(productIDSearch != null) {
                    searchProduct.add(productIDSearch);
                }
            }
        }
    }
    

}
