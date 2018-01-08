package api.genetic;

import java.util.List;

public interface Algorithmable {
	
	public double getFitness();
	
	public void setFitness(double d);
	
	public void findFitness(Object target);
	
	public Algorithmable cross(Algorithmable a);
	
	public void mutate();
	
}
