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
    //private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    
    //Methods
    
    /*
    Do I need a constructor?
    How do I initialize test data?
    static  {
        init();
    }
    
    private static void init() {
        allParts.add(new Inventory());
    }
    */
    
    public static void addPart(Part newPart){

    }
    
    /*public static void addProduct(Product newProduct){

    }*/
    
    public static void lookupPart(Integer partID) {
        
    }
    
    public static void lookupProduct(Integer productID) {
        
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
    
    /*public static ObservableList<Product> lookupProduct(String partialName) {
        ObservableList<Product> productNames = FXCollections.observableArrayList();
        
        
        return productNames;
    }*/
    
    public static void updatePart(Integer partID, Part selectedPart){
        
    }
    
    /*public static void updateProduct(Integer partID, Product newProduct){
        
    }*/
    
    /*public static boolean deletePart(Part selectedPart){
        
    }*/
    
    /*public static boolean deleteProduct(Product selectedProduct){
        
    }*/
    
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
   
    /*public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }*/

    
}
