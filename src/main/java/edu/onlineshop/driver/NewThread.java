package edu.onlineshop.driver;

import edu.onlineshop.dal.registration.Profile;
import edu.onlineshop.session.Session;

public class NewThread extends Thread {

	private boolean endThread;
	
	public void run(){
		System.out.println("Welcome to Online Shop!\n");
		System.out.println("Do you have Profile? (yes/no)");

		Session session = new Session();
		Profile profile = session.start();

		if(profile != null) {
			if(profile.getRoleID() == 1) {
				session.startAdminSession(profile);
			}
			else if(profile.getRoleID() == 2) {
				session.startUserSession(profile);
			}
			else {
				System.out.println("Unknown session!");
			}
		}
		this.endThread = session.exit();
	}

	public boolean getEnd() {
		return endThread;
	}
}
