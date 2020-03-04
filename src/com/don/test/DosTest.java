package com.don.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DosTest {

	@Test
	public void test() throws Exception {
		List<String> command = new ArrayList<String>();
		command.add("bin/ffmpeg/bin/ffmpeg.exe");
		// command.add("-h");
		command.add("-headers");
		command.add("Referer: http://tts.tmooc.cn/video/showVideo?menuId=658388&version=BIGTN201901");
		command.add("-i");
		command.add("http://c.it211.com.cn/big19070731pm/big19070731pm.m3u8");
		command.add("H:/TeduBigData/2.mp4");
		ProcessBuilder builder = new ProcessBuilder(command);
		builder.redirectErrorStream(true);
		Process process = builder.start();
		InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
		BufferedReader br = new BufferedReader(inputStreamReader);
		String line = "";
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		process.waitFor();
		if (br != null) {
			br.close();
		}
	}
}
