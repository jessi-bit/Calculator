package View;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;



import Model.*;
import Presenter.*;
import Service.Observer;
import Service.Pair;
import Service.Subject;

public class OmbraPanel extends JPanel implements Observer <IDisplayOperation>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel label;
	
	public OmbraPanel (Subject <IOperation> subject) { 
		this.setLayout(new BorderLayout()); 
		
		// CREATE 3 COINTAINER 
		JPanel north = new JPanel();  //NORTH
		JPanel body = new JPanel(); //CENTER
		JPanel east = new JPanel(); //EAST
		body.setLayout(new GridLayout(5,3, 3, 3)); //DIVIDE IN row, colums and give gaps
		east.setLayout(new GridLayout(5,1, 3, 3));
		
		ArrayList<Pair<Operation, JButton>> buttonPairs = new ArrayList<>();
		ArrayList<Pair<Operation, JButton>> buttonOperatorPairs = new ArrayList<>();
		
		for(int i = 9; i >= 1; i--) { // Create buttons of ciphers, set buttons Layout and instantiate CipherOperation Action. Then, add in the pair the instance of the action and the button.
			JButton button = new JButton(""+i); 
			button.setBackground(Color.WHITE);
			OmbraCipherOperation operation = new OmbraCipherOperation(""+i);
			buttonPairs.add(new Pair<Operation, JButton>(operation, button));
		}

		JButton zero = new JButton("0");
		JButton execute = new JButton("=");	
		JButton clear = new JButton("AC");	
		JButton dot = new JButton(".");
		JButton canc = new JButton("DEL");
		Color VERY_LIGHT_BLUE = new Color(51,204,255);
		Color VERY_LIGHT_RED = new Color(255,102,102);

		buttonPairs.add(new Pair<Operation, JButton>(new OmbraCipherOperation("."), dot));
		buttonPairs.add(new Pair<Operation, JButton>(new OmbraCipherOperation("0"), zero));
		buttonPairs.add(new Pair<Operation, JButton>(new OmbraExecuteOperation(), execute));
		buttonPairs.add(new Pair<Operation, JButton>(new OmbraClearOperation(), clear));
		buttonPairs.add(new Pair<Operation, JButton>(new OmbraCancOperation(), canc));
		
		buttonOperatorPairs.add(new Pair<Operation, JButton>(new OmbraOperatorOperation(OperationTypes.SUM), new JButton("+")));
		buttonOperatorPairs.add(new Pair<Operation, JButton>(new OmbraOperatorOperation(OperationTypes.SUB), new JButton("-")));
		buttonOperatorPairs.add(new Pair<Operation, JButton>(new OmbraOperatorOperation(OperationTypes.MUL),  new JButton("X")));
		buttonOperatorPairs.add(new Pair<Operation, JButton>(new OmbraOperatorOperation( OperationTypes.DIV), new JButton("/")));
		
		// layout
		dot.setBackground(Color.WHITE);
		execute.setBackground(VERY_LIGHT_BLUE);
		clear.setBackground( VERY_LIGHT_RED);
		zero.setBackground(Color.WHITE);
		canc.setBackground(VERY_LIGHT_RED);
		
		Font font = (new Font("Synchro LET", Font.PLAIN, 30));
		
		for (Pair<Operation, JButton> pair : buttonPairs) { // scorro tutti gli elementi della lista di coppie
			pair.button.addActionListener(new OmbraAction(subject, pair.operation)); // operazione di binding (collegamento) tra bottone ed azione corrispondente
			pair.button.setFont(font); // setto il font
			pair.button.setForeground(Color.BLACK);
			body.add(pair.button); // aggiungo il bottone al pannello
		}
		
		for (Pair<Operation, JButton> pair : buttonOperatorPairs) { // scorro tutti gli elementi della lista di coppie
			pair.button.addActionListener(new OmbraAction(subject, pair.operation)); // operazione di binding (collegamento) tra bottone ed azione corrispondente
			pair.button.setFont(font); // setto il font
			pair.button.setForeground(Color.BLACK);
			east.add(pair.button); // aggiungo il bottone al pannello
		}
		
		this.label = new JLabel("0");
		label.setFont(new Font("Synchro LET", Font.PLAIN, 80));
		label.setAlignmentX(100); //label.LEFT_ALIGNMENT
		label.setAlignmentY(100);
		label.setForeground(Color.GREEN);
		north.add(this.label);
		north.setBackground(Color.BLACK);
		north.setPreferredSize(new java.awt.Dimension(300, 150));
		east.setPreferredSize(new java.awt.Dimension(120, 30));
		
		this.add(north, BorderLayout.NORTH);
		this.add(body, BorderLayout.CENTER);
		this.add(east, BorderLayout.EAST);
		
	}
	
	public JLabel getLabel() {
		return label;
	}

	@Override
	public void update(IDisplayOperation operation) {
		this.getLabel().setFont(new Font("Synchro LET", Font.PLAIN, operation.getDim()));
		this.getLabel().setText(operation.getResult()); //the method setText allows me to write what I want in the label//In this case I want the value of the counter
				
	}
}


//public String getActionCommand()
//Returns the command name of the action event fired by this button. If the command name is null (default) then this method returns the label of the button.

//label2.setAlignmentX(0);
//label2.setAlignmentY(50);
//Dimension size = label.getPreferredSize();
//label.setBounds(90, 100, size.width, size.height);
