package br.gov.go.sefaz.hrs.web.config.init;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class AppHttpSessionListener implements HttpSessionListener {

	private static final int SESSION_MAX_INACTIVE_INTERVAL = 10; // Minutes
	
	@Override
	public void sessionCreated(HttpSessionEvent sessionEvent) {
		sessionEvent.getSession().setMaxInactiveInterval(SESSION_MAX_INACTIVE_INTERVAL * 60);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		// Do nothing
	}
}
