/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import Controller.*;
import static View.HomePage.username;
import static View.ViewVehicles.nameOfVehicle;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EditVehicle extends javax.swing.JFrame {
    
    //public static String dateMonth = null;
    public static String previousVehicleName = nameOfVehicle;
    public static int index = 0;
    public static int monthBought;

    public EditVehicle(String username, String nameOfVehicle) {
        initComponents();
        
        int currentDay = 1;
        while(currentDay < 32){
            jComboBox3.addItem(Integer.toString(currentDay));
            currentDay = currentDay + 1;
        }
        
        EditVehicle.jComboBox2.addItem("Jan");
        EditVehicle.jComboBox2.addItem("Feb");
        EditVehicle.jComboBox2.addItem("Mar");
        EditVehicle.jComboBox2.addItem("Apr");
        EditVehicle.jComboBox2.addItem("May");
        EditVehicle.jComboBox2.addItem("Jun");
        EditVehicle.jComboBox2.addItem("Jul");
        EditVehicle.jComboBox2.addItem("Aug");
        EditVehicle.jComboBox2.addItem("Sep");
        EditVehicle.jComboBox2.addItem("Oct");
        EditVehicle.jComboBox2.addItem("Nov");
        EditVehicle.jComboBox2.addItem("Dec");

        int year = Calendar.getInstance().get(Calendar.YEAR);
        for(int y = 0; y < 20; y++){
            EditVehicle.jComboBox1.addItem(Integer.toString(year-y));
        }
        
        try {
            AtlantisFuel.deserialiseVehicles();
        } catch (IOException ex) {
            Logger.getLogger(EditVehicle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //setting the information
        boolean find = false;
        int c = 0;
        while(find == false && c < AtlantisFuel.vehicles.size()){
            if(AtlantisFuel.vehicles.get(c).getUsername().equals(HomePage.username) && AtlantisFuel.vehicles.get(c).getNameOfVehicle().equals(nameOfVehicle)){        
                jTextField2.setText(nameOfVehicle);
                jComboBox3.setSelectedItem(String.valueOf(AtlantisFuel.vehicles.get(c).getDayBought()));
                jComboBox2.setSelectedItem(AtlantisFuel.vehicles.get(c).getMonthBought());
                if(AtlantisFuel.vehicles.get(c).getMonthBought() == 1){
                    jComboBox2.setSelectedItem("Jan");
                } else if(AtlantisFuel.vehicles.get(c).getMonthBought() == 2){
                    jComboBox2.setSelectedItem("Feb");
                } else if(AtlantisFuel.vehicles.get(c).getMonthBought() == 3){
                    jComboBox2.setSelectedItem("Mar");
                } else if(AtlantisFuel.vehicles.get(c).getMonthBought() == 4){
                    jComboBox2.setSelectedItem("Apr");
                } else if(AtlantisFuel.vehicles.get(c).getMonthBought() == 5){
                    jComboBox2.setSelectedItem("May");
                } else if(AtlantisFuel.vehicles.get(c).getMonthBought() == 6){
                    jComboBox2.setSelectedItem("Jun");
                } else if(AtlantisFuel.vehicles.get(c).getMonthBought() == 7){
                    jComboBox2.setSelectedItem("Jul");
                } else if(AtlantisFuel.vehicles.get(c).getMonthBought() == 8){
                    jComboBox2.setSelectedItem("Aug");
                } else if(AtlantisFuel.vehicles.get(c).getMonthBought() == 9){
                    jComboBox2.setSelectedItem("Sep");
                } else if(AtlantisFuel.vehicles.get(c).getMonthBought() == 10){
                    jComboBox2.setSelectedItem("Oct");
                } else if(AtlantisFuel.vehicles.get(c).getMonthBought() == 11){
                    jComboBox2.setSelectedItem("Nov");
                } else if(AtlantisFuel.vehicles.get(c).getMonthBought() == 12){
                    jComboBox2.setSelectedItem("Dec");
                }
                
                jComboBox1.setSelectedItem(String.valueOf(AtlantisFuel.vehicles.get(c).getYearBought()));
                if(AtlantisFuel.vehicles.get(c).isFourWheelDrive() == true){
                    jCheckBox1.setSelected(true);
                }
                find = true;
                
            } else{
                c = c+1;
            }
        }
        
//        int dateDay = AtlantisFuel.vehicles.get(index).getDayBought();
//        jComboBox3.setSelectedIndex(dateDay-1);
//        int dateMonth1 = AtlantisFuel.vehicles.get(index).getMonthBought();
//        jComboBox2.setSelectedIndex(dateMonth1-1);
//        int dateYear = AtlantisFuel.vehicles.get(index).getYearBought();
//        jComboBox1.setSelectedIndex(dateYear-2020);
//        //this whole year thing needs to be editted !!!!! 
    }
    
    public void changeButton(){
        String day1 = (String)jComboBox3.getSelectedItem();
        int dayBought = Integer.valueOf(day1);

        String month1 = (String)jComboBox2.getSelectedItem();

        if(month1.equals("Jan")){
            monthBought = 1; 
        } else if(month1.equals("Feb")){
            monthBought = 2; 
        } else if(month1.equals("Mar")){
            monthBought = 3; 
        } else if(month1.equals("Apr")){
            monthBought = 4; 
        } else if(month1.equals("May")){
            monthBought = 5; 
        } else if(month1.equals("Jun")){
            monthBought = 6; 
        } else if(month1.equals("Jul")){
            monthBought = 7; 
        } else if(month1.equals("Aug")){
            monthBought = 8; 
        } else if(month1.equals("Sep")){
            monthBought = 9; 
        } else if(month1.equals("Oct")){
            monthBought = 10; 
        } else if(month1.equals("Nov")){
            monthBought = 11; 
        } else if(month1.equals("Dec")){
            monthBought = 12; 
        }

        String year1 = (String)jComboBox1.getSelectedItem();
        int yearBought = Integer.valueOf(year1);

        boolean fourWheelDrive = false;
        if(jCheckBox1.isSelected()){
            fourWheelDrive = true;
        }

        String nameOfVehicle = jTextField2.getText();
        boolean duplicate = false;

        if(nameOfVehicle.equals("")){
            jLabel7.setText("Please enter a name");
        }
        else{
            try {
                AtlantisFuel.deserialiseVehicles();
            } catch (IOException ex) {
                Logger.getLogger(EditVehicle.class.getName()).log(Level.SEVERE, null, ex);
            }

            //need to check if the user has another vehicle with the same name
            int var = 0;
            while(duplicate == false && var < AtlantisFuel.vehicles.size()){
                    //  right here if the vehicle name is not changed it will come up as a duplicate
                    if(AtlantisFuel.vehicles.get(var).getUsername().equals(username) && nameOfVehicle.equals(previousVehicleName)){
                        break;
                    } else if(AtlantisFuel.vehicles.get(var).getUsername().equals(username) && AtlantisFuel.vehicles.get(var).getNameOfVehicle().equals(nameOfVehicle)){
                            //if user already has vehicle with this name
                            jLabel7.setText("You already have a vehicle with this name. Please choose another name.");
                            duplicate = true;
                    } else {
                        var = var + 1;
                    }
                    
            }

            if(duplicate == false){
                try {
                    AtlantisFuel.deserialiseFuel();
                } catch (IOException ex) {
                    Logger.getLogger(EditVehicle.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //find index
                for(int i = 0; i < AtlantisFuel.vehicles.size(); i++){
                    if(AtlantisFuel.vehicles.get(i).getUsername().equals(username) && AtlantisFuel.vehicles.get(i).getNameOfVehicle().equals(previousVehicleName)){
                        index = i;
                        break;
                    }
                }

                AtlantisFuel.editVehicle(index, username, nameOfVehicle, dayBought, monthBought, yearBought, fourWheelDrive); 
                //change vehicle in entries
                for(int count = 0; count < AtlantisFuel.fuelConsumption.size(); count++){
                    if(AtlantisFuel.fuelConsumption.get(count).getUsername().equals(HomePage.username) && AtlantisFuel.fuelConsumption.get(count).getVehicleName().equals(previousVehicleName)){
                        AtlantisFuel.fuelConsumption.get(count).setVehicleName(nameOfVehicle);
                    }
                }

                AtlantisFuel.serialiseVehicles();
            }
        }
    }
    
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setBackground(new java.awt.Color(239, 240, 245));
        jLabel5.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jLabel5.setText("New Day");

        jComboBox1.setBackground(new java.awt.Color(239, 240, 245));
        jComboBox1.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(12, 12, 20));

        jComboBox2.setBackground(new java.awt.Color(239, 240, 245));
        jComboBox2.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(12, 12, 20));

        jComboBox3.setBackground(new java.awt.Color(239, 240, 245));
        jComboBox3.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jComboBox3.setForeground(new java.awt.Color(12, 12, 20));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(239, 240, 245));
        jLabel1.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jLabel1.setText("New Month");

        jLabel3.setBackground(new java.awt.Color(239, 240, 245));
        jLabel3.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jLabel3.setText("New Year");

        jButton1.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jButton1.setText("Change");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setText(".");

        jCheckBox1.setBackground(new java.awt.Color(239, 240, 245));
        jCheckBox1.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jCheckBox1.setText("      Is this vehicle a four wheel drive?");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jTextField2.setBackground(new java.awt.Color(239, 240, 245));
        jTextField2.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jTextField2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(12, 12, 20)));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(239, 240, 245));
        jLabel4.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jLabel4.setText("New Name");

        jPanel8.setBackground(new java.awt.Color(8, 54, 193));
        jPanel8.setPreferredSize(new java.awt.Dimension(150, 800));

        jButton20.setBackground(new java.awt.Color(239, 188, 79));
        jButton20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton20.setText("Add A Fuel Entry");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setBackground(new java.awt.Color(239, 188, 79));
        jButton21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton21.setText("Add Your Vehicles");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton22.setBackground(new java.awt.Color(8, 54, 193));
        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/atlantis_fuel.png"))); // NOI18N
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 231, Short.MAX_VALUE)
                .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(139, 139, 139))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(388, 388, 388))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(233, 233, 233)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 2, Short.MAX_VALUE)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121)
                .addComponent(jLabel7)
                .addGap(41, 41, 41))
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
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

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        changeButton();
        new HomePage(username).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
        //cost
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        new AddEntry(username).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
        new AddVehicle(username).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
        new HomePage(username).setVisible(true);
    }//GEN-LAST:event_jButton22ActionPerformed

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
            java.util.logging.Logger.getLogger(EditVehicle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditVehicle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditVehicle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditVehicle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditVehicle(username, nameOfVehicle).setVisible(true);
                //VIEWVEHICLES2.setVisible(false);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JCheckBox jCheckBox1;
    public static javax.swing.JComboBox<String> jComboBox1;
    public static javax.swing.JComboBox<String> jComboBox2;
    public static javax.swing.JComboBox<String> jComboBox3;
    public static javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel jLabel4;
    public static javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
