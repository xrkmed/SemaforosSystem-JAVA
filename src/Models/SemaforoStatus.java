package Models;

import Controllers.SemaforoStatusInterface;

public class SemaforoStatus implements SemaforoStatusInterface {
	
	public enum SemaforosStatus_t{
		SEMAFORO_STATUS_LOADING,
		SEMAFORO_STATUS_ABERTO,
		SEMAFORO_STATUS_ATENCAO,
		SEMAFORO_STATUS_FECHADO,
		SEMAFORO_STATUS_DESATIVADO
	}
	
	private SemaforosStatus_t semaforoStatus;
	private long lastUpdated = System.currentTimeMillis() % 1000;
	
	public SemaforoStatus() {
		this.semaforoStatus = SemaforosStatus_t.SEMAFORO_STATUS_LOADING;
	}
	
	public SemaforoStatus(SemaforosStatus_t type) {
		this.semaforoStatus = type;
	}
	
	public void disableSemaforo() {
		semaforoStatus = SemaforosStatus_t.SEMAFORO_STATUS_DESATIVADO;
	}
	
	public void updateStatus(SemaforosStatus_t newStatus) {
		semaforoStatus = newStatus;
	}
	
	public SemaforosStatus_t getStatus() {
		return semaforoStatus;
	}
	
	public String getActualStatusString() {
		switch(semaforoStatus) {
			case SEMAFORO_STATUS_ABERTO: {
				return "aberto";
			}
			case SEMAFORO_STATUS_ATENCAO: {
				return "prestes a fechar";
			}
			case SEMAFORO_STATUS_FECHADO: {
				return "fechado";
			}
			case SEMAFORO_STATUS_DESATIVADO: {
				return "desativado";
			}
			default:
				return "Manutencao";
		}
	}
	
	public String getCor() {
		switch(semaforoStatus) {
		case SEMAFORO_STATUS_ABERTO: {
			return "verde";
		}
		case SEMAFORO_STATUS_ATENCAO: {
			return "laranja";
		}
		case SEMAFORO_STATUS_FECHADO: {
			return "vermelho";
		}
		case SEMAFORO_STATUS_DESATIVADO: {
			return "alternada";
		}
		default:
			return "desligado";
	}
	}
	

}
