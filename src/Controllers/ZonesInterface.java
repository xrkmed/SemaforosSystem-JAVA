package Controllers;

import java.util.ArrayList;

import Models.Location;

public interface ZonesInterface {
    
    public void addLocation(Location loc); // adicionar uma localizacao
    public ArrayList<SemaforoInterface> getSemaforosList(); // retornar a lista de semaforos
    public void syncSemaforos(); // sincronizar os semaforos
    
}
