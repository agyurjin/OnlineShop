package edu.onlineshop.session;

import edu.onlineshop.dal.registration.Profile;

public interface SessionManager {
	public void startUserSession(Profile profile);
	public void startAdminSession(Profile profile);
	public Profile start();
}
