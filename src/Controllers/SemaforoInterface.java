package Controllers;

import Models.Location;

public interface SemaforoInterface {

    public int getId(); // retornar o id do semaforo
    public Location getLocation(); // retornar a localizacao do semaforo
    public void disable(); // desabilitar o semaforo
    public void start(); // iniciar o semaforo
    public void updateStatus(); // atualizar o status do semaforo

}
