package Presenter;
import Model.*;


public class OmbraClearOperation extends Operation{
	
	public OmbraClearOperation (){
		super();
	}
	@Override
	public void actionPerformed(Status status) { 
		status.clear();
		//this.setLabel(status, this.bigDim);
	}

}
