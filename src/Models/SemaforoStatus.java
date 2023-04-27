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
	
	public SemaforoStatus() {
		this.semaforoStatus = SemaforosStatus_t.SEMAFORO_STATUS_LOADING;
	}
	
	public SemaforoStatus(SemaforosStatus_t type) {
		this.semaforoStatus = type;
	}
	
	// Desativa o semaforo (usado para madrugadas obs: funcao de madrugada ainda nao implementada)
	public void disableSemaforo() {
		semaforoStatus = SemaforosStatus_t.SEMAFORO_STATUS_DESATIVADO;
	}
	
	// Atualiza o status do semaforo
	public void updateStatus(SemaforosStatus_t newStatus) {
		semaforoStatus = newStatus;
	}
	
	// Retorna o status do semaforo
	public SemaforosStatus_t getStatus() {
		return semaforoStatus;
	}

	// Retorna o status do semaforo em string
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
	
	// Retorna a cor do semaforo
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
