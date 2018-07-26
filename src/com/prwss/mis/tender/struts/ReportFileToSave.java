/**
 * 
 */
package com.prwss.mis.tender.struts;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
/**
 * @author pjha
 *
 */
public class ReportFileToSave extends JFrame implements ActionListener {
	

	
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JEditorPane jep = new JEditorPane();
	    ReportFileToSave() {
	      super("Editor");
	      Container cp = getContentPane( );
	      cp.add(new JScrollPane(jep), BorderLayout.CENTER);
	      
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	      setSize(200,300);
	   }  

	  

	  public  File  saveFile( ) {
	      JFileChooser jfc = new JFileChooser();
	      int result = jfc.showSaveDialog(this);
	      if(result == JFileChooser.CANCEL_OPTION) return null ;
	      File file = jfc.getSelectedFile();
	   
	     
	      
	      try {
	         BufferedWriter bw = new BufferedWriter(new FileWriter(file));
	         bw.write(jep.getText());
	         bw.close();
	      }
	      catch (Exception e) {
	         JOptionPane.showMessageDialog(
	            this,
	            e.getMessage(),
	            "File Error",
	            JOptionPane.ERROR_MESSAGE
	         );
	      }
	      return file;
	   }

	  
	  /* public static void main(String[] args) {
	      //new ReportFileToSave().setVisible(true);
	      new ReportFileToSave().saveFile();
	      
	   }*/



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	}



	
	

