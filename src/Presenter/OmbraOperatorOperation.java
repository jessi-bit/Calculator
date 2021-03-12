package Presenter;
import Model.*;

public class OmbraOperatorOperation extends Operation{
	private OperationTypes operation;
	
	public OmbraOperatorOperation( OperationTypes operation){
		super(); //
		this.operation = operation;
	}
	public void actionPerformed(Status status) { 
		status.operatorInserted(this.operation); 
		//this.setLabel(status,this.smallDim); 
	}
	
}


