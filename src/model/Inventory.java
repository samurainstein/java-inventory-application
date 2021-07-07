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
    
    public static void addPart(Part newPart){

    }
    
    //addProduct
    
    //lookupPart ID
    
    //lookupProduct ID
    
    public static ObservableList<Part> lookupPart(String partialName) {
        ObservableList<Part> partNames = FXCollections.observableArrayList();
        
        
        return partNames;
    }
    
    //lookupProduct Name
    
    //updatePart
    
    //updateProduct
    
    //public static boolean deletePart(Part selectedPart){
    //    
    //}
    
    //deleteProduct
    
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
   
    //getAllProducts

    
}
