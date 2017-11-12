package sessions;

import java.util.HashMap;

public class SessionStorage {
	private HashMap<Long, Session> storage = new HashMap<>();
	
	public boolean add(Session s) {
		long sessionId = s.getId();
		if (!storage.containsKey(sessionId)) {
			storage.put(sessionId, s);
			return true;
		}
		return false;
	}
	
	public Session findSession(long id) {
		return storage.get(id);
	}
	
	public void expireSession(long id) {
		storage.remove(id);
	}
}
