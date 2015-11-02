/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Slothmun
 */
public class Log extends javax.swing.JFrame {

    /**
     * Creates new form log
     */
    private final Dialog dialog;

    public Log() {
        initComponents();
        URL iconURL = getClass().getResource("/files/mysql-logo-500x272.png");
        ImageIcon icon = new ImageIcon(iconURL);

        this.setIconImage(icon.getImage());
        this.setTitle("MySQL Database Connection Tester Log");

        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());

        this.setSize(new Dimension(xSize / 3, ySize));

        dialog = new Dialog();
    }

    public void append(String text) {
        String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
        if (txtLog.getText().equals("")) {
            txtLog.setText("[" + timeStamp + "] " + text);
        } else {
            txtLog.setText(txtLog.getText() + "\n" + "[" + timeStamp + "] " + text);
        }
    }

    private void saveLog() {
        JFileChooser fileChooser;
        String fileDirectory;
        String timeStamp;
        int selectedOption;
        int option = 0;

        fileChooser = new JFileChooser(); //Create a new file chooser object
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(".txt", "txt"));
        timeStamp = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());

        fileChooser.setSelectedFile(new File("log_" + timeStamp + ".txt"));

        //Get the selected option
        selectedOption = fileChooser.showSaveDialog(null);
        fileDirectory = fileChooser.getSelectedFile().getAbsolutePath();
        //If the user selected "Save" get the directory chosen

        if (selectedOption == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileDirectory);

            if (file.exists()) {
                option = dialog.showYesNo("The file \"" + file.getName() + "\" already exists. Overwrite this file?", this);
            }

            if (option == 0) {
                PrintWriter outputStream;
                FileOutputStream fileOutput;

                try {

                    fileOutput = new FileOutputStream(fileDirectory, false);
                    outputStream = new PrintWriter(fileOutput);

                    outputStream.println(txtLog.getText());
                    //Print the text to the stream then close the stream to save the file
                    outputStream.close();

                } catch (FileNotFoundException e) {
                    dialog.showError("Error: File not found. "
                            + e.getLocalizedMessage(), this);
                    //Notify the user if something goes wrong
                }

                dialog.showInfo(fileChooser.getSelectedFile().getName()
                        + " was saved successfully.", this);
                //Notify the user that the file was saved
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

        pneLog = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextPane();
        mnbMenu = new javax.swing.JMenuBar();
        mnuFile = new javax.swing.JMenu();
        mniSave = new javax.swing.JMenuItem();
        mnuEdit = new javax.swing.JMenu();
        mniClear = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtLog.setEditable(false);
        pneLog.setViewportView(txtLog);

        mnuFile.setText("File");

        mniSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        mniSave.setText("Save");
        mniSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniSaveActionPerformed(evt);
            }
        });
        mnuFile.add(mniSave);

        mnbMenu.add(mnuFile);

        mnuEdit.setText("Edit");

        mniClear.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        mniClear.setText("Clear");
        mniClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniClearActionPerformed(evt);
            }
        });
        mnuEdit.add(mniClear);

        mnbMenu.add(mnuEdit);

        setJMenuBar(mnbMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pneLog, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pneLog, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mniClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniClearActionPerformed
        txtLog.setText(null);
    }//GEN-LAST:event_mniClearActionPerformed

    private void mniSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniSaveActionPerformed
        saveLog();
    }//GEN-LAST:event_mniSaveActionPerformed

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
            java.util.logging.Logger.getLogger(Log.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Log.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Log.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Log.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Log().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar mnbMenu;
    private javax.swing.JMenuItem mniClear;
    private javax.swing.JMenuItem mniSave;
    private javax.swing.JMenu mnuEdit;
    private javax.swing.JMenu mnuFile;
    private javax.swing.JScrollPane pneLog;
    private javax.swing.JTextPane txtLog;
    // End of variables declaration//GEN-END:variables
}
