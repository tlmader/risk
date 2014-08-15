import java.io.OutputStream;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 * Used to wrapper a window.
 * This is utilized in order to allow easy printing of messages on the Risk GUI.
 * @author Ted Mader
 * @version Alpha
 * @date 5/02/14
 **/
public class TextAreaOutputStream extends OutputStream {

    private JTextArea textArea;
    private StringBuilder stringBuilder = new StringBuilder();

    public TextAreaOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }
	
	
	
	public void write(int b) {
	//Writes the specified byte to this output stream	
		
		if (b == '\n') {
		//If text is on a new line, creates a new String
			final String text = stringBuilder.toString();
			
			SwingUtilities.invokeLater(new Runnable() {
				public void run()
				{
					textArea.append(text);
				}
            });
			
            stringBuilder.setLength(0);
        }

        stringBuilder.append((char) b);
    }
}