/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whishare.flathouse_application;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author nicol
 */
public class FlathouseGUI extends javax.swing.JFrame {

    /**
     * Creates new form FlathouseGUI
     */
    public FlathouseGUI() {
        initComponents();
        this.setResizable(false);
        FlatHouse.instance = this;
        //JOptionPane.showMessageDialog(null, "My Goodness, this is so concise");
        buttonRent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FlatHouse.rent();
            }
        });
        buttonRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                int flatID = Integer.parseInt(String.valueOf(comboBoxFlatID.getSelectedItem()));
                int residentID = Integer.parseInt(textFieldResidentID.getText());
                //System.out.println(flatID+","+residentID);
                FlatHouse.register(flatID,residentID);
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Трапилась помилка!");
                }
            }
        });
        buttonUnregister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                int flatID = Integer.parseInt(String.valueOf(comboBoxFlatID.getSelectedItem()));
                FlatHouse.unregister(flatID);
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Трапилась помилка!");
                }
            }
        });
        buttonPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                int paymentID = Integer.parseInt(textFieldPaymentID.getText());
                FlatHouse.pay(paymentID);
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Трапилась помилка!");
                }
            }
        });
        refresh();
    }
    
    public LocalDate getDateOfRent() {
        return LocalDate.of((int)spinnerYear.getValue(),(int)spinnerMonth.getValue(), 1);
    }
    
    private String[][] getArrayOfFlats() {
        FlatHouse.refresh();
        String[][] result = new String[FlatHouse.flats.size()][4];
        for(int i = 0; i < FlatHouse.flats.size(); i++) {
            result[i][0] = String.valueOf(FlatHouse.flats.get(i).ID);
            result[i][1] = String.valueOf(FlatHouse.flats.get(i).area);
            result[i][2] = String.valueOf(FlatHouse.flats.get(i).rent);
            try {
                result[i][3] = String.valueOf(FlatHouse.flats.get(i).resident.ID);
            }
            catch (NullPointerException ex) {
                result[i][3] = "Не заселений";
            }
        }
        return result;
    }
    private String[][] getArrayOfPayments() {
        FlatHouse.refresh();
        String[][] result = new String[FlatHouse.payments.size()][8];
        for(int i = 0; i < FlatHouse.payments.size(); i++) {
            result[i][0] = String.valueOf(FlatHouse.payments.get(i).ID);
            result[i][1] = String.valueOf(FlatHouse.payments.get(i).flatID);
            //result[i][2] = String.valueOf(FlatHouse.payments.get(i).paid);
            if (FlatHouse.payments.get(i).paid)
                result[i][2] = "Оплачено";
            else 
                result[i][2] = "Не оплачено";
            result[i][3] = String.valueOf(FlatHouse.payments.get(i).rent);
            result[i][4] = String.valueOf(FlatHouse.payments.get(i).debt);
            result[i][5] = String.valueOf(FlatHouse.payments.get(i).fine);
            result[i][6] = String.valueOf(FlatHouse.payments.get(i).forDay);
            if (FlatHouse.payments.get(i).payDay != null )
                result[i][7] = String.valueOf(FlatHouse.payments.get(i).payDay);
            else 
                result[i][7] = "Не оплачено";
        }
        return result;
    }
    private String[] getComboBoxFlatID () {
        String[] result = new String[FlatHouse.flats.size()];
        for(int i = 0; i < FlatHouse.flats.size();i++) {
            result[i] = String.valueOf(FlatHouse.flats.get(i).ID);
        }
        return result;
    }
    private String[] flatsColumnNames = {"ID","Площа","Квартплата","ID Мешканця"};
    private String[] paymentsColumnNames = {"ID","ID Квартири","Оплачено?","Оренда","Борг","Пеня","Оплата за день","День оплати"};
    public void refresh() {
        this.tableFlats.setModel(new DefaultTableModel(getArrayOfFlats(),flatsColumnNames));
        this.tablePayments.setModel(new DefaultTableModel(getArrayOfPayments(),paymentsColumnNames));
        this.comboBoxFlatID.setModel(new DefaultComboBoxModel<>(getComboBoxFlatID()));
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tablePayments.getModel());
        tablePayments.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
        sorter.setSortKeys(sortKeys);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableFlats = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablePayments = new javax.swing.JTable();
        buttonRegister = new javax.swing.JButton();
        textFieldResidentID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        buttonUnregister = new javax.swing.JButton();
        buttonPay = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        textFieldPaymentID = new javax.swing.JTextField();
        comboBoxFlatID = new javax.swing.JComboBox<>();
        spinnerYear = new javax.swing.JSpinner();
        spinnerMonth = new javax.swing.JSpinner();
        buttonRent = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tableFlats.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableFlats.setEnabled(false);
        jScrollPane1.setViewportView(tableFlats);

        tablePayments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablePayments.setEnabled(false);
        jScrollPane2.setViewportView(tablePayments);

        buttonRegister.setText("Заселити мешканця");
        buttonRegister.setActionCommand("register");

        jLabel1.setText("- ID мешканця");

        jLabel2.setText("- ID квартири");

        buttonUnregister.setText("Виселити мешканця");
        buttonUnregister.setActionCommand("unregister");

        buttonPay.setText("Оплатити рахунок");
        buttonPay.setActionCommand("pay");

        jLabel3.setText("- ID рахунку");

        comboBoxFlatID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        spinnerYear.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        spinnerYear.setValue(2021);

        spinnerMonth.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        spinnerMonth.setValue(1);

        buttonRent.setText("Провести квартплату");
        buttonRent.setActionCommand("rent");

        jLabel4.setText("Рік");

        jLabel5.setText("Місяць");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(buttonRegister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(buttonUnregister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(comboBoxFlatID, 0, 1, Short.MAX_VALUE)
                                    .addComponent(textFieldResidentID, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(8, 8, 8))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonPay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textFieldPaymentID, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addGap(4, 4, 4))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(spinnerYear, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(spinnerMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonRent, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(4, 4, 4)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonRegister)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(buttonUnregister)
                                    .addComponent(comboBoxFlatID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(textFieldResidentID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1)))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldPaymentID)
                            .addComponent(jLabel3)
                            .addComponent(buttonPay))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(spinnerYear, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                    .addComponent(spinnerMonth)))
                            .addComponent(buttonRent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(FlathouseGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FlathouseGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FlathouseGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FlathouseGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FlathouseGUI().setVisible(true);
            }
        });
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonPay;
    private javax.swing.JButton buttonRegister;
    private javax.swing.JButton buttonRent;
    private javax.swing.JButton buttonUnregister;
    private javax.swing.JComboBox<String> comboBoxFlatID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner spinnerMonth;
    private javax.swing.JSpinner spinnerYear;
    private javax.swing.JTable tableFlats;
    private javax.swing.JTable tablePayments;
    private javax.swing.JTextField textFieldPaymentID;
    private javax.swing.JTextField textFieldResidentID;
    // End of variables declaration//GEN-END:variables
}
