import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JPanel;



public abstract class Document extends JPanel {
	
	  private String FileName;
	  private Date CreateDate;
	  private final  int MaxWordInLine;
	  private StringBuffer Text; // text in the file
	  private int  SizeFile;
	
	  public Document(String fileName , int maxLengthInLine) {
			this.FileName = fileName;
			this.MaxWordInLine = maxLengthInLine; // max word in line  
			Date date = new Date();
			this.CreateDate = date;
			this.SizeFile=0;
		    this.Text=new StringBuffer();

		}  
	  
	  
	  
	  public String getFileName() {
		return FileName;
	}
	public void setFileName(String fileName) {
		FileName = fileName;
	}
	public Date getCreateDate() {
		return CreateDate;
	}

	public  int getMaxWordInLine() {
		return MaxWordInLine;
	}
	public StringBuffer getText() {
		return Text;
	}
	public void setText(String text) {
		Text.append(text);
	}
	public void ClearText() {
		Text.delete(0, Text.length());
	}
	public int getSizeFile() {
		return SizeFile;
	}
	public void setSizeFile(int sizeFile) {
		SizeFile = sizeFile;
	}
	
	public  void setNewText(String string) { //just that i can make this func in the son class
		
	}

}