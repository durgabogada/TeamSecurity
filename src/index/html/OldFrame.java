/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package index.html;

import browseractioncapture.RecordAction;
import com.teamsecurity.controller.VulnerabilityController;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author nvlakshmidurgab
 */
public class OldFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public OldFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        urlName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        browserName = new javax.swing.JComboBox<>();
        startRecord = new javax.swing.JButton();
        stopRecord = new javax.swing.JButton();
        chooseFileLocation = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        fileLocation = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        siteLevelAttack = new javax.swing.JButton();
        saveRecord = new javax.swing.JButton();
        playRecord = new javax.swing.JButton();
        openBrowser = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Url");

        urlName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                urlNameActionPerformed(evt);
            }
        });

        jLabel2.setText("Check with Browser");

        browserName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chrome", "FireFox" }));
        browserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browserNameActionPerformed(evt);
            }
        });

        startRecord.setText("Start Record");
        startRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startRecordActionPerformed(evt);
            }
        });

        stopRecord.setText("Stop Record");
        stopRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopRecordActionPerformed(evt);
            }
        });

        chooseFileLocation.setText("Choose Location");
        chooseFileLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseFileLocationActionPerformed(evt);
            }
        });

        jLabel3.setText("Browser File Location");

        fileLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileLocationActionPerformed(evt);
            }
        });

        jLabel4.setText("PAGE SEC");

        siteLevelAttack.setText("Attack");
        siteLevelAttack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siteLevelAttackActionPerformed(evt);
            }
        });

        saveRecord.setText("Save Record");
        saveRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveRecordActionPerformed(evt);
            }
        });

        playRecord.setText("Play Record");
        playRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playRecordActionPerformed(evt);
            }
        });

        openBrowser.setText("Open ");
        openBrowser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openBrowserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(30, 30, 30)
                        .addComponent(chooseFileLocation)
                        .addGap(35, 35, 35)
                        .addComponent(fileLocation)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(startRecord)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stopRecord)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saveRecord)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(playRecord))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(71, 71, 71)
                                .addComponent(browserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(openBrowser)))
                        .addGap(0, 282, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel1)
                        .addGap(57, 57, 57)
                        .addComponent(urlName, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(siteLevelAttack))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(urlName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(siteLevelAttack))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(browserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(openBrowser))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(chooseFileLocation)
                    .addComponent(fileLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startRecord)
                    .addComponent(stopRecord)
                    .addComponent(saveRecord)
                    .addComponent(playRecord))
                .addContainerGap(109, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void urlNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_urlNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_urlNameActionPerformed

    private void browserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browserNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_browserNameActionPerformed

    private void chooseFileLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseFileLocationActionPerformed
        // TODO add your handling code here:
         JFileChooser chooser = new JFileChooser(""); 
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY); 
        int returnVal = chooser.showOpenDialog((java.awt.Component) null); 
        
        if ( returnVal == chooser.APPROVE_OPTION ) {  
            File inFile = chooser.getSelectedFile(); 
            fileLocation.setText(inFile.toPath().toString());
            System.out.println("fileName"+inFile);
        } 
    }//GEN-LAST:event_chooseFileLocationActionPerformed
    
    /* 
        function : siteLevelAttack
        description : security testing on entire site attack
    */
    private void siteLevelAttackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siteLevelAttackActionPerformed
        // attack logic 
        VulnerabilityController vc = new VulnerabilityController();
        vc.attackAction(urlName.getText());   
    }//GEN-LAST:event_siteLevelAttackActionPerformed

    private void startRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startRecordActionPerformed
        // it should start capturing the actions in browser
        recordAction.startRecord();      
    }//GEN-LAST:event_startRecordActionPerformed

    private void openBrowserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openBrowserActionPerformed
        // TODO add your handling code here:
        System.out.println("printed:"+String.valueOf(browserName.getSelectedItem()));
        //BrowserActionCapture b = new BrowserActionCapture();
        initializeRecordAction(urlName.getText(), fileLocation.getText(), String.valueOf(browserName.getSelectedItem()));
        recordAction.openBrowser();
       
    }//GEN-LAST:event_openBrowserActionPerformed

    private void fileLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileLocationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fileLocationActionPerformed

    private void stopRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopRecordActionPerformed
        // invoke stop record
        recordAction.stopRecord();
    }//GEN-LAST:event_stopRecordActionPerformed

    private void saveRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveRecordActionPerformed
        // invoke save record and pass the fileName
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Choose a directory to save your file: ");
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = jfc.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
                if (jfc.getSelectedFile().isDirectory()) {
                    System.out.println("You selected the directory: " + jfc.getSelectedFile());
                }
        }
        System.out.println("the filepath"+jfc.getSelectedFile().getAbsolutePath());
        recordAction.saveRecord(jfc.getSelectedFile().getAbsolutePath());
    }//GEN-LAST:event_saveRecordActionPerformed

    private void playRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playRecordActionPerformed
        // to read excel and perform the same action
         JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
         jfc.setDialogTitle("Choose the script to play: ");
         jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
         jfc.showOpenDialog(null);
         System.out.println("The filename in action"+jfc.getSelectedFile().toString());
         RecordAction recordAction = new RecordAction();
         recordAction.playRecordedScript(jfc.getSelectedFile().toString(),browserName.getSelectedItem().toString(),fileLocation.getText());
    }//GEN-LAST:event_playRecordActionPerformed

    private void initializeRecordAction (String urlName, String driverPath, String browserType) {
        recordAction = new RecordAction(urlName, driverPath, browserType);
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
            java.util.logging.Logger.getLogger(OldFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OldFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OldFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OldFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OldFrame().setVisible(true);
            }
        });
    }

    RecordAction recordAction;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> browserName;
    private javax.swing.JButton chooseFileLocation;
    private javax.swing.JTextField fileLocation;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton openBrowser;
    private javax.swing.JButton playRecord;
    private javax.swing.JButton saveRecord;
    private javax.swing.JButton siteLevelAttack;
    private javax.swing.JButton startRecord;
    private javax.swing.JButton stopRecord;
    private javax.swing.JTextField urlName;
    // End of variables declaration//GEN-END:variables
}
