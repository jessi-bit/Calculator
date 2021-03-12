package Presenter;

import Model.Status;

public class OmbraCancOperation extends Operation{
	public OmbraCancOperation (){
		super();
	}

	@Override
	public void actionPerformed(Status status) {
		status.canc();
		
	}

}
