import UI.EchoFrame;
import javax.swing.JFrame;
import ws.schild.jave.Encoder;
import ws.schild.jave.MultimediaObject;

@SuppressWarnings("unused")
public class Main {
	
	public static void init() {
		System.out.println("Ayo");
		// Run initalization of program, start UI, etc.
		// This will run when the app is started.
                EchoFrame ef = new EchoFrame();
                ef.setSize(512, 512);
                ef.setTitle("Echo Master");
                ef.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ef.setVisible(true);
                System.out.println(ef);
	}

	public static void main(String[] args) {
		System.out.println("Attempting to initialize EchoMaster...");
		init();
	}
}
