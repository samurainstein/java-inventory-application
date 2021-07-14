/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**This class extends the Part class, and defines and modifies parts that are created
 * in house.
 * @author Eric
 */
public class InHouse extends Part {
    
    //Members
    private int machineID;
    
    //Methods
    /**
     * Constructor for InHouse Part objects. 
     * 
     * @param id ID number to be assigned to part
     * @param name Name to be assigned to part
     * @param price Price to be assigned to part
     * @param stock Stock amount to be assigned to part
     * @param min Minimum stock amount to be assigned to part
     * @param max Maximum stock amount to be assigned to part
     * @param machineID Machine ID to be assigned to part
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
    }
    
    /**Method for setting a machine ID to a part. 
     * @param machineID Machine ID number to set to part
     */
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
    
    /**Method for getting the machine ID from a part. 
     * @return Returns a machine ID
     */
    public int getMachineID() {
        return machineID;
    }
    
}
