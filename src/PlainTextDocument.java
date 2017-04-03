import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledEditorKit.ForegroundAction;


public class PlainTextDocument extends Document{     // extends class Document( Document extends jpanel) 
	
	private JTextArea TextArea ; //text for create new text 
	private JButton saveButton ;
	private PrintWriter Writer;
	
	PlainTextDocument(String fileName)
	{
		super(fileName, 10);
		
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  // size of a screen
	    double height = screenSize.getHeight();
	    double width = screenSize.getWidth();
		
		
		this.setVisible(false);
		setLayout(null);
		
	    TextArea = new JTextArea(); 
	    saveButton = new JButton("save");
		add(TextArea);	
		add(saveButton);
		

		
		Insets insets = this.getInsets();
		Dimension size = saveButton.getPreferredSize();
	
		saveButton.setBounds(((int)width/2) + insets.left-size.width+20, ((int)height-100) + insets.top,size.width+30, size.height);	
		TextArea.setBounds(((int)width/2-400) + insets.left, 10 + insets.top,size.width+700,((int)height-150));
		
		
		saveButton.addActionListener(new ActionListener() { //save the txt in the TextArea and creat no more 10 words in line

		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		    	String textString = TextArea.getText();
		    	TextArea.setText("");
		    	String lines[] = textString.split("\\s");		    	
		    	int LineDown=getMaxWordInLine();
		    	try {
					Writer = new PrintWriter(fileName.toString()+".txt", "UTF-8");
				} catch (FileNotFoundException | UnsupportedEncodingException e) {  // catch Exception 
					
					JOptionPane.showMessageDialog(null,"Nothing good came of this exception , can not create a file sorry ):  ");
		
				}
		    	for (int i = 0; i < lines.length; i++) {
		    		if(i==LineDown)
		    		{
		    			Writer.println("\n");
		    			setText("\n");
		    			LineDown=LineDown+10;
		    		}
		    		setText(lines[i]);
		    		Writer.print(lines[i]);
		    		setText(" ");
		    		Writer.print(" ");
		    							
				}
		    	Writer.close();	
		    	setVisibleObj();
		    	System.out.print(getText());		    	
		    	setSizeFile(lines.length);   
		    	System.out.println("\n");
		    	System.out.println("----------------------------------------------------------------");

		    }
		});

	}
	public void setVisibleObj() {//when done with that obj set to no visble
		
		 setVisible(false);
	}
	
	@Override
	public String toString() { 
         return super.toString();
    }
	
	
	
	public void setNewText(String newText) { //set new text - delete old one from memory and take the new also no moire than 10 words in line
    	String textString = TextArea.getText();
    	TextArea.setText("");
    	
    	String lines[] = newText.split("\\s");		    	
    	int LineDown=getMaxWordInLine();
    	try {
			Writer = new PrintWriter(getFileName().toString()+".txt", "UTF-8");
			ClearText();
			Writer.print("");
			Writer.close();	
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			
			JOptionPane.showMessageDialog(null,"Nothing good came of this exception , can not create a file sorry ):  ");

		}
    	for (int i = 0; i < lines.length; i++) {// go line down 10 words 
    		if(i==LineDown)
    		{
    			Writer.println("\n");
    			setText("\n");
    			LineDown=LineDown+10;
    		}
    		setText(lines[i]);
    		Writer.print(lines[i]);
    		setText(" ");
    		Writer.print(" ");    							
		}
    	
    	Writer.close();	
    	setVisibleObj();
    	System.out.print(getText());		    	
    	setSizeFile(lines.length);   
    	System.out.println("\n");
    	System.out.println("----------------------------------------------------------------");
    	}
		
	}







