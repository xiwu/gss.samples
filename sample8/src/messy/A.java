package messy;

public class A {
	OpsParkingSlotRepository opsParkingSlotRepository = new OpsParkingSlotRepository();
	NosCountryStation nosCountryStation = null;
	public static void main(String args[]) { 
		A a = new A();
		OpsParkingSlot opsParkingSlot = a.opsParkingSlotRepository.getParkingPosition(a.nosCountryStation.getId());
	    
	}
}
