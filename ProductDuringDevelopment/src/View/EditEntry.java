/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import Controller.AtlantisFuel;
import static View.HomePage.username;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static View.HomePage.username;
import static View.ViewEntries.fuelID;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author yessi
 */
public class EditEntry extends javax.swing.JFrame {
    public static int fuelAmount;
    public static float cost;
    public static int monthEnterred;
    public static String month;


    public EditEntry(long fuelID) {
        initComponents();

        try {
            AtlantisFuel.deserialiseUser();
        } catch (IOException ex) {
            Logger.getLogger(EditEntry.class.getName()).log(Level.SEVERE, null, ex);
        }

        int currentDay = 1;
        while(currentDay < 32){
            jComboBox3.addItem(Integer.toString(currentDay));
            currentDay = currentDay + 1;
        }

        jComboBox2.addItem("Jan");
        jComboBox2.addItem("Feb");
        jComboBox2.addItem("Mar");
        jComboBox2.addItem("Apr");
        jComboBox2.addItem("May");
        jComboBox2.addItem("Jun");
        jComboBox2.addItem("Jul");
        jComboBox2.addItem("Aug");
        jComboBox2.addItem("Sep");
        jComboBox2.addItem("Oct");
        jComboBox2.addItem("Nov");
        jComboBox2.addItem("Dec");
        
        int year = Calendar.getInstance().get(Calendar.YEAR);
        jComboBox1.addItem(Integer.toString(year-1));
        jComboBox1.addItem(Integer.toString(year));
        
        jComboBox4.addItem("E10");
        jComboBox4.addItem("Unleaded");
        jComboBox4.addItem("Leaded");
        jComboBox4.addItem("Diesel");
        jComboBox4.addItem("Other");
        
        jComboBox5.addItem("Other vehicle");
        for(int g=0; g < AtlantisFuel.vehicles.size();g++){
            if(AtlantisFuel.vehicles.get(g).getUsername().equals(username)){
                jComboBox5.addItem(AtlantisFuel.vehicles.get(g).getNameOfVehicle());
            }
        }


        for(int i = 0; i < AtlantisFuel.fuelConsumption.size(); i++){
            if(AtlantisFuel.fuelConsumption.get(i).getFuelID() == fuelID){
                jComboBox3.setSelectedItem(AtlantisFuel.fuelConsumption.get(i).getDateEnterred().getDay() - 2);

            String sub = String.valueOf(AtlantisFuel.fuelConsumption.get(i).getDateEnterred());
            jComboBox2.setSelectedItem(sub.substring(4,7));
            jComboBox1.setSelectedItem(AtlantisFuel.fuelConsumption.get(i).getDateEnterred().getYear() + 1900);
            jComboBox4.setSelectedItem(AtlantisFuel.fuelConsumption.get(i).getFuelType());
            jComboBox5.setSelectedItem(AtlantisFuel.fuelConsumption.get(i).getVehicleName());
            jTextField1.setText(String.valueOf(AtlantisFuel.fuelConsumption.get(i).getFuelAmount()));
            jTextField2.setText(String.valueOf(AtlantisFuel.fuelConsumption.get(i).getCost()));
                
            }
        }
    }
        
