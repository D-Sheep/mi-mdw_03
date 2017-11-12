

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sessions.Session;
import sessions.SessionFactory;
import sessions.SessionStorage;

/**
 * Servlet implementation class TravelAgencyServlet
 */
public class TravelAgencyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static SessionStorage sessionStorage;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TravelAgencyServlet() {
        super();
        sessionStorage = new SessionStorage();
    }
    
    protected Session getSessionFromCookies(Cookie[] cookies) {
		Session result = null;
    	if (cookies != null) {
			for (Cookie c : cookies) {
				System.out.println(c.getName() + " = " + c.getValue());
				if (c.getName().contentEquals("sessID")) {
					Long sessionId = Long.parseLong(c.getValue());
					System.out.println("Looking for session with ID " + sessionId);
					result = sessionStorage.findSession(sessionId);
					if (result == null) {
						System.out.println("Session not found!");
					} else {
						System.out.println("Session found");
					}
					break;
				}
			}
		}
    	return result;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Session session = getSessionFromCookies(request.getCookies());
		PrintWriter payload = response.getWriter();
		payload.append("[GET] ");
		if (session == null) {
			System.out.println("Session is null");
			session = SessionFactory.createSession(sessionStorage);
			if (sessionStorage.add(session)) {
				payload.append("Created new session with id " + session.getId());
			}
			response.setHeader("Set-Cookie", "sessID=" + Long.toString(session.getId()));
		} else {
			payload.append("Using previously stored session");
		}
		payload.println();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Session session = getSessionFromCookies(request.getCookies());
		PrintWriter payload = response.getWriter();
		payload.append("[POST] ");
		if (session == null) {
			session = SessionFactory.createSession(sessionStorage);
			sessionStorage.add(session);
			payload.append("Created new session with ID: " + session.getId() + ", ");
			response.setHeader("Set-Cookie", "sessID=" + session.getId());
		} else {
			session.nextState();
		}
		payload.append("Session state is currently: " + session.getState());
		payload.println();
	}

}
