package Presenter;
import Model.*;

public class OmbraCipherOperation extends Operation{
	private String cipher;

	public OmbraCipherOperation(String cipher){
		super();
		this.cipher = cipher;
	}
	@Override
	public void actionPerformed(Status status) { 
		status.cipherInserted(this.cipher);
		//this.setLabel(status, this.smallDim);
	}
}
