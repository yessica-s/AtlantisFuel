package Model;
import java.util.ArrayList;

public class Amounts {
    
    private ArrayList[] Amounts;
    private String year;
    private int amount;
    
    public Amounts(String year, int amount) {
        this.year = year;
        this.amount = amount;
    }

    public ArrayList[] getAmounts() {
        return Amounts;
    }

    public void setAmounts(ArrayList[] Amounts) {
        this.Amounts = Amounts;
    }

    //accessors and mutators for year values:
    //getter for year value - used when traversing through amounts arraylist
    public String getYear() {
        return year;
    }

    //setter for year value
    public void setYear(String year) {
        this.year = year;
    }

    //accessors and mutators for amounts values
    //getter for amounts value - used when editing the amount value
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
