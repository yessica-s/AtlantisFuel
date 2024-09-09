package Model;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;

public class FuelConsumption implements Serializable{

    public FuelConsumption(long fuelID, String username, String fuelType, int fuelAmount, float cost, Date dateEnterred, String vehicleName) {
        this.fuelID = fuelID;
        this.username = username;
        this.fuelType = fuelType;
        this.fuelAmount = fuelAmount;
        this.cost = cost;
        this.dateEnterred = dateEnterred;
        this.vehicleName = vehicleName;
    }
    
    private ArrayList[] fuelConsumption;
    private long fuelID;
    private String username;
    private String fuelType;
    private int fuelAmount;
    private float cost;
    private Date dateEnterred;
    private String vehicleName;



    public ArrayList[] getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(ArrayList[] fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getFuelAmount() {
        return fuelAmount;
    }

    public void setFuelAmount(int fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public Date getDateEnterred() {
        return dateEnterred;
    }

    public void setDateEnterred(Date dateEnterred) {
        this.dateEnterred = dateEnterred;
    }

    public long getFuelID() {
        return fuelID;
    }

    public void setFuelID(long fuelID) {
        this.fuelID = fuelID;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }
    
}
