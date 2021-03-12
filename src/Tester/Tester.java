
/**
 * @author JessicaVecchia and AndreiBlindu
 *
 */

package Tester;
import javax.swing.JFrame;
import Model.*;
import Presenter.*;
import View.*;
import Service.*;

public class Tester {

	public static void main(String[] args) {
		Subject<IDisplayOperation> subjectView = new Subject<>("View"); // il subject view viene istanziato
		Status status = new Status(subjectView); // viene creato lo status e gli si passa il subject view
		Subject<IOperation> subjectOperation = new Subject<>("Action"); // viene creato il subject operation
		subjectOperation.register(status); // si registra lo status come OBSERVER del subject Operation
		OmbraFrame frame = new OmbraFrame(subjectOperation); // si crea il panel/frame e gli si passa il subject operation
		subjectView.register(frame.getPanel()); //si registra il panel al subject operation
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
//		OmbraPanel panel = new OmbraPanel(i);
//		panel.setVisible(true);
	}

}

