package Models;

import java.util.Objects;

public class AddressEntity {
	
	private String rua;
	private String ref;
	private long CEP;
	
	public AddressEntity() {
		
	}
	
	
	//Com os semaforos sao divididos por cidade (administrados pela prefeitura munucipal) nao ha necessidade de colocar estado e cidade.
	public AddressEntity(String rua, String ref, long CEP) {
		this.rua = rua;
		this.ref = ref;
		this.CEP = CEP;
	}


	@Override
	public int hashCode() {
		return Objects.hash(CEP);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressEntity other = (AddressEntity) obj;
		return CEP == other.CEP;
	}
	
	@Override
	public String toString() {
		return "{Rua: " + rua + ", Referencia: " + ref + ", CEP: " + CEP + "}";
	}
	
	

}
