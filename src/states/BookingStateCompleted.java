package states;

public class BookingStateCompleted extends BookingState {

	protected BookingStateCompleted() {
		super();
		state = StateNames.COMPLETED;
	}
	
	@Override
	public BookingState getNextState() {
		return null;
	}
}
