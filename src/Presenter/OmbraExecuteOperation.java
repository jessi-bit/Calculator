package Presenter;
import Model.*;


public class OmbraExecuteOperation extends Operation{

	
	public OmbraExecuteOperation (){
		super();
	}
	@Override
	public void actionPerformed(Status status) { 
		status.operatorInserted(OperationTypes.EQUAL); 
		status.execute();
		//this.setLabel(status, this.bigDim);
	}
	
}


