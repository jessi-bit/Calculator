package Presenter;

import Model.*;

public abstract class Operation implements IOperation{
protected final int bigDim = 80;
protected final int smallDim = 30;
protected final int mediumDim = 45;
	
	public abstract void actionPerformed(Status status);
	
	public Operation() {
	}
	
	@Override
	public int getDim(Status status) {
		return this.resize(status);
	}
	
	public int resize(Status status) {
		if(status.getCurrentDisplay().length() <= 9) {
			return this.bigDim;
		}
		else if(status.getCurrentDisplay().length() < 20) {
			return this.mediumDim;
		}
		else {
			return this.smallDim;
		}
			
	}
}
