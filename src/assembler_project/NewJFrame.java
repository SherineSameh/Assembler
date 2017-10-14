
package assembler_project;

import java.awt.event.ItemEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class NewJFrame extends javax.swing.JFrame {


private Pass1 pass1;
private Pass2 pass2;
        
private Files Sfile; 
private Files Ifile ;
private Files Ofile ;
private Files Lfile ;

private ArrayList <String> line;
private ArrayList <String> line1;
private ArrayList <String> line2;
private ArrayList <String> line3;

private String OpText1;
private String OpText2;
private String OpText3;

private String IpText;
private String SrcFName;

    public NewJFrame() {
    
    initComponents();
    
    SrcFName = "";
    line1 = new ArrayList<>();
    line2 = new ArrayList<>();
    line3 = new ArrayList<>();
    
    line = new ArrayList<>();
    
    OpText1 = "";
    OpText2 = "";
    OpText3 = "";
    
    IpText = "";
    SrcFName = "";
       
    Sfile = new Files(); 
    Ifile = new Files();
    Ofile = new Files();
    Lfile = new Files();
    
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Assembler = new javax.swing.JPanel();
        ObjFile = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        InputFile = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        OutputFile = new javax.swing.JTextArea();
        LisFile = new javax.swing.JButton();
        SrcName = new javax.swing.JTextField();
        LoadFile = new javax.swing.JButton();
        INTFILE = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Assembler.setBackground(new java.awt.Color(238, 238, 238));
        Assembler.setForeground(new java.awt.Color(255, 204, 204));
        Assembler.setAlignmentX(0.0F);
        Assembler.setAlignmentY(0.0F);
        Assembler.setAutoscrolls(true);
        Assembler.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Assembler.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));

        ObjFile.setBackground(new java.awt.Color(255, 255, 204));
        ObjFile.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        ObjFile.setText("OBJFILE");
        ObjFile.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ObjFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ObjFileActionPerformed(evt);
            }
        });

        InputFile.setBackground(new java.awt.Color(230, 246, 254));
        InputFile.setColumns(20);
        InputFile.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        InputFile.setRows(3);
        jScrollPane1.setViewportView(InputFile);

        OutputFile.setBackground(new java.awt.Color(255, 255, 204));
        OutputFile.setColumns(20);
        OutputFile.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        OutputFile.setRows(5);
        jScrollPane2.setViewportView(OutputFile);

        LisFile.setBackground(new java.awt.Color(255, 255, 204));
        LisFile.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        LisFile.setText("LISFILE");
        LisFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LisFileActionPerformed(evt);
            }
        });

        SrcName.setBackground(new java.awt.Color(230, 246, 254));
        SrcName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        SrcName.setToolTipText("enter your source file name");
        SrcName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SrcNameActionPerformed(evt);
            }
        });

        LoadFile.setBackground(new java.awt.Color(230, 246, 254));
        LoadFile.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        LoadFile.setForeground(new java.awt.Color(66, 76, 82));
        LoadFile.setText("Load File");
        LoadFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadFileActionPerformed(evt);
            }
        });

        INTFILE.setBackground(new java.awt.Color(255, 255, 204));
        INTFILE.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        INTFILE.setText("Literal pool / Blocs table");
        INTFILE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                INTFILEActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AssemblerLayout = new javax.swing.GroupLayout(Assembler);
        Assembler.setLayout(AssemblerLayout);
        AssemblerLayout.setHorizontalGroup(
            AssemblerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AssemblerLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(AssemblerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AssemblerLayout.createSequentialGroup()
                        .addComponent(SrcName, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(LoadFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                .addGroup(AssemblerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AssemblerLayout.createSequentialGroup()
                        .addComponent(LisFile, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(ObjFile, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(INTFILE, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        AssemblerLayout.setVerticalGroup(
            AssemblerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AssemblerLayout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addGroup(AssemblerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(INTFILE, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LisFile, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ObjFile, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SrcName, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LoadFile, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AssemblerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Assembler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Assembler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ObjFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ObjFileActionPerformed

        IpText = "";
        OpText1 = "";
        OpText2 = "";
        OpText3 = "";
        try {
        
        IpText = InputFile.getText();
        Sfile.openFileW("SRCFILE.txt");
        Sfile.writeToFile(IpText);
        Sfile.closeFileW();
        
        pass1 = new Pass1();
        pass2 = new Pass2();
        pass2.setLists();
        pass2.CreateLisFile();
        pass2.CreateObjectCode();
       
        Ofile.openFileR("OBJFILE.txt");
        Ofile.readFromFile();
        Ofile.closeFileR();   
    
        line1 = Ofile.getLines();
        
        for (int i = 0; i < line1.size(); i++) { OpText1 += line1.get(i)+"\n"; }
        line1.clear();
      
        
        
        } catch(Exception e ){ OpText1 = "SRCFILE NOT FOUND"; }
         OutputFile.setText(OpText1);
         OutputFile.setEditable(false);

    }//GEN-LAST:event_ObjFileActionPerformed

    private void LisFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LisFileActionPerformed
        IpText ="";
        OpText2 = "";
        OpText1 = "";
        OpText3 = "";
        try {
        
        IpText = InputFile.getText();
        Sfile.openFileW("SRCFILE.txt");
        Sfile.writeToFile(IpText);
        Sfile.closeFileW();
        
        pass1 = new Pass1();
        pass2 = new Pass2();
        pass2.setLists();
        pass2.CreateLisFile();
        Lfile.openFileR("LISFILE.txt");
        Lfile.readFromFile();
        Lfile.closeFileR();   
    
        line2 = Lfile.getLines();
        
        for (int i = 0; i < line2.size(); i++) { OpText2 += line2.get(i)+"\n"; }
        line2.clear();
      
        } catch(Exception e ){ OpText2 = "SRCFILE NOT FOUND"; }
         OutputFile.setText(OpText2);
         OutputFile.setEditable(false);

    }//GEN-LAST:event_LisFileActionPerformed

    private void LoadFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadFileActionPerformed
        
        IpText ="";
        SrcFName = SrcName.getText();
        
        if(!SrcFName.isEmpty()){
        
        try {
        Sfile.openFileR(SrcFName);
        Sfile.readFromFile();
        Sfile.closeFileR();   
        line = Sfile.getLines();
        for (int i = 0; i < line.size(); i++) { IpText += line.get(i)+"\n"; }
        line.clear();
        } catch(Exception e)
        {
                IpText = "";
                JOptionPane.showMessageDialog(null,"ERROR in loading Source File\n please enter your code or enter a valid source file\n","ERROR",JOptionPane.WARNING_MESSAGE);
        }
        InputFile.setText(IpText);
        }
        else
        {   
        JOptionPane.showMessageDialog(null,"No Source File has been detected \n please enter your code or enter a valid source file\n","ERROR",JOptionPane.WARNING_MESSAGE);
        IpText = InputFile.getText();
        Sfile.openFileW(SrcFName);
        Sfile.writeToFile(IpText);
        Sfile.closeFileW();
        
        }
        
    }//GEN-LAST:event_LoadFileActionPerformed

    private void INTFILEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_INTFILEActionPerformed
        IpText = "";
        OpText1 = "";
        OpText2 = "";
        OpText3 = "";
        
        try {
        
        
        pass1 = new Pass1();
        pass1.createIntFile();
    
        ArrayList <Literals> l = pass1.getFinalLiteral();
        ArrayList <ProgramBlocs> b = pass1.getBloc();
      
        OpText3 += "\n\n\t*** Literals Pool ***\n\n";
        for(Literals Lit : l)
        { OpText3 += Lit.getName() +"\t" + Lit.getHexValue() + "\t" +Lit.getAddress() +"\t" +Lit.getLength() +"\n";}
        
        OpText3 +=("\n\n\t*** ProgramBlocs ***\n\n ");
        for(ProgramBlocs bl : b)
        { OpText3 += bl.getBlockName()+"\t"+bl.getBlockNumber()+"\t" +bl.getAddress()+"\t"+bl.getLength()+"\n"; }
     
        
        
        
        
        } catch(Exception e ){ OpText3 = "SRCFILE NOT FOUND"; }
         OutputFile.setText(OpText3);
         OutputFile.setEditable(false);
         OpText3 = "";
        
    }//GEN-LAST:event_INTFILEActionPerformed

    private void SrcNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SrcNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SrcNameActionPerformed
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);               
    
            }
        });
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Assembler;
    private javax.swing.JButton INTFILE;
    private javax.swing.JTextArea InputFile;
    private javax.swing.JButton LisFile;
    private javax.swing.JButton LoadFile;
    private javax.swing.JButton ObjFile;
    private javax.swing.JTextArea OutputFile;
    private javax.swing.JTextField SrcName;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
