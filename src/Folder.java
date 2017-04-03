import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Menu;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class Folder extends JPanel {
	
	private String FolderName;
	private ArrayList <Document> NameOfFilesInFolder;
	private JTextArea TextArea ; 
	private JButton saveButton ;
	public Document doc;
	Folder (String folderName )
	{   
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  // size of a screen
	    double height = screenSize.getHeight();
	    double width = screenSize.getWidth();
		
	    this.setVisible(false);
	    setLayout(null);
		
	     TextArea = new JTextArea();
	     TextArea.setEditable(false); 
	     JButton openButton = new JButton("open file to edit");
	     JButton saveButton = new JButton("save file ");
	     JButton DeleteFile = new JButton("Delete File");
	     JButton close = new JButton("close");
	    
	    add(TextArea);
	    add(openButton);
	    add(saveButton);
	    add(DeleteFile);
	    add(close);
	    
	    Insets insets = this.getInsets();
		Dimension size = saveButton.getPreferredSize();
		
		saveButton.setBounds((((int)width/2)-insets.left-200) , ((int)height-100) + insets.top,size.width+30, size.height);
		close.setBounds((((int)width/2) -insets.left+200), ((int)height-100) + insets.top,size.width+30, size.height);
		openButton.setBounds((((int)width/2)-insets.left-400) , ((int)height-100) + insets.top,size.width+60, size.height);
		DeleteFile.setBounds((((int)width/2) -insets.left), ((int)height-100) + insets.top,size.width+30, size.height);		
		TextArea.setBounds(((int)width/2-400) + insets.left, 10 + insets.top,size.width+700,((int)height-150));	
		
		try{
		
	
		setFolderName("C:\\"+folderName);
     	File file = new File(getFolderName());
     	if (!file.exists()&&!folderName.contains("null")) {
     		if (file.mkdir()) {
     			JOptionPane.showMessageDialog(null,"Directory in c:\\"+folderName+" created!");
     			menu.errorCreateFolder=true;
     			 this.NameOfFilesInFolder= new ArrayList<Document>(100);
     		
     		} else {
     			JOptionPane.showMessageDialog(null,"Failed to create directory!","Failed",JOptionPane.WARNING_MESSAGE);
     		
     		}
     	} else {
     		JOptionPane.showMessageDialog(null,"Failed to create directory !","Failed",JOptionPane.WARNING_MESSAGE);
     		
		}
	}
	catch(NullPointerException e) {
		JOptionPane.showMessageDialog(null,"Failed to create directory !","Failed",JOptionPane.WARNING_MESSAGE);
	}
     
		openButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		    
		       ArrayList <Document> getFiles;
		      	 getFiles =getFiles(); 	
		      	 List <String> myArrayList = new ArrayList<String>();  	
		      	 ArrayList <String> filename = null;       
		      	
		      	 for (Document document : getFiles) {
		      		 myArrayList.add(document.getFileName());
		      		 
		   		}
		      	
		      	 String[] myArray = myArrayList.toArray(new String[myArrayList.size()]);              
		      	String file = (String) JOptionPane.showInputDialog(null, 
		                      "Which file do you want?",
		                      "File edit",
		                      JOptionPane.QUESTION_MESSAGE, 
		                      null, 
		                      myArray,
		                      myArray[0]
		                      );
		     	for (Document document : NameOfFilesInFolder) {

		   	if(document.getFileName().contains(file))
		   	{
		   		doc=document;
		   		
		   		TextArea.setText(document.getText().toString());
		   		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		   		JOptionPane.showMessageDialog(null,"Name file:" +doc.getFileName()+ " Date: "+ dateFormat.format(doc.getCreateDate()).toString());
		   		TextArea.setEditable(true);
		   	}
		       }
		    	
		    	
		    	
		    }
		});
	    
		saveButton.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		    	
		    	doc.setNewText(TextArea.getText());
		    	setVisibleObj();
		    	
		    }
		});
	   
	    
	    close.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		    	setVisibleObj();
		    }
		});
	    
	    DeleteFile.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		    	
	    		 try{
		    		 String pathfile=doc.getFileName()+".txt";
		    		 File file = new File(pathfile);
		    		 file.delete();		    		 
		    		 NameOfFilesInFolder.remove(doc);
		    		 JOptionPane.showMessageDialog(null,"Bye Bye evil file");
		    		 setVisibleObj();
	    		 }
	    		 catch(Exception e){
	    			 JOptionPane.showMessageDialog(null,"NOT GOOD Something happened,File is not deleted :( ");
	    		 }
		    
		    		
		    	}
		});
	
	}
	
	
	
	  public ArrayList <Document> getFiles() {
		return NameOfFilesInFolder;
	}
	  
	public void AddFileToFolder(Document file) {
		NameOfFilesInFolder.add(file);		
	}
	
	public void RemoveFileFromFolder(Document file) {
		NameOfFilesInFolder.remove(file);
			
		}	
	
    public void showAllDocuments ()
    {
    	String ALLfile="";
    	for (Document document : NameOfFilesInFolder) {
		     ALLfile+=document.getFileName().toString();
		     ALLfile+="\n";
		}
    	
	     JOptionPane.showMessageDialog(null,ALLfile);
		System.out.println(ALLfile);
	}



    public  String getFolderName() {
		return FolderName;
	}



    public  void setFolderName(String folderName) {
		FolderName = folderName;
	}
    
   
    public Boolean FindFile(String file){

    	for (Document document : NameOfFilesInFolder) {

	if(document.getFileName().equals(file))
	{
		return true;		
	}
}
    	return false;
    }
    
  	public void setVisibleObj() {
		TextArea.setText("");
		 setVisible(false);
		 this.invalidate();
		
		 
	}
	 
    
}


	
	
	
	


