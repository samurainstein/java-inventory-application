/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *This class contains all of the items in the inventory program, and the methods for 
 * creating, updating, retrieving, and deleting those items.
 * @author Eric
 */
public class Inventory {
    
    //Members
    /**Observable list of all of the parts in the inventory program.*/
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    /**Observable list of all of the products in the inventory program.*/
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    /**A variable used to store the next part ID that should be used when the next Part is created*/
    private static int nextPartID = 0;
    /**A variable used to store the next product ID that should be used when the next Part is created*/
    private static int nextProductID = 0;
    
    //Methods
    /**Method for auto-generating the next part ID. 
     * @return Returns the next sequential part ID.
     */
    public static int getNextPartID() {
        nextPartID += 1;
        return nextPartID;
    }
    
    /**Method for auto-generating the next product ID. 
     * @return Returns the next sequential product ID.
     */
    public static int getNextProductID() {
        nextProductID += 1;
        return nextProductID;
    }
    
    /**Method for adding a new part to the allParts list. 
     * @param newPart A new part object 
     */
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }
    
    /**Method for adding a new part to the allParts list. 
     * @param newProduct A new part object 
     */
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }
    
    /**Method for looking up a part in the table by part ID. 
     * @param partID A part ID passed from search field
     * @return Returns a found part or null
     */
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
    
    /**Method for looking up a product in the table by product ID. 
     * @param productID A product ID passed from search the field
     * @return Returns a found product or null
     */
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
    
    /**Method for looking up a part in the table by a string. 
     * @param partialName A string passed from search the field
     * @return Returns a found part, list of parts, or null
     */
    public static ObservableList<Part> lookupPart(String partialName) {
        ObservableList<Part> partNames = FXCollections.observableArrayList();
        
        for(Part partSearch : getAllParts()) {
            if(partSearch.getName().contains(partialName)) {
                partNames.add(partSearch);
            }
        } 
        return partNames;
    }
    
    /**Method for looking up a product in the table by a string. 
     * @param partialName A string passed from search the field
     * @return Returns a found product, list of products, or null
     */
    public static ObservableList<Product> lookupProduct(String partialName) {
        ObservableList<Product> productNames = FXCollections.observableArrayList();
        
        for(Product productSearch : getAllProducts()) {
            if(productSearch.getName().contains(partialName)) {
                productNames.add(productSearch);
            }
        }
        
        return productNames;
    }
    
    /**Method for updating an existing part. 
     * @param index Index of existing part that will be updated
     * @param selectedPart Part object with updated fields to copy to the matching existing part
     */
    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }
    
    /**Method for updating an existing product. 
     * @param index Index of existing product that will be updated
     * @param newProduct Product object with updated fields to copy to the matching existing product
     */
    public static void updateProduct(int index, Product newProduct){
        allProducts.set(index, newProduct);
    }
    
    /**Method for deleting a part. 
     * @param selectedPart The part to be deleted
     * @return Returns a boolean value verifying if the part was deleted
     */
    public static boolean deletePart(Part selectedPart){
        return allParts.remove(selectedPart);
    }
    
    /**Method for deleting a product. 
     * @param selectedProduct The product to be deleted
     * @return Returns a boolean value verifying if the product was deleted
     */
    public static boolean deleteProduct(Product selectedProduct){
        return allProducts.remove(selectedProduct);
    }
    
    /**Method for returning all of the parts in the inventory. 
     * @return Returns a list of all parts
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
   
    /**Method for returning all of the products in the inventory. 
     * @return Returns a list of all products
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    
}
