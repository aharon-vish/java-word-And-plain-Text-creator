import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;
import javax.swing.text.MaskFormatter;
import javax.swing.text.StyledEditorKit.BoldAction;


public class menu extends JFrame {
	  
	
	public Folder FolderHolderFiles; //hold all the file
	public  String foldername;  //folder name for later use to create a file by path 
	public static boolean errorCreateFolder=false;  //if create a folder so make the "errorCreateFolder" true ;
	public menu() {
		     
	        initUI();	        
	}	
// create window with menu bar at top 
    private void initUI() {   
           	
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  // size of a screen
	    double height = screenSize.getHeight();
	    double width = screenSize.getWidth();

	    
        createMenuBar();
        setTitle("ProjecFile");     
        setSize((int)width,(int)height);
        setResizable(false);
        setLocationRelativeTo(null);
       
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
    }
        private void createMenuBar() {
            
        	Font font = new Font( "Serif", Font.ITALIC, 20 );         
        	JMenuBar menubar = new JMenuBar();   
        	
        	JMenuItem CreateFolder  = new JMenuItem("Create Folder");                   
            JMenuItem CreatePlainTextDocument = new JMenuItem("Create PlainTextDocument");           
            JMenuItem CreateWordDocument  = new JMenuItem("Create WordDocument");
            JMenuItem FolderAction = new JMenuItem("Open file");
            JMenuItem ShowAllDocumentsFolder = new JMenuItem("Show All Documents");

            JMenuItem Exit = new JMenuItem("Exit");
            Exit.setHorizontalAlignment(SwingConstants.RIGHT);
            ShowAllDocumentsFolder.setHorizontalAlignment(SwingConstants.RIGHT);   
          
            menubar.add(CreateFolder);
            menubar.add(CreatePlainTextDocument);
            menubar.add(CreateWordDocument);
            menubar.add(FolderAction);
            menubar.add(ShowAllDocumentsFolder);
            menubar.add(Exit);
            
            FolderAction.setEnabled(false);
            CreateWordDocument.setEnabled(false);
            CreatePlainTextDocument.setEnabled(false);
            ShowAllDocumentsFolder.setEnabled(false);
            
            Exit.addActionListener(new ActionListener() {         	
            	@Override
                public void actionPerformed(ActionEvent event) {
                    System.exit(0);
                }
        
           });

                        
        //Create Folder 
            
            CreateFolder.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {

                    String FolderNmae=JOptionPane.showInputDialog("Type name of folder:");
                    if(FolderNmae!=null && FolderNmae !="-1" && FolderNmae!="null" && !FolderNmae.isEmpty()) // check if the given name is ok
                    {
                    	 FolderHolderFiles = new Folder(FolderNmae);  // new folder with name
                	 
                    }
                    else{
                    	JOptionPane.showMessageDialog(null,"File name empty"); //no name
                    }
                	if(errorCreateFolder==true){ // if thge folder was created so trun on the button
                    CreateFolder.setEnabled(false);
                    CreateWordDocument.setEnabled(true);
                    CreatePlainTextDocument.setEnabled(true);
                	}
                    

                }
                
            });

           
            CreatePlainTextDocument.addActionListener(new ActionListener() { //create a  PlainTextDocument
				
				@Override
				public void actionPerformed(ActionEvent e) {

	            	File FolderName;
	            	String fileName="";	            	
	            	fileName=JOptionPane.showInputDialog("Type name of file:");
	            	String fULLFileName=FolderHolderFiles.getFolderName()+"\\"+"TEXT_"+fileName; // create txtfile in C:\\"FolderName"\\"TEXT_"+fileName
	            	if(FolderHolderFiles.FindFile(fULLFileName)){
	            		JOptionPane.showMessageDialog(null,"File existed");  // check if existed
	            	}
	            	else{
	            	if(fileName!=null && !fileName.isEmpty() && !fileName.contains("null")){ // check if the given name is ok
	                
	                JOptionPane.showMessageDialog(null,"THIS is your file "+fULLFileName);
	            	PlainTextDocument NewCreatetxt = new PlainTextDocument(fULLFileName);
	            	add(NewCreatetxt);
					NewCreatetxt.setVisible(true);
					FolderHolderFiles.AddFileToFolder(NewCreatetxt);
					}
					
	            	}
				}
			});
            
            
            CreateWordDocument.addActionListener(new ActionListener() { //create a  PlainTextDocument
				
				@Override
				public void actionPerformed(ActionEvent e) {	
					
					File FolderName;
	            	String fileName="";	            	
	            	fileName=JOptionPane.showInputDialog("Type name of file:");
	            	String fULLFileName=FolderHolderFiles.getFolderName()+"\\"+"WORD_"+fileName; // create txtfile in C:\\"FolderName\\"+"WORD_"+fileName
	            	if(FolderHolderFiles.FindFile(fULLFileName)){
	            		JOptionPane.showMessageDialog(null,"File existed");
	            	}
	            	else{
	            	if(fileName!=null && !fileName.isEmpty() && !fileName.contains("null")){   // check if the given name is ok
                    JOptionPane.showMessageDialog(null,"THIS is your file "+fULLFileName);
	            	WordDocument NewCreateWord = new WordDocument(fULLFileName);
	            	add(NewCreateWord);
	            	NewCreateWord.setVisible(true);
	            	FolderHolderFiles.AddFileToFolder(NewCreateWord);
	                FolderAction.setEnabled(true);
	                ShowAllDocumentsFolder.setEnabled(true);
	            	}
	            	}
				}
			});
           
            FolderAction.addActionListener(new ActionListener() { // open window for Folder Action ,edit,delete
				
				@Override
				public void actionPerformed(ActionEvent e) {	
					add(FolderHolderFiles);
					FolderHolderFiles.setVisible(true);
				}
			}); 
            
            ShowAllDocumentsFolder.addActionListener(new ActionListener() { // show alll file that in folder
				
				@Override
				public void actionPerformed(ActionEvent e) {	
		     	FolderHolderFiles.showAllDocuments();
					
				}
			}); 

           
           setJMenuBar(menubar); 
    }
}  
   
