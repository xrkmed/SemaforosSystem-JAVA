package Views;

import Controllers.SemaforoInterface;
import Database.SemaforoDatabase;
import Models.AddressEntity;
import Models.Location;
import Models.SemaforoEntity;
import Models.Zones;

public class Runner {
	
	public static void main(String[] args) {
		
		System.out.println("Iniciando");
		
		//Database de inicializacao (diferente do DAO)
		SemaforoDatabase semaforoDb = new SemaforoDatabase();
		
		//Criar uma zona (regiao)
		Zones zoneType = new Zones();
		
		//Criando as localizacoes
		Location semaforoLocalizacao1 = new Location(new AddressEntity("R. Dos palmares", "Semaforo localizado na frente do Bazar Martins", 88130000));
		Location semaforoLocalizacao2 = new Location(new AddressEntity("R. Das gracas", "Semaforo localizado ao lado do Hospital Americanas", 88125000));
		
		//Adicionando as localizacoes em uma regiao
		zoneType.addLocation(semaforoLocalizacao1);
		zoneType.addLocation(semaforoLocalizacao2);
		
		//Criar as classes de semaforos
		SemaforoInterface semaforo1 = semaforoDb.registerSemaforo(semaforoLocalizacao1);
		SemaforoInterface semaforo2 = semaforoDb.registerSemaforo(semaforoLocalizacao2);
		
		//Ligar os semaforos
		zoneType.syncSemaforos();
		
		
		//O sistema funciona por regioes, em uma determinada regiao (por exemplo uma via gigante, todos os semaforos sao ligados de uma vez para facilitar o transito continuo)
	}

}
