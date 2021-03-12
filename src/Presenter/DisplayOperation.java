package Presenter;

public class DisplayOperation implements IDisplayOperation{
	private String result;
	private int dim;
	
	public DisplayOperation(String result, int dim){
		this.result = result;
		this.dim = dim;
	
	}

	@Override
	public int getDim() {
		return this.dim;
	}

	@Override
	public String getResult() {
		return this.result;
	}
	
}
