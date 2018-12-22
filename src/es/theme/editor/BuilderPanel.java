/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.theme.editor;

import es.theme.editor.objects.ESystem;
import es.theme.editor.objects.ThemeBuilder;
import java.awt.Color;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 *
 * @author andrea
 */
public class BuilderPanel extends javax.swing.JFrame {
    
    final private ThemeBuilder builder;

    /**
     * Creates new form BuilderPanel
     * @param builder
     */
    public BuilderPanel(ThemeBuilder builder) {
        initComponents();
        
        this.builder = builder;
        
        BrowseTextField.setText(System.getProperty("user.home"));
        setFinalPath();
        
        output("Ready");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ProgressBar = new javax.swing.JProgressBar();
        BuildButton = new javax.swing.JButton();
        BroseButton = new javax.swing.JButton();
        BrowseTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        CancelButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Textpane = new javax.swing.JTextPane();
        FinalDestLabel = new javax.swing.JLabel();

        setTitle("Build theme");
        setMaximumSize(new java.awt.Dimension(420, 570));
        setMinimumSize(new java.awt.Dimension(420, 570));
        setPreferredSize(new java.awt.Dimension(420, 570));
        setResizable(false);

        ProgressBar.setStringPainted(true);

        BuildButton.setText("Build");
        BuildButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuildButtonActionPerformed(evt);
            }
        });

        BroseButton.setText("Browse");
        BroseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BroseButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Select target folder:");

        CancelButton.setText("Cancel");
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButtonActionPerformed(evt);
            }
        });

        Textpane.setEditable(false);
        jScrollPane2.setViewportView(Textpane);

        FinalDestLabel.setText("Final destination will be:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(ProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BrowseTextField)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 185, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BroseButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(BuildButton)
                        .addGap(11, 11, 11)
                        .addComponent(CancelButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(FinalDestLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BrowseTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BroseButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FinalDestLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BuildButton)
                    .addComponent(CancelButton))
                .addGap(7, 7, 7))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_CancelButtonActionPerformed

    private void BroseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BroseButtonActionPerformed
        final JFileChooser folderChooser = new JFileChooser();
        folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        folderChooser.setDialogTitle("Choose target directory");
        folderChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        folderChooser.setAcceptAllFileFilterUsed(false);
        
        if (folderChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            if(folderChooser.getSelectedFile().isDirectory()){
                output("Set target folder to " + folderChooser.getSelectedFile().getAbsolutePath());
                BrowseTextField.setText(folderChooser.getSelectedFile().getAbsolutePath());
                
                setFinalPath();
            }
        }
    }//GEN-LAST:event_BroseButtonActionPerformed

    private void BuildButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuildButtonActionPerformed
        
        BuildButton.setEnabled(false);
        CancelButton.setEnabled(false);
        BrowseTextField.setEditable(false);
        ProgressBar.setMaximum(2*builder.getSaveFile().getSystems().size());
        
        
        
//        builder.setPath(BrowseTextField.getText()+"/"+builder.getSaveFile().getInfo("name"));
//        
//        int c=0;
//        while(Files.exists(builder.rootDir)){
//            c++;
//            builder.setPath(BrowseTextField.getText()+"/"+builder.getSaveFile().getInfo("name") + " " + c);
//            output("Warning: '" + builder.getSaveFile().getInfo("name") + "' folder already exists. Changing it to '" + 
//                    builder.getSaveFile().getInfo("name") + " " + c+"'",Color.ORANGE);
//        }
        
        output("Starting building theme at: "+builder.rootDir.toString()+"...",Color.BLUE);
        
        for(ESystem s : builder.getSaveFile().getSystems()){
            output("    Building xml file for system '" + s.getSpec("name")+"'...");
            if(builder.buildXMLOfSystem(s)){
                advanceProgress();
            }
            else{
                output("Error during building xml file for system '" + s.getSpec("name")+"'",Color.RED);
            }
            output("    Building resources for system '" + s.getSpec("name")+"'...");
            if(builder.buildResourcesOfSystem(s)){
                advanceProgress();
            }
            else{
                output("Error during building resources for system '" + s.getSpec("name")+"'",Color.RED);
            }
        }
        
        output("DONE",Color.GREEN);
        
        BuildButton.setEnabled(true);
        CancelButton.setEnabled(true);
        BrowseTextField.setEditable(true);
        output("Ready");
    }//GEN-LAST:event_BuildButtonActionPerformed

    private void advanceProgress(){
        ProgressBar.setValue(ProgressBar.getValue()+1);
    }
    
    private void output(String msg){
        output(msg,Color.BLACK);
    }
    
    private void output(String msg,Color c){
        Textpane.setEditable(true);
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = Textpane.getDocument().getLength();
        Textpane.setCaretPosition(len);
        Textpane.setCharacterAttributes(aset, false);
        Textpane.replaceSelection(msg+"\n");
        Textpane.setEditable(false);
    }
    
    private void setFinalPath(){
        builder.setPath(BrowseTextField.getText()+"/"+builder.getSaveFile().getInfo("name"));
        
        int c=0;
        while(Files.exists(builder.rootDir)){
            c++;
            output("Warning: '" + builder.rootDir.getFileName() + "' folder already exists. Changing it to '" + 
                    builder.getSaveFile().getInfo("name") + " " + c+"'",Color.ORANGE);
            builder.setPath(BrowseTextField.getText()+"/"+builder.getSaveFile().getInfo("name") + " " + c);
        }
        
        FinalDestLabel.setText("Final destination will be: " + builder.rootDir);
        ProgressBar.setValue(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BroseButton;
    private javax.swing.JTextField BrowseTextField;
    private javax.swing.JButton BuildButton;
    private javax.swing.JButton CancelButton;
    private javax.swing.JLabel FinalDestLabel;
    private javax.swing.JProgressBar ProgressBar;
    private javax.swing.JTextPane Textpane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
