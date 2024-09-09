package Controller;
import Model.*;
import View.*;
import static View.ViewEntries.jTable1;
import static View.ViewFuelTypes.Diesel;
import static View.ViewFuelTypes.E10;
import static View.ViewFuelTypes.Leaded;
import static View.ViewFuelTypes.Other;
import static View.ViewFuelTypes.Total;
import static View.ViewFuelTypes.Unleaded;
import static View.ViewFuelTypes.month1;
//import static View.ViewFuelTypes.type;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

//imports for serialization & deserialisation
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream; 
import java.util.logging.Level;
import java.util.logging.Logger;


public class AtlantisFuel {
    
    //user arrayList: username, password, index number
    public static ArrayList<Users> users = new ArrayList<>();
    public static ArrayList<Vehicles> vehicles = new ArrayList<>();
    public static ArrayList<FuelConsumption> fuelConsumption = new ArrayList<>();
    
    //Signup page
    public static boolean addUser(String username, String password, String firstname,
            String surname, String email, int phone){
        try {
            deserialiseUser();
        } catch (IOException ex) {
            Logger.getLogger(AtlantisFuel.class.getName()).log(Level.SEVERE, null, ex);
        }
        users.add(new Users(username, password, firstname, surname, email, phone));
        serialiseUser();
        return true;

    }
    
    //EditProfile page
    public static boolean editUser(String username, String password, String firstname,
            String surname, String email, int phone, int currentUser){
        try {
            deserialiseUser();
        } catch (IOException ex) {
            Logger.getLogger(AtlantisFuel.class.getName()).log(Level.SEVERE, null, ex);
        }
        users.get(currentUser).setUsername(username);
        users.get(currentUser).setPassword(password);
        users.get(currentUser).setFirstname(firstname);
        users.get(currentUser).setSurname(surname);
        users.get(currentUser).setEmail(email);
        users.get(currentUser).setPhone(phone);
        serialiseUser();
        
        return true;
    }
    
