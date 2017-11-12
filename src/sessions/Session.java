package sessions;
import states.BookingState;

public class Session {
	private final long id;
	private BookingState state;
	private SessionStorage storage;
	
	public Session(long id, SessionStorage storage) {
		this.id = id;
		this.storage = storage;
		this.state = BookingState.createNewState();
	}
	
	public final long getId() {
		return id;
	}
	
	public BookingState getState() {
		return state;
	}
	
	public void nextState() {
		this.state = this.state.getNextState();
		if (this.state.isFinal()) {
			this.storage.expireSession(id);
		}
	}
}
