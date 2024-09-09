/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import View.*;
import java.util.ArrayList;

/**
 *
 * @author yessi
 */
public class CostsPerYear {
    
    public CostsPerYear(String year, float cost) {
        this.year = year;
        this.cost = cost;
        
    }
    
    private ArrayList[] costsPerYear;
    private String year;
    private float cost;

    public ArrayList[] getCostsPerYear() {
        return costsPerYear;
    }

    public void setCostsPerYear(ArrayList[] costsPerYear) {
        this.costsPerYear = costsPerYear;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
    
}
