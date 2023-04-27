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
	
	private boolean isRunning;
	
	
	public SemaforoEntity() {
		this.semaforoStatus = new SemaforoStatus();
		this.semaforoId = new SemaforosDAO().getInstance().getSemaforosCount()+1;
		this.isRunning = false;
	}
	
	public SemaforoEntity(Location semaforoLocation) {
		this();
		this.semaforoLocation = semaforoLocation;
	}
	
	public int getId() {
		return this.semaforoId;
	}
	
	public Location getLocation() {
		return semaforoLocation;
	}
	
	public void disable() {
		semaforoStatus.disableSemaforo();
	}
	
	public void start() {
		if(isRunning)
			return;
		
		updateStatus();
		
		isRunning = true;
	}
	
	public void updateStatus() {
		String oldStatus = semaforoStatus.getActualStatusString();
		String oldCor = semaforoStatus.getCor();
		String timeString = new SimpleDateFormat("HH:mm:ss.SSSS dd/MM/yyyy").format(new java.util.Date());
		
		switch(semaforoStatus.getStatus()) {
			case SEMAFORO_STATUS_LOADING: case SEMAFORO_STATUS_FECHADO: {
				semaforoStatus.updateStatus(SemaforosStatus_t.SEMAFORO_STATUS_ABERTO);
				EventScheduler nextInterval = new EventScheduler();
				nextInterval.addEvent(20000,	() -> {this.updateStatus();}); // 20 segundos para atualizar de aberto para atencao
				
				break;
			}
			
			case SEMAFORO_STATUS_ABERTO: {
				semaforoStatus.updateStatus(SemaforosStatus_t.SEMAFORO_STATUS_ATENCAO);
				EventScheduler nextInterval = new EventScheduler();
				nextInterval.addEvent(5000,	() -> {this.updateStatus();}); // 5 segundos para atualizar de atencao para fechado
				
				break;
			}
			
			case SEMAFORO_STATUS_ATENCAO: {
				semaforoStatus.updateStatus(SemaforosStatus_t.SEMAFORO_STATUS_FECHADO);
				
				EventScheduler nextInterval = new EventScheduler();
				nextInterval.addEvent(20000,	() -> {this.updateStatus();}); // 20 segundos para atualizar de fechado para aberto
				break;
			}
			
			default: break;
		}
		
		System.out.println("[" + timeString + "] O Semaforo localizado na " + semaforoLocation + " foi de " + oldStatus + " (" + oldCor + ") para " + semaforoStatus.getActualStatusString() + " (" + semaforoStatus.getCor() + ")");
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
