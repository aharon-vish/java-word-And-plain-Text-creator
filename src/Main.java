import java.awt.EventQueue;

// program that create file in c:\\"name of the folder given by the user" 
//also create folder object ' and tow files docx and plaintxt
public class Main {

	public static void main(String[] args) {
     
        EventQueue.invokeLater(new Runnable() { //start run and set the menu 
            @Override
            public void run() {
            	
                menu Menu = new menu();
                Menu.setVisible(true);
                  
                }
        });
			

	}

}
