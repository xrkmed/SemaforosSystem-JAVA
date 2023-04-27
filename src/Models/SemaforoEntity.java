package Models;

import java.text.SimpleDateFormat;
import java.util.Objects;

import Controllers.SemaforoInterface;
import Controllers.SemaforoStatusInterface;
import Database.SemaforosDAO;
import Models.SemaforoStatus.SemaforosStatus_t;

public class SemaforoEntity implements SemaforoInterface {
	
	private int semaforoId;
	private Location semaforoLocation;
	private SemaforoStatusInterface semaforoStatus;
	private long nextChange;

	private final long DELAY_CONFIG_SEMAFORO_ABERTO_PARA_ATENCAO = 20000;
	private final long DELAY_CONFIG_SEMAFORO_ATENCAO_PARA_FECHADO = 20000;
	private final long DELAY_CONFIG_SEMAFORO_FECHADO_PARA_ABERTO = 20000;
	
	// Construtor
	public SemaforoEntity() {
		this.semaforoStatus = new SemaforoStatus();
		this.semaforoId = new SemaforosDAO().getInstance().getSemaforosCount()+1;
		this.nextChange = System.currentTimeMillis();
	}
	
	// Construtor
	public SemaforoEntity(Location semaforoLocation) {
		this();
		this.semaforoLocation = semaforoLocation;
	}
	
	// Retorna o id do semaforo
	public int getId() {
		return this.semaforoId;
	}
	
	// Retorna a localizacao do semaforo
	public Location getLocation() {
		return semaforoLocation;
	}
	
	// desabilita o semaforo
	public void disable() {
		semaforoStatus.disableSemaforo();
	}
	
	// inicia o semaforo
	public void sync() {		
		updateStatus();
	}
	
	// atualiza o status do semaforo (executar apenas uma vez que o sistema automaticamente faz a verificacao em intervalos de tempos)
	public void updateStatus() {
		String oldStatus = semaforoStatus.getActualStatusString();
		String oldCor = semaforoStatus.getCor();
		String timeString = new SimpleDateFormat("HH:mm:ss.SSSS dd/MM/yyyy").format(new java.util.Date());
		long actualTimeStamp = System.currentTimeMillis();
		if(nextChange > actualTimeStamp)
			return;
		
		switch(semaforoStatus.getStatus()) {
			case SEMAFORO_STATUS_LOADING: case SEMAFORO_STATUS_FECHADO: {
				semaforoStatus.updateStatus(SemaforosStatus_t.SEMAFORO_STATUS_ABERTO);		

				nextChange = actualTimeStamp + DELAY_CONFIG_SEMAFORO_ABERTO_PARA_ATENCAO;
				break;
			}
			
			case SEMAFORO_STATUS_ABERTO: {
				semaforoStatus.updateStatus(SemaforosStatus_t.SEMAFORO_STATUS_ATENCAO);

				nextChange = actualTimeStamp + DELAY_CONFIG_SEMAFORO_ATENCAO_PARA_FECHADO;
				break;
			}
			
			case SEMAFORO_STATUS_ATENCAO: {
				semaforoStatus.updateStatus(SemaforosStatus_t.SEMAFORO_STATUS_FECHADO);

				nextChange = actualTimeStamp + DELAY_CONFIG_SEMAFORO_FECHADO_PARA_ABERTO;
				break;
			}
			
			default: break;
		}
		
		System.out.println("[" + timeString + "] O Semaforo localizado na " + semaforoLocation + " foi de " + oldStatus + " (" + oldCor + ") para " + semaforoStatus.getActualStatusString() + " (" + semaforoStatus.getCor() + ")");
		return;
	}

	@Override
	public int hashCode() {
		return Objects.hash(semaforoId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SemaforoEntity other = (SemaforoEntity) obj;
		return semaforoId == other.semaforoId;
	}

	@Override
	public String toString() {
		return "Semaforo localizado na " + semaforoLocation;
	}

	
	
}
