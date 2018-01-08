package api.neural;

public interface Neuron {
	Neuron[] neighbors = null ;
	int   [] weights   = null ;
	int      bias      =   0  ;
	int      state     =   0  ;
	int      output    =   0  ;
	
}