package states;

public class BookingStateNew extends BookingState {

	protected BookingStateNew() {
		super();
		state = StateNames.NEW;
	}
	
	@Override
	public BookingState getNextState() {
		return new BookingStatePayment();
	}

}
