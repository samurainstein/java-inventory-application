/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    private TableColumn<Part, String> partsNameCol;
    @FXML
    private TableColumn<Part, Integer> partsStockCol;
    @FXML
    private TableColumn<Part, Double> partsPriceCol;
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
        if(Inventory.getAllParts().size() == 0) {
            Inventory.addPart(new InHouse(Inventory.getNextPartID(), "dart", .50, 5, 0, 10, 101));
            Inventory.addPart(new InHouse(Inventory.getNextPartID(), "mart", .50, 5, 0, 10, 101));
            Inventory.addPart(new Outsourced(Inventory.getNextPartID(), "cart", .50, 5, 0, 10, "company 1"));
            Inventory.addPart(new Outsourced(Inventory.getNextPartID(), "starter", .50, 5, 0, 10, "company 2"));
        }
        
        if(Inventory.getAllProducts().size() == 0) {
            Inventory.addProduct(new Product(Inventory.getNextProductID(), "snare", .50, 5, 0, 10));
            Inventory.addProduct(new Product(Inventory.getNextProductID(), "dare", .50, 5, 0, 10));
            Inventory.addProduct(new Product(Inventory.getNextProductID(), "hare", .50, 5, 0, 10));
            Inventory.addProduct(new Product(Inventory.getNextProductID(), "rare", .50, 5, 0, 10));
        }
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ModifyPartForm.fxml"));
            Parent root = loader.load();
            ModifyPartFormController modPartCont = loader.getController();
            modPartCont.passPartData(allPartsTable.getSelectionModel().getSelectedItem());
            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Modify Part Form");
            stage.setScene(scene);
            stage.show();
        }
        catch(NullPointerException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Selection");
            alert.setContentText("Please select a part");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void onPartsDelete(ActionEvent event) {
        System.out.println("Delete part button clicked");
        Part selectedPart = allPartsTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null){
                JOptionPane.showMessageDialog(null, "Please Select a Part");
                return;
            }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this part?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK) { 
                if(Inventory.deletePart(selectedPart)) {
                    JOptionPane.showMessageDialog(null, "Selected part was deleted");
                }
                else if(!(Inventory.deletePart(selectedPart))) {
                    JOptionPane.showMessageDialog(null, "No parts were deleted");
                }
            }
        }
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ModifyProductForm.fxml"));
            Parent root = loader.load();
            ModifyProductFormController modProductCont = loader.getController();
            modProductCont.passProductData(allProductsTable.getSelectionModel().getSelectedItem());
            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Modify Products Form");
            stage.setScene(scene);
            stage.show();
        }
        catch(NullPointerException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Selection");
            alert.setContentText("Please select a product");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void onProductsDelete(ActionEvent event) {
        System.out.println("Delete product button clicked");
        Product selectedProduct = allProductsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null){
                JOptionPane.showMessageDialog(null, "Please Select a Part");
                return;
            }
        
        if (selectedProduct.getAllAssociatedParts().size() != 0) {
            JOptionPane.showMessageDialog(null, "Please remove associated parts from product before deleting");
            return;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this product?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK) { 
                if(Inventory.deleteProduct(selectedProduct)) {
                    JOptionPane.showMessageDialog(null, "Selected product was deleted");
                }
                else if(!(Inventory.deleteProduct(selectedProduct))) {
                    JOptionPane.showMessageDialog(null, "No products were deleted");
                }
            }
        }
    }

    @FXML
    private void onPartSearch(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
            String searchText = partSearchText.getText();
            ObservableList<Part> searchParts = Inventory.lookupPart(searchText);
            allPartsTable.setItems(searchParts);
            
            if(searchParts.size() == 0) {
                try {
                    int partID = Integer.parseInt(searchText);
                    Part partIDSearch = Inventory.lookupPart(partID);
                    if(partIDSearch != null) {
                        searchParts.add(partIDSearch);
                    }
                }
                catch(NumberFormatException exception){
                    JOptionPane.showMessageDialog(null, "No parts were found");                   
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
                try {
                    int productID = Integer.parseInt(searchText);
                    Product productIDSearch = Inventory.lookupProduct(productID);
                    if(productIDSearch != null) {
                        searchProduct.add(productIDSearch);
                    }
                }
                catch(NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "No products were found");
                }
            }
        }
    }

    @FXML
    private void onExit(ActionEvent event) {
        System.exit(0);
    }
}
