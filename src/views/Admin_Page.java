package views;

import controllers.SystemController;
import dataaccess.DataAccessFacade;
import models.Book;
import models.LibraryMember;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author yohannes
 */
public class Admin_Page extends javax.swing.JFrame {
    private LibraryMember member;
    public void setMember(String memberid){
        DataAccessFacade da= new DataAccessFacade();
        member=da.getLibraryMember(memberid);

    }

    /**
     * Creates new form Admin_Page
     */
    public Admin_Page() {
        initComponents();
        addRowToTable();

    }
    public void Search(){
        DefaultTableModel model= (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        String key= jTextField1.getText();
        DataAccessFacade da= new DataAccessFacade();
        HashMap<String, Book> books= da.readBooksMap();
        List<Book> values = da.readBooksMap().values().stream().collect(Collectors.toList());
        List<Book> result = values.stream().filter(x -> x.getTitle().startsWith(key)).collect(Collectors.toList());
        jTable1.removeAll();
        Object[] rowData =new Object[books.size()];
        for(int i=0; i< result.size();i++){
            rowData[0]=result.get(i).getTitle();
            rowData[1]=result.get(i).getIsbn();
            rowData[2]=result.get(i).getAuthorList().get(0).getFirstName();
            rowData[3]=result.get(i).numberofAvailableCopies();
            model.addRow(rowData);
        }
    }
    public void memberSearch(){
        DefaultTableModel model= (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        String key= jTextField1.getText();
        DataAccessFacade da= new DataAccessFacade();
        HashMap<String, LibraryMember> members= da.readMemberMap();
        List<LibraryMember> values = da.readMemberMap().values().stream().collect(Collectors.toList());
        List<LibraryMember> result = values.stream().filter(x -> x.getFirstName().startsWith(key)).collect(Collectors.toList());
        jTable1.removeAll();
        Object[] rowData =new Object[members.size()];
        for(int i=0; i< result.size();i++){
            rowData[0]=result.get(i).getFirstName();
            rowData[1]=result.get(i).getLastName();
            rowData[2]=result.get(i).getMemberId();
            rowData[3]=result.get(i).getPhone();
            model.addRow(rowData);
        }
    }
    public void checkOutABook(){
        setMember("1001");
        int a=jTable1.getSelectedRow();

        SystemController systemController= new SystemController();
        System.out.println(systemController.checkOutBook((String)jTable1.getValueAt(a,1),member.getMemberId()));
        JOptionPane.showMessageDialog(rootPane,member.getCheckOutRecord().toString());
    }

    public void addRowToTable(){
        DefaultTableModel model= (DefaultTableModel) jTable1.getModel();
        jTable1.getColumnModel().getColumn(0).setHeaderValue("Title");
        jTable1.getColumnModel().getColumn(1).setHeaderValue("ISBN");
        jTable1.getColumnModel().getColumn(2).setHeaderValue("Author");
        jTable1.getColumnModel().getColumn(3).setHeaderValue("AvailableCopies");
        jTable1.getTableHeader().repaint();
        model.setRowCount(0);
        DataAccessFacade da= new DataAccessFacade();
        HashMap<String,Book> books= da.readBooksMap();
        Object[] rowData =new Object[books.size()];
        List<String> isbn= new ArrayList<>();
        books.forEach((x,y)-> isbn.add(y.getIsbn()));
        for(int i=0; i< books.size();i++){
            rowData[0]=books.get(isbn.get(i)).getTitle();
            rowData[1]=books.get(isbn.get(i)).getIsbn();
            rowData[2]=books.get(isbn.get(i)).getAuthorList().get(0).getFirstName();
            rowData[3]=books.get(isbn.get(i)).numberofAvailableCopies();
            model.addRow(rowData);
        }

    }
    public void checkOutBookRecord(){
        int a= jTable1.getSelectedRow();
        String key = (String) jTable1.getValueAt(a, 2);
        DataAccessFacade da= new DataAccessFacade();
        LibraryMember member1= da.getLibraryMember(key);
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CheckoutRecordDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CheckoutRecordDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CheckoutRecordDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CheckoutRecordDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CheckoutRecordDetail(member1).setVisible(true);
            }
        });


    }
    public void addmemberToTable(){
        DefaultTableModel model= (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        jTable1.getColumnModel().getColumn(0).setHeaderValue("FirstName");
        jTable1.getColumnModel().getColumn(1).setHeaderValue("LastName");
        jTable1.getColumnModel().getColumn(2).setHeaderValue("MemberId");
        jTable1.getColumnModel().getColumn(3).setHeaderValue("Phone");
        jTable1.getTableHeader().repaint();
        DataAccessFacade da= new DataAccessFacade();
        HashMap<String,LibraryMember> members= da.readMemberMap();
        Object[] rowData =new Object[members.size()];
        List<String> memberid= new ArrayList<>();
        members.forEach((x,y)-> memberid.add(y.getMemberId()));
        for(int i=0; i< members.size();i++){
            rowData[0]=members.get(memberid.get(i)).getFirstName();
            rowData[1]=members.get(memberid.get(i)).getLastName();
            rowData[2]=members.get(memberid.get(i)).getMemberId();
            rowData[3]=members.get(memberid.get(i)).getPhone();
            model.addRow(rowData);
        }

    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        button1 = new java.awt.Button();
        button2 = new java.awt.Button();
        button3 = new java.awt.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        button4 = new java.awt.Button();
        button5 = new java.awt.Button();
        label1 = new java.awt.Label();
        button7 = new java.awt.Button();
        button8 = new java.awt.Button();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        button1.setBackground(new java.awt.Color(0, 102, 153));
        button1.setLabel("AddNewMember");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        button2.setLabel("AddNewBook");
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        button3.setLabel("AddBookCopy");
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Title", "ISBN", "Author", "isAvailable"
                }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTextField1.setCaretColor(new java.awt.Color(0, 102, 255));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        button4.setBackground(new java.awt.Color(255, 255, 255));
        button4.setForeground(new java.awt.Color(0, 153, 204));
        button4.setLabel("Search Book");
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });

        button5.setForeground(new java.awt.Color(0, 204, 0));
        button5.setLabel("View Books");
        button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button5ActionPerformed(evt);
            }
        });

        label1.setBackground(new java.awt.Color(0, 102, 153));
        label1.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        label1.setForeground(new java.awt.Color(255, 255, 255));
        label1.setText("Books");

        button7.setLabel("Search member");
        button7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button7ActionPerformed(evt);
            }
        });

        button8.setLabel("View Members");
        button8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button8ActionPerformed(evt);
            }
        });

        jButton1.setText("Logout");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Return a book");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jDesktopPane1.setLayer(button1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(button2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(button3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jTextField1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(button4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(button5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(label1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(button7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(button8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
                jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(25, 25, 25)
                                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                                                .addComponent(button7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(button8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                                                .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(19, 19, 19)
                                                                .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(29, 29, 29)
                                                .addComponent(jButton2)))
                                .addGap(34, 34, 34))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
        );
        jDesktopPane1Layout.setVerticalGroup(
                jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addComponent(jButton1)))
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                                .addGap(23, 23, 23)
                                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(button7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(button8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addGap(34, 34, 34)))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        Search();
    }

    private void button5ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        addRowToTable();
        label1.setText("Books");

    }



    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewMemberForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewMemberForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewMemberForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewMemberForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewMemberForm().setVisible(true);
            }
        });
    }

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewBookFrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewBookFrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewBookFrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewBookFrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewBookFrom().setVisible(true);
            }
        });
    }

    private void button7ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        memberSearch();

    }

    private void button8ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        addmemberToTable();
        label1.setText("Members");
    }

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddBookCopyFrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddBookCopyFrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddBookCopyFrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddBookCopyFrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddBookCopyFrom().setVisible(true);
            }
        });

    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);

            }
        });
        // TODO add your handling code here:
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {


    }

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
            java.util.logging.Logger.getLogger(Admin_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin_Page().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private java.awt.Button button1;
    private java.awt.Button button2;
    private java.awt.Button button3;
    private java.awt.Button button4;
    private java.awt.Button button5;
    private java.awt.Button button7;
    private java.awt.Button button8;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private java.awt.Label label1;
    // End of variables declaration
}
