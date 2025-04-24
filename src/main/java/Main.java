import UI.EchoFrame;
import javax.swing.JFrame;
import ws.schild.jave.Encoder;
import ws.schild.jave.MultimediaObject;

public class Main {
	
	public void init() {
		// Run initalization of program, start UI, etc.
		// This will run when the app is started.
                System.out.println("Attempting to initialize EchoMaster...");
                EchoFrame ef = new EchoFrame();
                ef.setSize(512, 512);
                ef.setTitle("Echo Master");
                ef.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ef.setVisible(true);
	}

	public static void main(String[] args) {
                Main main = new Main();
                main.init();
		
	}
}
