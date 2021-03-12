package View;
import java.awt.*;
import Presenter.*;
import Service.*;
import javax.swing.*;


public class OmbraFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OmbraPanel panel;
	
	public OmbraFrame(Subject<IOperation> subject){
		setTitle("OMBRA CALCULATOR");
		setSize(500, 700);
		this.panel = new OmbraPanel(subject); //gli do il contatore al panel
		Container c = getContentPane(); 
		c.add(this.panel);
	}
	
	public OmbraPanel getPanel() {
		return this.panel;
	}
	
}
