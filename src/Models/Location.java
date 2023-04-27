package Models;

import java.util.Objects;

public class Location {
	
	private AddressEntity address;
	
	
	public Location() {
		
	}
	
	public Location(AddressEntity address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		return Objects.equals(address, other.address);
	}

	@Override
	public String toString() {
		return address.toString();
	}

	
	
	
}