    public static int findUser(String username){
        
        //String username = username;
        
        try {
            deserialiseUser();
        } catch (IOException ex) {
            Logger.getLogger(AtlantisFuel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        boolean find = false;
        int m = 0;
        int userIndex = 0;
        
        while(m < users.size() && find == false){
            if(username.equals(users.get(m).getUsername())){
                userIndex = m;
                find = true;
            }
            else{
                m=m+1;
            }
        }
        serialiseUser();
        return userIndex;
    }
    
    //AddEntry page
    public static boolean addFuelEntry(long fuelID, String username, String fuelType, int fuelAmount, float cost, Date dateEnterred, String vehicleName){
        try {
            deserialiseFuel();
        } catch (IOException ex) {
            Logger.getLogger(AtlantisFuel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        fuelConsumption.add(new FuelConsumption(fuelID, username, fuelType, fuelAmount, cost, dateEnterred, vehicleName));
        serialiseFuel();
        return true;
    }
    
    public static boolean editEntry(long fuelID, String username, String fuelType, int fuelAmount, float cost, Date dateEnterred, String vehicleName){
        try {
            deserialiseFuel();
        } catch (IOException ex) {
            Logger.getLogger(AtlantisFuel.class.getName()).log(Level.SEVERE, null, ex);
        }
        int number = 0;
        for(int i = 0; i < AtlantisFuel.fuelConsumption.size(); i++){
            if(AtlantisFuel.fuelConsumption.get(i).getFuelID() == fuelID){
                number = i;
            }
        }
        
        fuelConsumption.get(number).setFuelType(fuelType);
        fuelConsumption.get(number).setFuelAmount(fuelAmount);
        fuelConsumption.get(number).setCost(cost);
        fuelConsumption.get(number).setDateEnterred(dateEnterred);
        
        serialiseFuel();
        return true;
    }

    //AddVehicle page
    public static boolean addVehicle(String username, String nameOfVehicle, int dayBought, int monthBought, int yearBought, boolean fourWheelDrive){
        try {
            deserialiseVehicles();
        } catch (IOException ex) {
            Logger.getLogger(AtlantisFuel.class.getName()).log(Level.SEVERE, null, ex);
        }
        vehicles.add(new Vehicles(username, nameOfVehicle, dayBought, monthBought, yearBought, fourWheelDrive));
        serialiseVehicles();
        return true;
    }
    
    //EditVehicle page
    public static boolean editVehicle(int index, String username, String nameOfVehicle, int dayBought, int monthBought, int yearBought, boolean fourWheelDrive){
        try {
            deserialiseVehicles();
        } catch (IOException ex) {
            Logger.getLogger(AtlantisFuel.class.getName()).log(Level.SEVERE, null, ex);
        }
        vehicles.get(index).setUsername(username);
        vehicles.get(index).setNameOfVehicle(nameOfVehicle);
        vehicles.get(index).setDayBought(dayBought);
        vehicles.get(index).setMonthBought(monthBought);
        vehicles.get(index).setYearBought(yearBought);
        vehicles.get(index).setFourWheelDrive(fourWheelDrive);
        serialiseVehicles();
        return true;
    }
    
    //for the add fuel entry page (day) & add vehicle: - only works for add entry
    public static boolean displayDays(){
        int currentDay = 1;
        while(currentDay < 32){
            AddEntry.jComboBox3.addItem(Integer.toString(currentDay));
            currentDay = currentDay + 1;
        }
        return true;
    }
    
    //for the add fuel entry page (month) & add vehicle:
    public static boolean displayMonths(){
        AddEntry.jComboBox2.addItem("Jan");
        AddEntry.jComboBox2.addItem("Feb");
        AddEntry.jComboBox2.addItem("Mar");
        AddEntry.jComboBox2.addItem("Apr");
        AddEntry.jComboBox2.addItem("May");
        AddEntry.jComboBox2.addItem("Jun");
        AddEntry.jComboBox2.addItem("Jul");
        AddEntry.jComboBox2.addItem("Aug");
        AddEntry.jComboBox2.addItem("Sep");
        AddEntry.jComboBox2.addItem("Oct");
        AddEntry.jComboBox2.addItem("Nov");
        AddEntry.jComboBox2.addItem("Dec");
        return true;
    }
    
    //for the add fuel entry page (year) & add vehicle:
    public static boolean displayYear(){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for(int y = 0; y < 20; y++){
            AddEntry.jComboBox1.addItem(Integer.toString(year-y));
        }
        return true;
    }
    
    //for the add fuel entry page (type of fuel)
    public static boolean displayFuelTypes(){
        AddEntry.jComboBox4.addItem("E10");
        AddEntry.jComboBox4.addItem("Unleaded");
        AddEntry.jComboBox4.addItem("Leaded");
        AddEntry.jComboBox4.addItem("Diesel");
        AddEntry.jComboBox4.addItem("Other");
        return true;
    }
    
    //find the index of the vehicle in question
    public static int findVehicle(String nameOfVehicle, String username){
        try {
            deserialiseVehicles();
        } catch (IOException ex) {
            Logger.getLogger(AtlantisFuel.class.getName()).log(Level.SEVERE, null, ex);
        }
        int n = 0;
        boolean find = false;
        int currentVehicleIndex = 0;
        while(find == false && n < vehicles.size()){
            //username needs to be checked too
            if(vehicles.get(n).getNameOfVehicle().equals(nameOfVehicle) && vehicles.get(n).getUsername().equals(username)){
                currentVehicleIndex = n;
                find = true;
            }
            else{
                n = n + 1;
            }
        }
        serialiseVehicles();
        return currentVehicleIndex;
    }
    
    //FROM THE VIEWENTRIES PAGE:
    
    public static void addRowToJTable(){
        
        try {
            deserialiseFuel();
        } catch (IOException ex) {
            Logger.getLogger(AtlantisFuel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*the following code is adapted from 
        https://1bestcsharp.blogspot.com/2016/03/java-populate-jtable-from-arraylist.html
        */
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        
        //should the following loop be here or after the instantiation of the object?

        for(int i = 0; i < AtlantisFuel.fuelConsumption.size(); i++){
            if(AtlantisFuel.fuelConsumption.get(i).getUsername().equals(HomePage.username)){
                Object rowData[] = new Object[7];
                rowData[0] = String.valueOf((AtlantisFuel.fuelConsumption.get(i).getFuelID())); 
                
                Date var = AtlantisFuel.fuelConsumption.get(i).getDateEnterred();
                String one = String.valueOf(var);
                //System.out.println(one.substring(9, 10));
                rowData[1] = one.substring(8,10);
                
//                int var = AtlantisFuel.fuelConsumption.get(i).getDateEnterred().getDay();
//                System.out.println(var);
//                System.out.println(var-2);
//                rowData[1] = String.valueOf((var)-4);
                //rowData[1] = String.valueOf(((AtlantisFuel.fuelConsumption.get(i).getDateEnterred()).getDay())-2);
                rowData[2] = String.valueOf((AtlantisFuel.fuelConsumption.get(i).getDateEnterred()).getMonth()+1);
                rowData[3] = String.valueOf((AtlantisFuel.fuelConsumption.get(i).getDateEnterred()).getYear()+1900);
                rowData[4] = AtlantisFuel.fuelConsumption.get(i).getFuelType();
                rowData[5] = String.valueOf(AtlantisFuel.fuelConsumption.get(i).getFuelAmount());
                rowData[6] = String.valueOf(AtlantisFuel.fuelConsumption.get(i).getCost());

                model.addRow(rowData);
            }
            
            /*ORIGINAL CODE:
            Object rowData[] = new Object[7];
        for(int i = 0; i < AtlantisFuel.fuelConsumption.size(); i++){
           
            //it adds all the entries bro...not only this users 
            rowData[0] = String.valueOf((AtlantisFuel.fuelConsumption.get(i).getFuelID())); 
            rowData[1] = String.valueOf((AtlantisFuel.fuelConsumption.get(i).getDateEnterred()).getDay() - 2);
            rowData[2] = String.valueOf((AtlantisFuel.fuelConsumption.get(i).getDateEnterred()).getMonth() + 1);
            rowData[3] = String.valueOf((AtlantisFuel.fuelConsumption.get(i).getDateEnterred()).getYear() + 1900);
            rowData[4] = AtlantisFuel.fuelConsumption.get(i).getFuelType();
            rowData[5] = String.valueOf(AtlantisFuel.fuelConsumption.get(i).getFuelAmount());
            rowData[6] = String.valueOf(AtlantisFuel.fuelConsumption.get(i).getCost());
            
            model.addRow(rowData);
            */
        }
        
        serialiseFuel();
                
    }
//    
//    public static void addRowToJTable(){
//        try {
//            deserialiseFuel();
//        } catch (IOException ex) {
//            Logger.getLogger(AtlantisFuel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        /*the following code is adapted from 
//        https://1bestcsharp.blogspot.com/2016/03/java-populate-jtable-from-arraylist.html
//        */
//        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
//        model.setRowCount(0);
//        
//        //should the following loop be here or after the instantiation of the object?
//
//        for(int i = 0; i < AtlantisFuel.fuelConsumption.size(); i++){
//            if(AtlantisFuel.fuelConsumption.get(i).getUsername().equals(HomePage.username)){
//                Object rowData[] = new Object[7];
//                rowData[0] = String.valueOf((AtlantisFuel.fuelConsumption.get(i).getFuelID())); 
//                
//                Date var = AtlantisFuel.fuelConsumption.get(i).getDateEnterred();
//                String one = String.valueOf(var);
//                //System.out.println(one.substring(9, 10));
//                rowData[1] = one.substring(8,10);
//                
////                int var = AtlantisFuel.fuelConsumption.get(i).getDateEnterred().getDay();
////                System.out.println(var);
////                System.out.println(var-2);
////                rowData[1] = String.valueOf((var)-4);
//                //rowData[1] = String.valueOf(((AtlantisFuel.fuelConsumption.get(i).getDateEnterred()).getDay())-2);
//                rowData[2] = String.valueOf((AtlantisFuel.fuelConsumption.get(i).getDateEnterred()).getMonth()+1);
//                rowData[3] = String.valueOf((AtlantisFuel.fuelConsumption.get(i).getDateEnterred()).getYear()+1900);
//                rowData[4] = AtlantisFuel.fuelConsumption.get(i).getFuelType();
//                rowData[5] = String.valueOf(AtlantisFuel.fuelConsumption.get(i).getFuelAmount());
//                rowData[6] = String.valueOf(AtlantisFuel.fuelConsumption.get(i).getCost());
//
//                model.addRow(rowData);
//            }
//        }
//        serialiseFuel();        
//    }
    
    //for ViewFuelTypes class
    
//    public static void chartThisMonth(){
//
////        if(jComboBox1.getSelectedItem().equals("this month")){
//            String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
//            System.out.println(date);
//            month1 = date.substring(3,5);
//            
//        switch (month1) {
//            case "01":
//                month1 = "1";
//                System.out.println("month converted");
//                break;
//            case "02":
//                month1 = "2";
//                System.out.println("month converted");
//                break;
//            case "03":
//                month1 = "3";
//                System.out.println("month converted");
//                break;
//            case "04":
//                month1 = "4";
//                System.out.println("month converted");
//                break;
//            case "05":
//                month1 = "5";
//                System.out.println("month converted");
//                break;
//            case "06":
//                month1 = "6";
//                System.out.println("month converted");
//                break;
//            case "07":
//                month1 = "7";
//                System.out.println("month converted");
//                break;
//            case "08":
//                month1 = "8";
//                System.out.println("month converted");
//                break;
//            case "09":
//                month1 = "9";
//                System.out.println("month converted");
//                break;
//            default:
//                break;
//        }
//        
//        try {
//            AtlantisFuel.deserialiseFuel();
//        } catch (IOException ex) {
//            Logger.getLogger(AtlantisFuel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//            
//            int j = 0;
//            int month = Integer.valueOf(month1);
//            while(j < AtlantisFuel.fuelConsumption.size()){
//                if((AtlantisFuel.fuelConsumption.get(j).getUsername().equals(HomePage.username))
//                        && (AtlantisFuel.fuelConsumption.get(j).getDateEnterred().getMonth() == month)
//                        && (AtlantisFuel.fuelConsumption.get(j).getDateEnterred().getYear() + 1900) == Integer.valueOf(date.substring(6))){
//                    System.out.println("loop begun");
//
//                    switch (AtlantisFuel.fuelConsumption.get(j).getFuelType()) {
//                        case "E10":
//                            E10 = E10 + 1;
//                            if(!(type.contains("E10"))){
//                                type.add("E10");
//                                System.out.println("added to type");
//                            }
//          
//                            break;
//                        case "Diesel":
//                            Diesel = Diesel + 1;
//                            if(!(type.contains("Diesel"))){
//                                type.add("Diesel");
//                                System.out.println("added to type");
//                            }
//                            break;
//                        case "Unleaded":
//                            Unleaded = Unleaded + 1;
//                            if(!(type.contains("Unleaded"))){
//                                type.add("Unleaded");
//                                System.out.println("added to type");
//                            }
//                            break;
//                            
//                        case "Leaded":
//                            Leaded = Leaded + 1;
//                            if(!(type.contains("Leaded"))){
//                                type.add("Leaded");
//                                System.out.println("added to type");
//                            }
//                            break;
//                        case "Other":
//                            Other = Other + 1;
//                            if(!(type.contains("Other"))){
//                                type.add("Other");
//                                System.out.println("added to type");
//                            }
//                            break;
//                        default:
//                            break;
//                    }
//                }
//                j = j + 1;
//            }
//            
//            Total = E10 + Diesel + Unleaded + Leaded + Other;
//            System.out.println("totalled");
//            
//            serialiseFuel();
//    }
    
    public static void serialiseUser(){
        try { /*the try-catch is used to avoid the program stalling and crashing
            if something goes wrong. it will stop the application from crashing
            if an error occurs in the run-time of the code within*/
            FileOutputStream fileOut = new FileOutputStream("users.ser");
            /*instantiated the text file in which the data will be stored*/
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(users);//the users arraylist is the object to be serialised
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in users.ser");

        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    
    public static void serialiseVehicles(){
        try {
            FileOutputStream fileOut = new FileOutputStream("vehicles.ser");
            //data saved in this file
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(vehicles);//the vehicles arraylist is the object to be serialised
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in vehicles.ser");

        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    
    public static void serialiseFuel(){
        try {
            FileOutputStream fileOut = new FileOutputStream("fuel.ser");
            //data saved in this file
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(fuelConsumption);//the fuelConsumption arraylist is the object to be serialised
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in fuel.ser");

        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    
    public static void deserialiseUser() throws IOException {
        try {/*once again a try catch statement is used to prevent the program
            from crashing should an exception to the code occur and prevent it 
            from running correctly.*/
            //searches for the serialised file:
            FileInputStream fileIn = new FileInputStream("users.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            //transfers the data back to its normal form
            users = (ArrayList<Users>) in.readObject();
            in.close();
            fileIn.close();

        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("User class not found");
            /*if an exception occured it will let the user know that the 
            serialised file could not be found*/
            c.printStackTrace();
            return;
        }
//        System.out.println("Deserializing User...");
//        for(int i = 0; i < users.size(); i++){//will run uptil userList's size
//            /*the following statements show the file has been deserialised and 
//            the data has been converted back to its original state from the bytestream*/
//            System.out.println("First Name of User: " + users.get(i).getFirstname());
//            System.out.println("Last Name of User: " + users.get(i).getSurname());
//        }
    }
    
    public static void deserialiseVehicles() throws IOException {
        try {
            //looks for the serialised file
            FileInputStream fileIn = new FileInputStream("vehicles.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            vehicles = (ArrayList<Vehicles>) in.readObject();
            in.close();
            fileIn.close();

        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Vehicle class not found");
            c.printStackTrace();
            return;
        }
        System.out.println("Deserializing Vehicle...");
        for(int i = 0; i < vehicles.size(); i++){//will run uptil userList's size
            System.out.println("Name of Vehicle: " + vehicles.get(i).getNameOfVehicle());
        }
    }
    
    public static void deserialiseFuel() throws IOException {
        try {
            //looks for the serialised file
            FileInputStream fileIn = new FileInputStream("fuel.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            fuelConsumption = (ArrayList<FuelConsumption>) in.readObject();
            in.close();
            fileIn.close();

        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Fuel class not found");
            c.printStackTrace();
            return;
        }
        System.out.println("Deserializing Fuel...");
        for(int i = 0; i < fuelConsumption.size(); i++){//will run uptil userList's size
            System.out.println("Fuel Type: " + fuelConsumption.get(i).getFuelType());
        }
    }
    
    
    
    public static void main(String[] args) {
        new StartPage().setVisible(true);
        

    }

}
