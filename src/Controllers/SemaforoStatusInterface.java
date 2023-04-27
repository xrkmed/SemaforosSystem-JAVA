package Controllers;

import Models.SemaforoStatus;

public interface SemaforoStatusInterface {
    
    public void disableSemaforo();
    public void updateStatus(SemaforoStatus.SemaforosStatus_t newStatus);
    public SemaforoStatus.SemaforosStatus_t getStatus();
    public String getActualStatusString();
    public String getCor();
    
}
