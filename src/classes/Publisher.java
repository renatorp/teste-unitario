package classes;

public class Publisher {
	Subscriber subscriber;

	public Subscriber getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}

	public void sendMessage(String message) {
		subscriber.receiveMessage(message);
	}
	
	public String getStatusSubcriber(int id) {
		Subscriber s = subscriber.findById(id);
		int result = subscriber.calcularStatus(id+2, id+4);
		result *= 2;
		
		String af = "Viva";
		
		try {
			subscriber.makeComplicatedCalculations(result);
		} catch (Exception e) {
			af = "Ops";
		}
		
		return af + " " + result + " " + s.getId();
	}
}
