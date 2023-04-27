package Exceptions;

import Controllers.SemaforoInterface;
import Models.SemaforoEntity;

public class SemaforoNotFound extends RuntimeException {
	
	
	public SemaforoNotFound(SemaforoInterface e) {
		super("Nenhum semaforo do tipo " + e + " foi encontrado.");
	}
	
	public SemaforoNotFound(String msg) {
		super(msg);
	}
	
	public SemaforoNotFound() {
		super("Nenhum semaforo foi encontrado na busca interna dos semaforos em funcionamento.");
	}

}
