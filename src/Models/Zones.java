package Models;

import java.util.ArrayList;

import Controllers.SemaforoInterface;
import Database.SemaforosDAO;

public class Zones {
	
	private static int zonesId = 0;
	private ArrayList<Location> zonesLocations = new ArrayList<>();
	private int zoneId;
	private boolean isRunning;
	
	public Zones() {
		this.zoneId = Zones.zonesId;
		this.isRunning = false;
		Zones.zonesId++;
	}
	
	public boolean isRunning() {
		return isRunning;
	}
	
	public void setRunning(boolean value) {
		this.isRunning = value;
	}
	
	public void addLocation(Location loc) {
		zonesLocations.add(loc);
	}

	public int getZoneId(){
		return zoneId;
	}
	
	// Retorna todos os semaforos daquela zona, neste sistema de semaforos, cada zona possui varios semaforos, bastante utilizado este sistema na vida real para garantir um fluxo continuo de uma via!
	public ArrayList<SemaforoInterface> getSemaforosList(){
		ArrayList<SemaforoInterface> result = new ArrayList<>();
		
		for(Location loc : zonesLocations) {
			SemaforoInterface semaforo = new SemaforosDAO().getInstance().getSemaforos(loc);
			result.add(semaforo);
		}
		
		return result;
	}
	
	// Usado apenas na inicializacao do sistema para iniciar todos os semaforos.
	public void syncSemaforos() {
		ArrayList<SemaforoInterface> semaforosList = getSemaforosList();
		for(SemaforoInterface object : semaforosList) {
			object.sync();
		}
	}
}
