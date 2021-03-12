package Service;

import java.util.ArrayList;



public class Subject <T> {
	public ArrayList <Observer <T>> observers;
	private String name;
	
	public Subject(String name) {
		this.observers = new ArrayList <Observer <T>>();
		this.name = name;
	}
	
	public void register(Observer <T>observer) {
		this.observers.add(observer);
	}
	
	public void update(T operation) {
		for (Observer <T> observer: this.observers) {
			observer.update(operation);
		}
	}
}


