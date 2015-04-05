/*
 * UserListDialog.java
 *
 * Created on September 8, 2006, 2:04 AM
 */

package com.floreantpos.ui.dialog;

import java.util.List;

import com.floreantpos.model.User;
import com.floreantpos.model.dao.UserDAO;
import com.floreantpos.swing.ListComboBoxModel;

/**
 *
 * @author  MShahriar
 */
public class UserListDialog extends POSDialog {
    
    /** Creates new form UserListDialog */
    public UserListDialog() {
        initComponents();
        setTitle(com.floreantpos.POSConstants.USER_LIST);
        
        List<User> userList = UserDAO.instance.findAll();
        cbUserList.setModel(new ListComboBoxModel(userList));
        
        cbUserList.setFocusable(false);
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        titlePanel1 = new com.floreantpos.ui.TitlePanel();
        transparentPanel1 = new com.floreantpos.swing.TransparentPanel();
        transparentPanel2 = new com.floreantpos.swing.TransparentPanel();
        btnOk = new com.floreantpos.swing.PosButton();
        btnCancel = new com.floreantpos.swing.PosButton();
        jSeparator1 = new javax.swing.JSeparator();
        transparentPanel3 = new com.floreantpos.swing.TransparentPanel();
        cbUserList = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        titlePanel1.setTitle(com.floreantpos.POSConstants.SELECT_USER);
        getContentPane().add(titlePanel1, java.awt.BorderLayout.NORTH);

        transparentPanel1.setLayout(new java.awt.BorderLayout());

        btnOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/finish_32.png")));
        btnOk.setText(com.floreantpos.POSConstants.OK);
        btnOk.setPreferredSize(new java.awt.Dimension(120, 50));
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doOk(evt);
            }
        });

        transparentPanel2.add(btnOk);

        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel_32.png")));
        btnCancel.setText(com.floreantpos.POSConstants.CANCEL);
        btnCancel.setPreferredSize(new java.awt.Dimension(120, 50));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doCancel(evt);
            }
        });

        transparentPanel2.add(btnCancel);

        transparentPanel1.add(transparentPanel2, java.awt.BorderLayout.CENTER);

        transparentPanel1.add(jSeparator1, java.awt.BorderLayout.NORTH);

        getContentPane().add(transparentPanel1, java.awt.BorderLayout.SOUTH);

        cbUserList.setFont(new java.awt.Font("Tahoma", 1, 18));

        org.jdesktop.layout.GroupLayout transparentPanel3Layout = new org.jdesktop.layout.GroupLayout(transparentPanel3);
        transparentPanel3.setLayout(transparentPanel3Layout);
        transparentPanel3Layout.setHorizontalGroup(
            transparentPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(transparentPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(cbUserList, 0, 486, Short.MAX_VALUE)
                .addContainerGap())
        );
        transparentPanel3Layout.setVerticalGroup(
            transparentPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(transparentPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(cbUserList, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        getContentPane().add(transparentPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void doOk(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doOk
    	setCanceled(false);
    	dispose();
    }//GEN-LAST:event_doOk

    private void doCancel(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doCancel
    	setCanceled(true);
    	dispose();
    }//GEN-LAST:event_doCancel
    
    public User getSelectedUser() {
    	return (User) cbUserList.getSelectedItem();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.floreantpos.swing.PosButton btnCancel;
    private com.floreantpos.swing.PosButton btnOk;
    private javax.swing.JComboBox cbUserList;
    private javax.swing.JSeparator jSeparator1;
    private com.floreantpos.ui.TitlePanel titlePanel1;
    private com.floreantpos.swing.TransparentPanel transparentPanel1;
    private com.floreantpos.swing.TransparentPanel transparentPanel2;
    private com.floreantpos.swing.TransparentPanel transparentPanel3;
    // End of variables declaration//GEN-END:variables
    
}
