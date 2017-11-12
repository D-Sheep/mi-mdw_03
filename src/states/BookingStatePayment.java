package states;

public class BookingStatePayment extends BookingState {

	protected BookingStatePayment() {
		super();
		state = StateNames.PAYMENT;
	}
	
	@Override
	public BookingState getNextState() {
		return new BookingStateCompleted();
	}
	
}
