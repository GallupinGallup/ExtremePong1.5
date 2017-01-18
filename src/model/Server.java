package model;

import java.io.IOException;
import java.net.ServerSocket;

public class Server extends Thread {
	
	private ServerSocket serverSocket;
	
	void ServerSocket(int port) {
		try {
			serverSocket = new ServerSocket(6666);
		} catch (IOException e) {
			e.printStackTrace();
		}
		accept();
	}

	private void accept() {
		
		
	}
}
