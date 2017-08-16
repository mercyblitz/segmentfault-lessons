package comet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingDeque;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.comet.CometEvent;
import org.apache.catalina.comet.CometProcessor;

/**
 * Servlet implementation class ChatServlet
 */
@WebServlet("/chat")
public class ChatServlet extends HttpServlet implements CometProcessor {
	private static final long serialVersionUID = 1L;

	protected MessageSender messageSender = null;

	public void init() throws ServletException {
		messageSender = new MessageSender();
		Thread messageSenderThread = new Thread(messageSender,
				"MessageSender[" + getServletContext().getContextPath() + "]");
		messageSenderThread.setDaemon(true);
		messageSenderThread.start();
	}

	public void destroy() {
		messageSender.stop();
		messageSender = null;
	}

	/**
	 * Process the given Comet event.
	 *
	 * @param event
	 *            The Comet event that will be processed
	 * @throws IOException
	 * @throws ServletException
	 */
	public void event(CometEvent event) throws IOException, ServletException {
		HttpServletRequest request = event.getHttpServletRequest();
		HttpServletResponse response = event.getHttpServletResponse();
		response.setCharacterEncoding("UTF-8");
		String sessionId = request.getSession(true).getId();
		if (event.getEventType() == CometEvent.EventType.BEGIN) {
			log("Begin for session: " + sessionId);
			messageSender.openSession(request, response);
			messageSender.send(sessionId, "<!DOCTYPE html>");
			messageSender.send(sessionId, "<head><title>JSP Chat</title></head><body>");
		} else if (event.getEventType() == CometEvent.EventType.ERROR) {
			log("Error for session: " + request.getSession(true).getId() + " , sub type :" + event.getEventSubType());
			messageSender.removeSession(request);
			event.close();
		} else if (event.getEventType() == CometEvent.EventType.END) {
			log("End for session: " + request.getSession(true).getId());
			messageSender.removeSession(request);
			PrintWriter writer = response.getWriter();
			writer.println("</body></html>");
			event.close();
		} else if (event.getEventType() == CometEvent.EventType.READ) {
			InputStream is = request.getInputStream();
			byte[] buf = new byte[512];
			do {
				int n = is.read(buf); // can throw an IOException
				if (n > 0) {
					String content = new String(buf, 0, n);
					log("Read " + n + " bytes: " + content + " for session: " + sessionId);
					messageSender.send(sessionId, content);
				} else if (n < 0) {
					error(event, request, response);
					return;
				}
			} while (is.available() > 0);
		}
	}

	private void error(CometEvent event, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	public static class Message {

		private final String sessionId;

		private final String value;

		public Message(String sessionId, String value) {
			this.sessionId = sessionId;
			this.value = value;
		}

		public String getSessionId() {
			return sessionId;
		}

		public String getValue() {
			return value;
		}

		public String toString() {
			return "Session [id :" + sessionId + "] : " + value;
		}

	}

	public class MessageSender implements Runnable {

		protected volatile boolean running = true;

		private ConcurrentMap<String, HttpServletResponse> responsesMap = new ConcurrentHashMap<String, HttpServletResponse>();

		public void removeSession(HttpServletRequest request) {
			String sessionId = getSessionId(request);
			if (sessionId != null) {
				responsesMap.remove(sessionId);
			}
		}

		public void openSession(HttpServletRequest request, HttpServletResponse response) {
			String sessionId = getSessionId(request);
			if (sessionId != null) {
				responsesMap.putIfAbsent(sessionId, response);
			}
		}

		private String getSessionId(HttpServletRequest request) {
			String sessionId = null;

			HttpSession session = request.getSession(false);

			if (session != null) {
				sessionId = session.getId();
			}

			return sessionId;
		}

		private final BlockingDeque<Message> messagesQueue = new LinkedBlockingDeque<Message>();

		public MessageSender() {
		}

		public void stop() {
			running = false;
		}

		/**
		 * Add message for sending.
		 */
		public void send(String sessionId, String data) {
			Message message = new Message(sessionId, data);
			messagesQueue.add(message);
		}

		public void run() {
			while (running) {
				try {
					Message message = messagesQueue.take();
					String sessionId = message.getSessionId();
					HttpServletResponse response = responsesMap.get(sessionId);
					PrintWriter writer = response.getWriter();
					System.out.println(
							"Thread " + Thread.currentThread().getName() + " , message : " + message.toString());
					writer.println(message.toString());
					writer.flush();
				} catch (Exception e) {
					log("IOExeption sending message", e);
				}
			}
		}
	}

}
