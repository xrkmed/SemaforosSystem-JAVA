package Controllers;

import Models.SemaforoStatus;

public interface SemaforoStatusInterface {
    
    public void disableSemaforo(); // Desativar semaforo
    public void updateStatus(SemaforoStatus.SemaforosStatus_t newStatus); // Atualizar status do semaforo
    public SemaforoStatus.SemaforosStatus_t getStatus(); // Retornar status do semaforo
    public String getActualStatusString(); // Retornar status do semaforo em string
    public String getCor(); // Retornar cor do semaforo
    
}
