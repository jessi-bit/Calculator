package Service;
import Presenter.*;


public interface Observer <T> {
	void update(T operation);
}
