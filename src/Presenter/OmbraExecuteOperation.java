package Presenter;
import Model.*;


public class OmbraExecuteOperation extends Operation{

	
	public OmbraExecuteOperation (){
		super();
	}
	@Override
	public void actionPerformed(Status status)   { 
		status.operatorInserted(OperationTypes.EQUAL); 
		try {
			status.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		//this.setLabel(status, this.bigDim);
	}
	
}