        public void editEntryButton(){
        //check if amount enterred numbers only - 1
        boolean checkAmount = false;
        boolean checkCost = false;

        String amount1 = jTextField1.getText();
        /*verifies that amount is acceptable as integer*/
        if(amount1.matches("^[0-9]*$")){
            fuelAmount = Integer.valueOf(amount1);
        }
        else{
            checkAmount = true;
        }

        //check if cost enterred numbers and dot only - 2
        String cost1 = jTextField2.getText();
        if(cost1.matches("^[0-9]*$") || cost1.contains(".")){
            cost = Float.valueOf(cost1);
        }
        else{
            checkCost = true;
        }

        int monthEnterred = 0;
        String month = "1";

        if(checkAmount == true && checkCost == true){
            jLabel7.setText("Please correctly enter amount and cost\n"
                + "Amount can only have numbers\n"
                + "Cost can only have numbers and .");
        }
        else if(checkAmount == true && checkCost == false){
            jLabel7.setText("Please correctly enter the amount\n"
                + "The value can only have numbers");
        }
        else if(checkCost == true && checkAmount == false){
            jLabel7.setText("Please correclty enter the cost\n"
                + "The value can only have numbers and .");
        }
        else{
            String day1 = (String)jComboBox3.getSelectedItem();
            if(day1.length() == 1){
                //if the date is a single number, it adds zero at the front
                day1 = "0" + day1;
            }

            String month1 = (String)jComboBox2.getSelectedItem();
            /*converts month enterred to integer to save to fuel object as date variable*/
            switch (month1) {
                case "Jan":
                monthEnterred = 1;
                month = "01";
                break;
                case "Feb":
                monthEnterred = 2;
                month = "02";
                break;
                case "Mar":
                monthEnterred = 3;
                month = "03";
                break;
                case "Apr":
                monthEnterred = 4;
                month = "04";
                break;
                case "May":
                monthEnterred = 5;
                month = "05";
                break;
                case "Jun":
                monthEnterred = 6;
                month = "06";
                break;
                case "Jul":
                monthEnterred = 7;
                month = "07";
                break;
                case "Aug":
                monthEnterred = 8;
                month = "08";
                break;
                case "Sep":
                monthEnterred = 9;
                month = "09";
                break;
                case "Oct":
                monthEnterred = 10;
                month = "10";
                break;
                case "Nov":
                monthEnterred = 11;
                month = "11";
                break;
                case "Dec":
                monthEnterred = 12;
                month = "12";
                break;
                default:
                break;
            }

            String year1 = (String)jComboBox1.getSelectedItem();

            String fuelType = (String)jComboBox4.getSelectedItem();

            String d1 = day1 + "-" + month + "-" + year1;
            Date dateEnterred = null;
            try {
                dateEnterred = new SimpleDateFormat("dd-MM-yyyy").parse(d1);
                //to find day use getDay() - 2
                //to find year yse getYear() + 1900
                System.out.println(dateEnterred);
            } catch (ParseException ex) {
                Logger.getLogger(AddEntry.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String vehicleName = (String)jComboBox5.getSelectedItem();

            AtlantisFuel.editEntry(fuelID, username, fuelType, fuelAmount, cost, dateEnterred, vehicleName);
            
        }
        
        AtlantisFuel.serialiseFuel();
        AtlantisFuel.serialiseVehicles();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(8, 54, 193));
        jPanel11.setPreferredSize(new java.awt.Dimension(150, 800));

        jButton24.setBackground(new java.awt.Color(239, 188, 79));
        jButton24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton24.setText("Add A Fuel Entry");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton25.setBackground(new java.awt.Color(239, 188, 79));
        jButton25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton25.setText("Add Your Vehicles");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jButton26.setBackground(new java.awt.Color(8, 54, 193));
        jButton26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/atlantis_fuel.png"))); // NOI18N
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(139, 139, 139))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setBackground(new java.awt.Color(239, 240, 245));
        jLabel3.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jLabel3.setText("Year");

        jComboBox4.setBackground(new java.awt.Color(239, 240, 245));
        jComboBox4.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jComboBox4.setForeground(new java.awt.Color(12, 12, 20));

        jLabel4.setBackground(new java.awt.Color(239, 240, 245));
        jLabel4.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jLabel4.setText("Cost");

        jButton6.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jButton6.setText("Delete Entry");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(239, 240, 245));
        jLabel5.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jLabel5.setText("Day");

        jLabel8.setBackground(new java.awt.Color(239, 240, 245));
        jLabel8.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jLabel8.setText("Vehicle");

        jTextField1.setBackground(new java.awt.Color(239, 240, 245));
        jTextField1.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(12, 12, 20)));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jComboBox5.setBackground(new java.awt.Color(239, 240, 245));
        jComboBox5.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jComboBox5.setForeground(new java.awt.Color(12, 12, 20));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(239, 240, 245));
        jLabel6.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jLabel6.setText("Amount ");

        jTextField2.setBackground(new java.awt.Color(239, 240, 245));
        jTextField2.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jTextField2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(12, 12, 20)));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jButton1.setText("Edit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setBackground(new java.awt.Color(239, 240, 245));
        jComboBox1.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(12, 12, 20));

        jLabel7.setText(".");

        jLabel1.setBackground(new java.awt.Color(239, 240, 245));
        jLabel1.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jLabel1.setText("Month");

        jComboBox2.setBackground(new java.awt.Color(239, 240, 245));
        jComboBox2.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(12, 12, 20));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(239, 240, 245));
        jLabel2.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jLabel2.setText("Type");

        jComboBox3.setBackground(new java.awt.Color(239, 240, 245));
        jComboBox3.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jComboBox3.setForeground(new java.awt.Color(12, 12, 20));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(132, 132, 132)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(198, 198, 198))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(jLabel7)
                .addGap(86, 86, 86))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 818, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        new AddEntry(username).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:
        new AddVehicle(username).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        // TODO add your handling code here:
        new HomePage(username).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // delete button
        try {
            AtlantisFuel.deserialiseFuel();
        } catch (IOException ex) {
            Logger.getLogger(EditEntry.class.getName()).log(Level.SEVERE, null, ex);
        }

        for(int count = 0; count < AtlantisFuel.fuelConsumption.size(); count++){
            if(AtlantisFuel.fuelConsumption.get(count).getFuelID() == fuelID){
                AtlantisFuel.fuelConsumption.remove(count);
                break;
            }
        }

        AtlantisFuel.serialiseFuel();
        this.setVisible(false);
        new ViewEntries().setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
        //amount
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        // shows user's vehicles.
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
        //cost
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        editEntryButton();
        this.setVisible(false);
        new ViewEntries().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EditEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditEntry(fuelID).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton6;
    public static javax.swing.JComboBox<String> jComboBox1;
    public static javax.swing.JComboBox<String> jComboBox2;
    public static javax.swing.JComboBox<String> jComboBox3;
    public static javax.swing.JComboBox<String> jComboBox4;
    public static javax.swing.JComboBox<String> jComboBox5;
    public static javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel2;
    public static javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel jLabel4;
    public static javax.swing.JLabel jLabel5;
    public static javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    public static javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
