package Models;

import java.util.ArrayList;

import Controllers.SemaforoInterface;
import Database.SemaforosDAO;

public class Zones {
	
	private static int zonesId = 0;
	private ArrayList<Location> zonesLocations = new ArrayList<>();
	private int zoneId;
	
	public Zones() {
		this.zoneId = Zones.zonesId;
		Zones.zonesId++;
	}
	
	public void addLocation(Location loc) {
		zonesLocations.add(loc);
	}
	
	public ArrayList<SemaforoInterface> getSemaforosList(){
		ArrayList<SemaforoInterface> result = new ArrayList<>();
		
		for(Location loc : zonesLocations) {
			SemaforoInterface semaforo = new SemaforosDAO().getInstance().getSemaforos(loc);
			result.add(semaforo);
			System.out.println("> Log: adicionado semaforo " + semaforo);
		}
		
		return result;
	}
	
	public void syncSemaforos() {
		ArrayList<SemaforoInterface> semaforosList = getSemaforosList();
		for(SemaforoInterface object : semaforosList) {
			object.start();
		}
	}
}
