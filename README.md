
# Sistema de Semaforos Sincronizados em Zona Urbana

O sistema de semáforos em zonas urbanas é útil pois ajuda a controlar o fluxo de tráfego e garantir a segurança dos usuários da via. Ao regulamentar o tempo de espera e passagem de veículos e pedestres em cada cruzamento, os semáforos ajudam a evitar congestionamentos e acidentes de trânsito. 
## Autores

- [@xrkmed](https://www.github.com/xrkmed)


## Funcionalidades


- Controlar o fluxo de tráfego de veículos e pedestres em cruzamentos;

- Regular o tempo de espera e passagem nos cruzamentos para evitar congestionamentos e acidentes de trânsito;

- Monitorar o tráfego em tempo real e fazer ajustes para melhorar a eficiência do fluxo de veículos;

- Incentivar o uso de meios de transporte mais sustentáveis, como bicicletas e transporte público, reduzindo as emissões de gases poluentes na atmosfera.## Uso/Testes

```
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
```
## Screenshots

![Log do sistema](https://i.imgur.com/eWFo17A.png)

