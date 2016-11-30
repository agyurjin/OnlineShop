package edu.onlineshop.driver;

public class RunApplication {

	public static void main(String[] args) {

//		boolean start = true;
//		while(start) {
//			NewThread t1 = new NewThread();
//			t1.run();
//			start = t1.getEnd();
//		}

		while(true) {
			new NewThread().run();
		}
		
		
//		System.out.println("Welcome to Online Shop!\n");
//		System.out.println("Do you have Profile? (yes/no)");
//
//		Session session = new Session();
//		Profile profile = session.start();
//
//		if(profile != null) {
//			if(profile.getRoleID() == 1) {
//				session.startAdminSession(profile);
//			}
//			else if(profile.getRoleID() == 2) {
//				session.startUserSession(profile);
//			}
//			else {
//				System.out.println("Unknown session!");
//			}
//		}
		
	}
}