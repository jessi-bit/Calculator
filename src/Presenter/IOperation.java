package Presenter;

import Model.*;
//towards status
public interface IOperation {
	void actionPerformed(Status status);
	int getDim(Status status);
}
