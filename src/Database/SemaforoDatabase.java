package Database;

import Controllers.SemaforoInterface;
import Exceptions.SemaforoNotFound;
import Models.Location;
import Models.SemaforoEntity;

public class SemaforoDatabase {
	
	public static SemaforosDAO instance = new SemaforosDAO().getInstance();
	
	public SemaforoInterface registerSemaforo(SemaforoInterface t) {
		return instance.addSemaforo(t);		
	}
	
	public SemaforoInterface registerSemaforo(Location loc) {
		SemaforoInterface t = new SemaforoEntity(loc);
		return instance.addSemaforo(t);
	}
	
	public boolean desabilitarSemaforo(int id) {
		try {
			return instance.disableSemaforoById(id);
		}catch(SemaforoNotFound exception) {
			System.out.println("Nao foi possivel desabilitar o semaforo de id " + id + " pois nao foi encontrado nenhum semaforo com este id em ativo.");
		}
		
		return false;
	}
	

}
