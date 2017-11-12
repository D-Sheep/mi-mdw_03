package sessions;

import java.util.Random;

public class SessionFactory {
	private static Random prng = new Random();
	
	public static Session createSession(SessionStorage storage) {
		return new Session(prng.nextLong(), storage);
	}
}
