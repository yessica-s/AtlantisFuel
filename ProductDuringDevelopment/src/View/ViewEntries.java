package View;
import Controller.AtlantisFuel;
import Model.FuelConsumption;
import static View.HomePage.username;
import com.lowagie.text.Chapter;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

public class ViewEntries extends javax.swing.JFrame {
    
    public static long fuelID;

    public ViewEntries() {
        initComponents();
        AtlantisFuel.addRowToJTable();
        
        //jLabel1.setText("Hello" + HomePage.username);
        
        int index = AtlantisFuel.findUser(HomePage.username);
        try {
            AtlantisFuel.deserialiseUser();
        } catch (IOException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
        jLabel1.setText("Hello " + AtlantisFuel.users.get(index).getFirstname());
        AtlantisFuel.serialiseUser();

    }

    //it is unclear whether this is successful on a macbook becuase of the file address system?!
    
    public void downloadPDF(){
        
        try {
            AtlantisFuel.deserialiseUser();
        } catch (IOException ex) {
            Logger.getLogger(ViewEntries.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String home = System.getProperty("user.home"); //locates address of user's home file location
        Document document = new Document(); 
        //the itext library has been imported to enable the following code
        
        try {
            PdfWriter.getInstance(document, new FileOutputStream(home+"/Downloads/"+"AtlantisFuel.pdf"));
            //automatically saves the file to the user's downloads folder 
            document.open();
            
            //adding a title to the pdf
            int currentUser = AtlantisFuel.findUser(HomePage.username); //retrieves username
            Paragraph chapterTitle = new Paragraph(AtlantisFuel.users.get(currentUser).getFirstname()+"'s Fuel Usage\n"
                    + " ");
            Chapter chapter1 = new Chapter(chapterTitle, 1); /*instantiates new title, will require one line on the pdf*/
            chapter1.setNumberDepth(0);
            document.add(chapter1); //adding the title to the document
            
            //6 columns required in this table to match headers of table in jframe
            PdfPTable table = new PdfPTable(6);
            table.addCell("Day");
            table.addCell("Month");
            table.addCell("Year");
            table.addCell("Type");
            table.addCell("Amount");
            table.addCell("Cost");
            
            /*loops for number of rows in table, loads all values into separate strings which will be
            added as cells in the table in the pdf*/
            for(int i = 0; i < jTable1.getRowCount(); i++){
                String d = jTable1.getValueAt(i,1).toString();
                String m = jTable1.getValueAt(i, 2).toString();
                String y = jTable1.getValueAt(i, 3).toString();
                String t = jTable1.getValueAt(i, 4).toString();
                String a = jTable1.getValueAt(i, 5).toString();
                String c = jTable1.getValueAt(i, 6).toString();
                
                //data added to table in document as strings
                table.addCell(d);
                table.addCell(m);
                table.addCell(y);
                table.addCell(t);
                table.addCell(a);
                table.addCell(c);  
            }
            
            document.add(table);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ViewEntries.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ViewEntries.class.getName()).log(Level.SEVERE, null, ex);
        }
        document.close();
        
        AtlantisFuel.serialiseUser();
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(12, 12, 20));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(239, 240, 245));
        jLabel1.setFont(new java.awt.Font("Franklin Gothic Book", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(8, 54, 193));
        jLabel1.setText("Hello");

        jTable1.setFont(new java.awt.Font("Franklin Gothic Book", 0, 14)); // NOI18N
        jTable1.setForeground(new java.awt.Color(8, 54, 193));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Number", "Day", "Month", "Year", "Type", "Amount", "Cost"
            }
        ));
        jTable1.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                jTable1ComponentAdded(evt);
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jComboBox1.setForeground(new java.awt.Color(8, 54, 193));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Oldest First", "Newest First", "Cost (Low - High)", "Cost (High - Low)" }));
        jComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox1MouseClicked(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(239, 188, 79));
        jButton2.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(8, 54, 193));
        jButton2.setText("DOWNLOAD DATA");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 845, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2))
                .addGap(0, 97, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 841, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(61, 61, 61))
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

    private void jComboBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseClicked
        
    }//GEN-LAST:event_jComboBox1MouseClicked

    /*the following ActionEvent will sort the table as requested by the user once
    an option is clicked from the drop down menu*/
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        try { /*try-catch statement avoids program crashing if errors occur in the midst
            of deserializing the arraylist.*/
            AtlantisFuel.deserialiseFuel(); /*gets the method from main class*/
        } catch (IOException ex) {
            Logger.getLogger(ViewEntries.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(jComboBox1.getSelectedItem().equals("Newest First")){
            Collections.sort(AtlantisFuel.fuelConsumption, Comparator.comparing(FuelConsumption::getDateEnterred));
            Collections.reverse(AtlantisFuel.fuelConsumption);
        } else if(jComboBox1.getSelectedItem().equals("Oldest First")){
            Collections.sort(AtlantisFuel.fuelConsumption, Comparator.comparing(FuelConsumption::getDateEnterred));
        } else if(jComboBox1.getSelectedItem().equals("Cost (Low - High)")){
            Collections.sort(AtlantisFuel.fuelConsumption, Comparator.comparing(FuelConsumption::getCost));
        } else if(jComboBox1.getSelectedItem().equals("Cost (High - Low)")){
            Collections.sort(AtlantisFuel.fuelConsumption, Comparator.comparing(FuelConsumption::getCost));
            Collections.reverse(AtlantisFuel.fuelConsumption);
        } 
        AtlantisFuel.serialiseFuel(); /*serialize arraylist to save updates made*/
        AtlantisFuel.addRowToJTable(); /*re-creates the table of entries using the updates ArrayList*/

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        /*when user clicks DOWNLOAD DATA this code runs. The following method
        runs which formats and downloads the PDF*/
        downloadPDF();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        //code for when an entry is clicked on to edit
        
        JTable table = (JTable)evt.getSource();
        int row = table.getSelectedRow();
        int column = table.getSelectedColumn();
        String id = (String) jTable1.getValueAt(row,0);
        fuelID = Long.valueOf(id);
        //fuelID = (long) jTable1.getValueAt(row, 0);
        //ObjectType o = (ObjectType)target.getValueAt(row, column));
        
        new EditEntry(fuelID).setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_jTable1MouseClicked

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

    private void jTable1ComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jTable1ComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1ComponentAdded

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
            java.util.logging.Logger.getLogger(ViewEntries.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewEntries.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewEntries.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewEntries.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewEntries().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    public static javax.swing.JComboBox<String> jComboBox1;
    public static javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
