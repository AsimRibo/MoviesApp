/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hr.algebra;

import hr.algebra.model.Person;
import hr.algebra.model.PersonAddable;
import hr.algebra.utils.MessageUtils;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author asim2
 */
public class AddActorDialog extends javax.swing.JDialog {

    private static final String ERROR_MARK = "X";
    
    private static final String ACTION_FAILED = "Action failed";
    private static final String ACTION_FAILED_MESSAGE = "Actor already listed in the movie.";
    
    private List<JTextField> validationFields;
    private List<JLabel> errorLabels;
    
    PersonAddable personAddable;
    
    /** Creates new form AddActorDialog */
    public AddActorDialog(java.awt.Frame parent, boolean modal, JPanel panel) {
        super(parent, modal);
        initComponents();
        personAddable = (PersonAddable) panel;
        init();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tfFirstName = new javax.swing.JTextField();
        tfLastName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        lbLastNameError = new javax.swing.JLabel();
        lbFirstNameError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("First name:");

        jLabel2.setText("Last name:");

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        lbLastNameError.setForeground(java.awt.Color.red);

        lbFirstNameError.setForeground(java.awt.Color.red);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfLastName, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfFirstName)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbLastNameError, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFirstNameError, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFirstNameError, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbLastNameError, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (formValid()) {
            try {
                Person person = new Person(tfFirstName.getText(), tfLastName.getText());
                if (personAddable.addPerson(person)) {
                    dispose();
                    return;
                }
                MessageUtils.showInformationMessage(ACTION_FAILED, ACTION_FAILED_MESSAGE);
            } catch (Exception ex) {
                Logger.getLogger(AddActorDialog.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_btnAddActionPerformed
    

    /**
     * @param args the command line arguments
     */
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lbFirstNameError;
    private javax.swing.JLabel lbLastNameError;
    private javax.swing.JTextField tfFirstName;
    private javax.swing.JTextField tfLastName;
    // End of variables declaration//GEN-END:variables

    private void init() {
        initValidation();
    }

    private void initValidation() {
        validationFields = Arrays.asList(tfFirstName, tfLastName);
        errorLabels = Arrays.asList(lbFirstNameError, lbLastNameError);
    }
    
    private boolean formValid() {
        boolean ok = true;
        for (int i = 0; i < validationFields.size(); i++) {
            ok &= !validationFields.get(i).getText().trim().isEmpty();
            errorLabels.get(i).setText(validationFields.get(i).getText().trim().isEmpty() ? ERROR_MARK : "");
        }
        return ok;
    }
    

}
