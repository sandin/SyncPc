package com.lds.syncpc.provider.m3u.test;

import java.io.File;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lds.syncpc.model.MusicItem;
import com.lds.syncpc.provider.m3u.M3uParser;

public class M3uParserTest {
	
	@Before
	public void setUp() throws Exception {
		
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testParser() throws Exception {
		File m3uFile = new File("test.m3u");
		Assert.assertTrue(m3uFile.exists());
		
		List<MusicItem> list = M3uParser.parserM3u(m3uFile);
		Assert.assertNotNull(list);
		
		for (MusicItem item: list) {
			System.out.println(item);
		}
	}

}
