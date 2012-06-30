package com.lds.syncpc.provider.m3u;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.lds.syncpc.model.MusicItem;

public class M3uParser {
	private static Logger log = Logger.getLogger(M3uParser.class);
	private static final String COMMENT_FLAG = "#"; 
	
	public static List<MusicItem> parserM3u(File m3uFile) throws IOException {
		List<MusicItem> list = new ArrayList<MusicItem>();
		
		List<String> lines = FileUtils.readLines(m3uFile);
		for (String line : lines) {
			if (line.startsWith(COMMENT_FLAG)) {
				continue;
			}
			File file = new File(line);
			if (file.exists()) {
				MusicItem item = new MusicItem();
				item.setFile(file);
				item.setPath(line); // orig path
				list.add(item);
			} else {
				log.error("file which in palylist is not exists: " + line);
			}
		}
		return list;
	}
	

}
