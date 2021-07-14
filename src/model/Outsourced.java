/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**This class extends the Part class, and defines and modifies parts that are outsourced..
 * @author Eric
 */
public class Outsourced extends Part {
    
    //Members
    private String companyName;
    
    //Methods
    /**
     * Constructor for Outsourced Part objects. 
     * 
     * @param id ID number to be assigned to part
     * @param name Name to be assigned to part
     * @param price Price to be assigned to part
     * @param stock Stock amount to be assigned to part
     * @param min Minimum stock amount to be assigned to part
     * @param max Maximum stock amount to be assigned to part
     * @param companyName Company Name to be assigned to part
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }
    
    /**Method for setting the company name to a part. 
     * @param companyName Company name to set to part
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    /**Method for getting the company name from a part. 
     * @return Returns a company name
     */
    public String getCompanyName() {
        return companyName;
    }
}
