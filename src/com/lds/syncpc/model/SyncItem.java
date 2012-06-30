package com.lds.syncpc.model;

import java.io.File;

public class SyncItem implements Syncables {
	
	protected long id;
	protected String path;
	protected File file;
	
	public SyncItem() {
		
	}
	
	/* GET & GET */

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	

}
