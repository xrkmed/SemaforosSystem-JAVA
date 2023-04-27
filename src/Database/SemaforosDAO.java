package Database;

import java.util.ArrayList;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import Controllers.SemaforoInterface;
import Exceptions.SemaforoNotFound;
import Models.Location;

public class SemaforosDAO {

	private static ArrayList<SemaforoInterface> semaforos = new ArrayList<>();
	
	//Singleton Class
	private static SemaforosDAO _instance = null;
	

	// Singleton Class Constructor
	public SemaforosDAO getInstance() {
		if(_instance == null) {
			_instance = new SemaforosDAO();
		}
		
		return _instance;
	}
	

	// Desativar semaforo pelo id 
	public boolean disableSemaforoById(int id) throws SemaforoNotFound {
		try {
			SemaforoInterface object = semaforos.stream().filter(semaforoObject -> semaforoObject.getId() == id).collect(Collectors.toList()).get(0);
			object.disable();
			return true;
		}catch(IndexOutOfBoundsException indexException) {
			throw new SemaforoNotFound();
		}
	}
	
	// Ver quantidade total de semaforos ativos
	public int getSemaforosCount() {
		return semaforos.size();
	}
	
	// Adicionar semaforo ao banco de dados DAO
	public SemaforoInterface addSemaforo(SemaforoInterface t) {
		semaforos.add(t);
		return t;
	}
	
	// Obter semaforo pelo id e localizacao
	public SemaforoInterface getSemaforo(BiPredicate<? super Integer, ? super Location> query) throws SemaforoNotFound {
		for(SemaforoInterface semaforoObject : semaforos) {
			if(query.test(semaforoObject.getId(), semaforoObject.getLocation())) {
				return semaforoObject;
			}
		}
		
		throw new SemaforoNotFound();
	}

	// Obter semaforo pelo id
	public SemaforoInterface getSemaforoById(int id) throws SemaforoNotFound {
		try {
			SemaforoInterface object = semaforos.stream().filter(semaforoObject -> semaforoObject.getId() == id).collect(Collectors.toList()).get(0);
			return object;
		}catch(ArrayIndexOutOfBoundsException e) {
			throw new SemaforoNotFound("Nenhum semaforo com o id " + id + " foi encontrado.");
		}
	}
	
	// Obter semaforo pela localizacao
	public SemaforoInterface getSemaforos(Location loc) {
		try {
			SemaforoInterface object = semaforos.stream().filter(semaforoObject -> semaforoObject.getLocation() == loc).collect(Collectors.toList()).get(0);
			return object;
		}catch(ArrayIndexOutOfBoundsException e) {
			throw new SemaforoNotFound("Nenhum semaforo em " + loc + " foi encontrado.");
		}
	}
	
	
}
