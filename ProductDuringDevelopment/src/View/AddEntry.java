package View;
import Controller.*;
import static View.HomePage.username;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.ThreadLocalRandom;


public class AddEntry extends javax.swing.JFrame {
    public static int fuelAmount;
    public static float cost;
    public static long fuelID;
    public static int idCounter;

    public AddEntry(String username) {
        initComponents();
        jLabel7.setText("");
        AtlantisFuel.displayDays();
        AtlantisFuel.displayMonths();
        AtlantisFuel.displayYear();
        AtlantisFuel.displayFuelTypes();
        
        try {
            //jComboBox5

            AtlantisFuel.deserialiseVehicles();
        } catch (IOException ex) {
            Logger.getLogger(AddEntry.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        jComboBox5.addItem("Other vehicle");
        for(int g=0; g < AtlantisFuel.vehicles.size();g++){
            if(AtlantisFuel.vehicles.get(g).getUsername().equals(username)){
                jComboBox5.addItem(AtlantisFuel.vehicles.get(g).getNameOfVehicle());
            }
        }
        
    }
    
    public void addEntryButton(){
        //check if amount enterred numbers only - 1
        boolean checkAmount = false;
        boolean checkAmountNumber = false;
        boolean checkCost = false;
      
        String amount1 = jTextField1.getText();
        //checks fuel amount is enterred correctly and not too long for the computer to process
        if(amount1.matches("^[0-9]*$") && amount1.length() <= 2147483647){
            fuelAmount = Integer.valueOf(amount1);
        }
        else{
            checkAmount = true;
        }
        
        //check if cost enterred numbers and dot only - 2
        String cost1 = jTextField2.getText();
        if((cost1.matches("^[0-9]*$") || cost1.contains(".")) && cost1.length() <= 2147483647){
            cost = Float.valueOf(cost1);
        }
        else{
            checkCost = true;
        }
        
        int monthEnterred = 0;
        String month = "1";
        
        if(checkAmount == true && checkCost == true){
            jLabel7.setText("Please correctly enter amount and cost \n"
                    + "Amount can only have numbers\n"
                    + "Cost can only have numbers and .");
        }
        else if(checkAmount == true && checkCost == false){
            jLabel7.setText("Please correctly enter the amount \n"
                    + "The value can only have numbers");
        }
        else if(checkCost == true && checkAmount == false){
            jLabel7.setText("Please correclty enter the cost \n"
                    + "The value can only have numbers and .");
        }
        else{
            String day1 = (String)jComboBox3.getSelectedItem();
            if(day1.length() == 1){
                //if the date is a single number, it adds zero at the front
                day1 = "0" + day1;
            }
            
            String month1 = (String)jComboBox2.getSelectedItem();

            
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
            
            //fuelID = fuelID + 1;
            //finding random number and checking if random number is already used as fuelID in arraylist
            long rand = Long.valueOf(ThreadLocalRandom.current().nextInt());
            boolean duplicate = false;
            for(int r = 0; r < AtlantisFuel.fuelConsumption.size(); r++){
                if(AtlantisFuel.fuelConsumption.get(r).getFuelID() == rand){
                    duplicate = true;
                    rand = Long.valueOf(ThreadLocalRandom.current().nextInt());
                    continue;
                }
            }
            
            if(duplicate == false){
                fuelID = rand;
                String vehicleName = (String) jComboBox5.getSelectedItem();
                AtlantisFuel.addFuelEntry(fuelID, username, fuelType, fuelAmount, cost, dateEnterred, vehicleName);
                new HomePage(username).setVisible(true);
                this.setVisible(false);
            }
        }
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
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 862));

        jTextField1.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(242, 128, 9));
        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(12, 12, 20)));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(242, 128, 9));
        jTextField2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(12, 12, 20)));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(242, 128, 9));

        jComboBox2.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(242, 128, 9));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jComboBox3.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jComboBox3.setForeground(new java.awt.Color(242, 128, 9));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jComboBox4.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jComboBox4.setForeground(new java.awt.Color(242, 128, 9));

        jLabel1.setBackground(new java.awt.Color(239, 240, 245));
        jLabel1.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(242, 128, 9));
        jLabel1.setText("Month");

        jLabel2.setBackground(new java.awt.Color(239, 240, 245));
        jLabel2.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(242, 128, 9));
        jLabel2.setText("Type");

        jLabel3.setBackground(new java.awt.Color(239, 240, 245));
        jLabel3.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(242, 128, 9));
        jLabel3.setText("Year");

        jLabel4.setBackground(new java.awt.Color(239, 240, 245));
        jLabel4.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(242, 128, 9));
        jLabel4.setText("Cost");

        jLabel5.setBackground(new java.awt.Color(239, 240, 245));
        jLabel5.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(242, 128, 9));
        jLabel5.setText("Day");

        jLabel6.setBackground(new java.awt.Color(239, 240, 245));
        jLabel6.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(242, 128, 9));
        jLabel6.setText("Amount ");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(242, 128, 9));
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setText(".");

        jComboBox5.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jComboBox5.setForeground(new java.awt.Color(242, 128, 9));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(239, 240, 245));
        jLabel8.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(242, 128, 9));
        jLabel8.setText("Vehicle");

        jPanel2.setBackground(new java.awt.Color(8, 54, 193));
        jPanel2.setPreferredSize(new java.awt.Dimension(150, 800));

        jButton4.setBackground(new java.awt.Color(239, 188, 79));
        jButton4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton4.setText("Add A Fuel Entry");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(239, 188, 79));
        jButton5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton5.setText("Add Your Vehicles");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(8, 54, 193));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/atlantis_fuel.png"))); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(139, 139, 139))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(136, 136, 136)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(230, 230, 230)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(182, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(jLabel7)
                .addGap(41, 41, 41))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 796, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 796, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
        //amount
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
        //cost
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        addEntryButton(); //when addd is pressed
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        // shows user's vehicles.
        
        
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        new AddEntry(username).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        new AddVehicle(username).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        new HomePage(username).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton10ActionPerformed

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
            java.util.logging.Logger.getLogger(AddEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new AddEntry(int currentUser).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
