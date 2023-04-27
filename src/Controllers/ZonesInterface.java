package Controllers;

import java.util.ArrayList;

import Models.Location;

public interface ZonesInterface {
    
    public void addLocation(Location loc);
    public ArrayList<SemaforoInterface> getSemaforosList();
    public void syncSemaforos();
    
}
