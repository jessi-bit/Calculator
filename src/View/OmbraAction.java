package View;


import java.awt.event.*;
import Presenter.*;
import Service.Subject;

public class OmbraAction implements ActionListener{
	private Subject<IOperation> subject;
	private IOperation operation;
	
	public OmbraAction(Subject<IOperation> subject, IOperation operation) { 
		this.subject = subject;
		this.operation = operation;
	}
	
	public void actionPerformed(ActionEvent event) {
		this.subject.update(this.operation); // Presenter emits event
		System.out.println("I'm emitting an event from View to Model");
	}
	

}
