package Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ZonesUpdate {
	
	//Essa classe serve para diluir as regioes em uma matriz para diminuir o uso da CPU conforme tenha muitas regioes.
	
	private final int ZONES_QUANTITY = 10; // quanto maior o valor, mais diluido vai ser
	private final long ZONES_INTERVAL = 1000L; // quanto maior aqui, mais demorado vai ser para o sistema verificar os semaforos.
	private HashMap<Integer, ArrayList<Zones>> zonesMatrix = new HashMap<>();
	
	//Singleton class
	private static ZonesUpdate _instance = null;
	
	public ZonesUpdate getInstance() {
		if(_instance == null) {
			_instance = new ZonesUpdate();
		}
		
		return _instance;
	}
	
	public ZonesUpdate() {
		//TODO
	}
	
	public void onThink(int index) {
		EventScheduler scheduler = new EventScheduler();
		scheduler.addEvent(ZONES_INTERVAL, () -> {this.onThink((index + 1) % ZONES_QUANTITY); });
		ArrayList<Zones> zone = zonesMatrix.getOrDefault(index, new ArrayList<Zones>());
		System.out.println("Verificando index " + index);
		for(Zones zoneObject : zone) {
			zoneObject.syncSemaforos();
		}
	}
	
	public void addZoneMatrix(Zones zone) {
		if(zone.isRunning())
			return;
		
		Random random = new Random();
		int i = random.nextInt(ZONES_QUANTITY)+1;
		if(zonesMatrix.get(i) != null) {
			zonesMatrix.get(i).add(zone);
		}else {
			ArrayList<Zones> zoneInit = new ArrayList<>();
			zoneInit.add(zone);
			zonesMatrix.put(i, zoneInit);
		}
		
		zone.setRunning(true);
	}

}
