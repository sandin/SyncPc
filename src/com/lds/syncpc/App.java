package com.lds.syncpc;

import java.io.IOException;

import com.lds.syncpc.transfer.TransferController;

public class App {
	private TransferController transferController; 
	
	public App() {
		transferController = new TransferController();
	}
	
	public void start() {
		try {
			transferController.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) throws IOException {
		App app = new App();
		app.start();
	}

}
