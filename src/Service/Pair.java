package Service;
import javax.swing.JButton;
import Presenter.*;

public class Pair<T extends Operation, U extends JButton> { //every class extending OmbraAction and JButton

	public T operation;
	public U button;
	
	public Pair(T operation, U button) {
		this.operation = operation;
		this.button = button;
	}

	
}
