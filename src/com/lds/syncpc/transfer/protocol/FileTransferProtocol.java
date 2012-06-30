package com.lds.syncpc.transfer.protocol;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class FileTransferProtocol {
	private static Logger log = Logger.getLogger(FileTransferProtocol.class);
	
	public static final String END_FLAG = "EOF";
	public static final String DS = System.getProperty("file.separator");

	public static DataOutputStream writeFileToOutputStream(File file,
			OutputStream os) throws IOException {
		byte[] mybytearray = FileUtils.readFileToByteArray(file);

		DataOutputStream dos = new DataOutputStream(os);
		dos.writeUTF(file.getName()); // 文件名
		dos.writeLong(mybytearray.length); // 文件长度
		dos.write(mybytearray, 0, mybytearray.length); // 文件内容
		dos.writeUTF(END_FLAG); // 结束标识符
		return dos;
	}

	public static String readDataIntputStreamToFile(DataInputStream din, String rootDir)
			throws IOException {
		String fileName = din.readUTF(); // 文件名
		long size = din.readLong(); // 文件长度

		FileUtils.forceMkdir(new File(rootDir));
		OutputStream output = new FileOutputStream(rootDir + DS + fileName);
		byte[] buffer = new byte[1024];
		int bytesRead;
		while (size > 0
				&& (bytesRead = din.read(buffer, 0,
						(int) Math.min(buffer.length, size))) != -1) {
			output.write(buffer, 0, bytesRead);
			size -= bytesRead;
		}
		output.close();
		log.info("write inputstream to file -> " + fileName);
		return din.readUTF();
	}

	public static void readInputStreamToFiles(InputStream in, String rootDir)
			throws IOException {
		DataInputStream dis = new DataInputStream(in);
		try {
			while (true) {
				readDataIntputStreamToFile(dis, rootDir);
			}
		} catch (EOFException e) {
			// do nothing
			log.info("EOF");
		} finally {
			log.info("loop end");
			dis.close();
		}
	}

}
