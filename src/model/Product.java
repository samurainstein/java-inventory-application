/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**This class defines the products that will be stored in the inventory program, and 
 * the methods for creating, updating, and retrieving those products.
 * @author Eric
 */
public class Product {
    
    //Members
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    
    //Methods
    /**
     * Constructor for product objects. 
     * 
     * @param id ID number to be assigned to product
     * @param name Name to be assigned to product
     * @param price Price to be assigned to product
     * @param stock Stock amount to be assigned to product
     * @param min Minimum stock amount to be assigned to product
     * @param max Maximum stock amount to be assigned to product
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    
    /**Method for setting the product ID. 
     * @param id ID number to set to product
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**Method for setting the product name. 
     * @param name Name to set to product
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**Method for setting the product price. 
     * @param price Price to set to product
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**Method for setting the product stock. 
     * @param stock Stock amount to set to product
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
    
    /**Method for setting the minimum stock amount. 
     * @param min Minimum stock amount to set to product
     */
    public void setMin(int min) {
        this.min = min;
    }
    
    /**Method for setting the maximum stock amount. 
     *@param max Maximum stock amount to set to product
     */
    public void setMax(int max) {
        this.max = max;
    }
    
    /**Method for getting the ID from a product. 
     * @return Returns a product ID
     */
    public int getId() {
        return id;
    }
    
    /**Method for getting the name from a product. 
     * @return Returns a product name
     */
    public String getName() {
        return name;
    }
    
    /**Method for getting the price from a product. 
     * @return Returns a product price
     */
    public double getPrice() {
        return price;
    }
    
    /**Method for getting the stock amount from a product. 
     * @return Returns a product stock amount
     */
    public int getStock() {
        return stock;
    }
    
    /**Method for getting the minimum stock amount from a product. 
     * @return Returns a product minimum stock amount
     */
    public int getMin() {
        return min;
    }
    
    /**Method for getting the maximum stock amount from a product. 
     * @return Returns a product maximum stock amount
     */
    public int getMax() {
        return max;
    }
    
    /**Method for adding an associated part to a product. 
     * @param part The part to be associated to the product
     */
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }
    
    /**Method for deleting an associated part from a product. 
     * @param selectedAssociatedPart The part to be deleted from the product
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        return associatedParts.remove(selectedAssociatedPart);
    }
    
    /**Method for retrieving all of the associated parts to a product. 
     * @return Returns a list of all of the associated parts of a product
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}
