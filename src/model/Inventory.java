/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Eric
 */
public class Inventory {
    
    //Members
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static int nextPartID = 0;
    private static int nextProductID = 0;
    
    //Methods
    
    public static int getNextPartID() {
        nextPartID += 1;
        return nextPartID;
    }
    
    public static int getNextProductID() {
        nextProductID += 1;
        return nextProductID;
    }
    
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }
    
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }
    
    public static Part lookupPart(Integer partID) {
        ObservableList<Part> allParts = getAllParts();
        for(int i = 0; i < allParts.size(); i++) {
            Part partCompare = allParts.get(i);
            if(partCompare.getId() == partID) {
                return partCompare;
            }
        }
        return null;  
    }
    
    public static Product lookupProduct(Integer productID) {
        ObservableList<Product> allProducts = getAllProducts();
        for(int i = 0; i < allProducts.size(); i++) {
            Product productCompare = allProducts.get(i);
            if(productCompare.getId() == productID) {
                return productCompare;
            }
        }
        return null;
    }
    
    public static ObservableList<Part> lookupPart(String partialName) {
        ObservableList<Part> partNames = FXCollections.observableArrayList();
        
        for(Part partSearch : getAllParts()) {
            if(partSearch.getName().contains(partialName)) {
                partNames.add(partSearch);
            }
        } 
        return partNames;
    }
    
    public static ObservableList<Product> lookupProduct(String partialName) {
        ObservableList<Product> productNames = FXCollections.observableArrayList();
        
        for(Product productSearch : getAllProducts()) {
            if(productSearch.getName().contains(partialName)) {
                productNames.add(productSearch);
            }
        }
        
        return productNames;
    }
    
    public static void updatePart(Integer partID, Part selectedPart){
        
    }
    
    public static void updateProduct(Integer partID, Product newProduct){
        
    }
    
    public static boolean deletePart(Part selectedPart){
        //FIX THIS
        boolean deleteConfirm = false;
        try {
            Inventory.getAllParts().remove(selectedPart);
            deleteConfirm = true;
            return deleteConfirm;
        }
        catch(NullPointerException exception) {
            return deleteConfirm;
        }
        
    }
    
    public static boolean deleteProduct(Product selectedProduct){
        //Add Code Here
        //Ask if they want to delete
        //if OK, delete
        //if no, cancel
        boolean deleteConfirm = false;
        
        return deleteConfirm;
    }
    
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
   
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    
}
