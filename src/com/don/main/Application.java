package com.don.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.don.response.GetVideoInFo;

public class Application {
	public static void main(String[] args) throws IOException, InterruptedException {
		GetVideoInFo info = new GetVideoInFo();
		List<String> urlList = info.URLHandler();

		for (String url : urlList) {
			List<String> command = new ArrayList<String>();
			command.add("bin/ffmpeg/bin/ffmpeg.exe");
			command.add("-headers");
			command.add("Referer: http://tts.tmooc.cn/video/showVideo?menuId=658388&version=BIGTN201901");
			command.add("-i");
			command.add(url);
			command.add("H:/TeduBigData/"+info.getName(url)+".mp4");
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
			if(process!=null) {
				process.destroy();
			}
		}
	}
}
