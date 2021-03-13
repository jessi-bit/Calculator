package Model;
import java.util.ArrayList;
import java.util.Objects;
import Presenter.*;

import Service.*;


public class Status implements Observer<IOperation> {
	private Subject <IDisplayOperation>subjectView;
	private String buffer;
	private ArrayList <String> buffers;
	private ArrayList <OperationTypes> operations;
	private String currentDisplay;
	private boolean isNotResult = true;
	
	public Status(Subject <IDisplayOperation> subjectView) {
		this.buffer = ""; 
		this.currentDisplay = "0";
		//this.operation = null;
		this.buffers = new ArrayList<String>();
		this.operations = new ArrayList<OperationTypes>();
		this.subjectView = subjectView;
	}
	
	public String getCurrentDisplay() {
		return this.currentDisplay;
	}
	
	public void cipherInserted(String inserted) {
		if(currentDisplay.equals("0")) {
			this.currentDisplay = inserted;
			this.buffer = inserted;
		} else {                                  
			this.currentDisplay += inserted;  
			this.buffer += inserted;
		}		
	}
	
	public void operatorInserted(OperationTypes operator) {
		this.currentDisplay += this.displayOperationSymbol(operator);
		if (this.isNotResult && ! this.buffer.equals("")) {
			this.buffers.add(this.buffer); 
		} else {
			this.isNotResult = true;
		}
		this.buffer = "";
		this.operations.add(operator);
		System.out.println(this.buffers);
		System.out.println(this.buffers.size());		
	}
	
	public String displayOperationSymbol(OperationTypes operation) {
		switch(operation) { 
		case SUM : return " + ";  //this format is useful to pay attention to for method lastInsertedIsCipher()
		case SUB : return " - ";
		case MUL : return " x ";
		case DIV : return " / ";
		default: return null;
		}
	}
	
	public int countMulDivOperators() { //count Mul and Div Operators
		int j = 0;
		for(int i = 0; i < this.operations.size()-1; i++) {
			if(this.operations.get(i).equals(OperationTypes.MUL) || this.operations.get(i).equals(OperationTypes.DIV)){
				j++;
			}
		}
		return j;
	}
	
	public void execute() throws Exception  {
		try {
			this.executeMulDiv();
			this.executeSumSub();
			this.currentDisplay = ""+this.buffers.get(0);
			this.isNotResult = false;

			this.operations = new ArrayList<OperationTypes>();
			System.out.println(this.buffers);
			System.out.println(this.operations);
		} catch (Exception e) {
			this.currentDisplay = "Digit Error! Press AC to reset";
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}
	}

	public void executeMulDiv() throws Exception {
		double result = 0;
		int n = this.countMulDivOperators();
		int i= 0; 
		int j = 0; 
		while(j < n) {
			if(this.operations.get(i).equals(OperationTypes.MUL) || this.operations.get(i).equals(OperationTypes.DIV) ) {
				j++;
				switch(this.operations.get(i)) {
				case MUL : {
					result = Double.parseDouble(this.buffers.get(i)) * Double.parseDouble(this.buffers.get(i+1));
					break;
				}
				case DIV : {
					result = Double.parseDouble(this.buffers.get(i)) / Double.parseDouble(this.buffers.get(i+1));
					break;
				}
				default:
					break;

				}
				this.buffers.remove(i+1);
				this.buffers.set(i, ""+result);

				this.buffers.remove(Objects.isNull(this.buffers));//the next operation in a list is made on the total result and not only on the last cipher inserted.
				this.operations.remove(i);
			}
			else {i++;}
		}

	}

	public void executeSumSub() throws Exception {
		double result = 0;
		for(int i = 0; i < this.operations.size()-1; i++) { //this.operations.Size(last) = "="; Exception e
			switch(this.operations.get(i)) {
			case SUM : {
				result = Double.parseDouble(this.buffers.get(0)) + Double.parseDouble(this.buffers.get(1));
				break;
			}
			case SUB : {
				result = Double.parseDouble(this.buffers.get(0)) - Double.parseDouble(this.buffers.get(1));
				break;
			}
			default : break;
			}

			this.buffers.remove(1);
			this.buffers.set(0, ""+result);

		}

	}

	public void clear() {
		this.isNotResult = true;
		this.buffers = new ArrayList<String>();
		this.operations = new ArrayList<OperationTypes>();
		this.currentDisplay = "0";
	}
	
	public void canc() {
		if (this.isNotResult) {
			if(this.lastInsertedIsCipher()) {
				this.currentDisplay = this.currentDisplay.substring(0, this.currentDisplay.length()-1);
				this.buffer = this.buffer.substring(0, this.buffer.length()-1);
			} else {
				this.currentDisplay = this.currentDisplay.substring(0, this.currentDisplay.length()-3);
				this.operations.remove(this.operations.size()-1);
			}			
		}
	}
	
	private boolean lastInsertedIsCipher() {
		char lastChar = this.currentDisplay.charAt(this.currentDisplay.length()-1);
		return !(lastChar == ' '); 
		// if the last thing inserted is an operator then the last char will be a space
	}

	@Override
	public void update(IOperation operation) {
		operation.actionPerformed(this);
		this.subjectView.update(new DisplayOperation(this.currentDisplay, operation.getDim(this)));
	}
		
}

//5x5x5/25 + 4x2
	// buffers : 5,5,5,25,4,2
	// operators : x,x,/,+,x  
	// n = 4 (0-3)
	// i=0; j=0;
	// w1) 
	/*		i=0;
	 * 		j=1;
	 * 		buffers : 25,5,25,4,2
	 * 		operators: x,/,+,x
	 * w2)
	 * 		i=0
	 * 		j=2
	 * 		buffers : 125,25,4,2
	 * 		operators: /,+,x
	 * w3)  i=0
	 * 		j=3
	 * 		buffers: 5,4,2
	 * 		operators: +,x
	 * w4)  i=1
	 * 		j=3
	 * 		buffers : 5,4,2
	 * 		operators : +,x
	 * w5)  i=1
	 * 		j=4
	 * 		buffers : 5,8
	 * 		operators : +
	*/