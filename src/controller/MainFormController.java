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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Part;

/**
 * FXML Controller class
 *
 * @author Eric
 */
public class MainFormController implements Initializable {

    @FXML
    private TableView<Part> allPartsTable;
    @FXML
    private TableColumn<?, ?> partsIDCol;
    @FXML
    private TableColumn<?, ?> partsNameCol;
    @FXML
    private TableColumn<?, ?> partsStockCol;
    @FXML
    private TableColumn<?, ?> partsPriceCol;
    @FXML
    private Button partsAddButton;
    @FXML
    private TableView<?> allProductsTable;
    @FXML
    private TableColumn<?, ?> productsIDCol;
    @FXML
    private TableColumn<?, ?> productsNameCol;
    @FXML
    private TableColumn<?, ?> productsStockCol;
    @FXML
    private TableColumn<?, ?> productsPriceCol;
    
    private ObservableList<Part> allParts = FXCollections.observableArrayList();
    //private ObservableList<Product> allProducts = FXCollections.observableArrayList();
    
    @FXML
    public void onPartsAdd(ActionEvent actionEvent) throws IOException {
        System.out.println("Add Parts Button Clicked");
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddPartForm.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Part Form");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        allPartsTable.setItems(allParts);
        //allProducts.setItems(allProducts);
        
        //"id" tied to getId() method in Part class
        partsIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        //productsIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        //productsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        //productsStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        //productsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
    }    
    

    
}
