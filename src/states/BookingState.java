package states;

public abstract class BookingState {
	protected enum StateNames {
		NEW ("New"),
		PAYMENT ("Payment"),
		COMPLETED ("Completed");
		
		private final String name;
		
		private StateNames(String s) {
			this.name = s;
		}
		
		@Override
		public String toString() {
			return this.name;
		}
	}
	protected StateNames state;
	
	public static BookingState createNewState() {
		return new BookingStateNew();
	}
	public abstract BookingState getNextState();
	public boolean isFinal() {
		return this.getNextState() == null;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) { return false; }
		if (obj == this) { return true; }
		if (!(obj instanceof BookingState)) { return false; }
		return state == ((BookingState)obj).state;
	}
	
	@Override
	public String toString() {
		return state.toString();
	}
}
