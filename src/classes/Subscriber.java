package classes;

public interface Subscriber {
	void receiveMessage(String message);

	Subscriber findById(int id);

	int calcularStatus(int i, int j);

	void makeComplicatedCalculations(int result);
	
	Integer getId();
}
