package com.lds.syncpc.transfer;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;
import org.apache.log4j.lf5.util.StreamUtils;

import com.lds.syncpc.transfer.protocol.FileTransferProtocol;

public class TransferController {
	private Logger log = Logger.getLogger(TransferController.class);
	
	private boolean stop = false;
	private int port = 2012;

	public TransferController() {

	}

	public TransferController(int port) {
		this.port = port;
	}

	public void start() throws IOException {
		log.info("server socket start");
		// create socket
		ServerSocket servsock = new ServerSocket(port);
		while (! stop) {
			log.info("ServerSocket Waiting...");

			Socket sock = servsock.accept();
			log.info("Accepted connection : " + sock);

			//Send file
			OutputStream os = sock.getOutputStream();
			
			//Sending file name and file size to the server
			log.info("Sending files...");
			File myFile = new File("Kelly Clarkson-Mr. Know It All.mp3");
			DataOutputStream dos = FileTransferProtocol.writeFileToOutputStream(myFile, os);
			FileTransferProtocol.writeFileToOutputStream(new File("01 Kicking My Heels.m4a"), os);
	        dos.flush();
			
			//Closing socket
			sock.close();
			log.info("file transfer success!");
		}
		log.info("server sockert close");
		servsock.close();
	}
	
	public void stop() {
		log.info("stop");
		stop = true;
	}

}
