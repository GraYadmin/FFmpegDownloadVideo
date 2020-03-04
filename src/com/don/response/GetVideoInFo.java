package com.don.response;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetVideoInFo {
	
	private Map<String, String> map = new HashMap<>();

	public List<String> URLHandler() {
		String urlstr = "http://tts.tmooc.cn/video/showVideo?menuId=692854&version=BIGTN201907";
		
		
		String cookie = "eBoxOpenBIGTN201907=true; " + "isCenterCookie=no; " + "tedu.local.language=zh-CN; "
				+ "__root_domain_v=.tmooc.cn; " + "_qddaz=QD.2h642r.n8nne8.k2ua5f2k; "
				+ "Hm_lvt_51179c297feac072ee8d3f66a55aa1bd=1572312223,1573467793,1573785287,1573964580; TMOOC-SESSION=64605a7f8787463fa30643073d70e81c; "
				+ "sessionid=64605a7f8787463fa30643073d70e81c|E_bfup8d3; " + "cloudAuthorityCookie=3; "
				+ "versionListCookie=BIGTN201907; " + "defaultVersionCookie=BIGTN201907; "
				+ "versionAndNamesListCookie=BIGTN201907N22N%25E5%25A4%25A7%25E6%2595%25B0%25E6%258D%25AE%25E5%2585%25A8%25E6%2597%25A5%25E5%2588%25B6%25E8%25AF%25BE%25E7%25A8%258BV06N22N731366;"
				+ " courseCookie=BIG; " + "stuClaIdCookie=731366; "
				+ "Hm_lvt_e997f0189b675e95bb22e0f8e2b5fa74=1573802250,1573802264,1573824587,1573964593; "
				+ "_qdda=3-1.1us199; " + "_qddab=3-m400cq.k32hvxsv; " + "_qddamta_2852189568=3-0; "
				+ "Hm_lpvt_51179c297feac072ee8d3f66a55aa1bd=1573965220; "
				+ "JSESSIONID=D9F2464669E092DD1ABCEE5D77F231E8; "
				+ "Hm_lpvt_e997f0189b675e95bb22e0f8e2b5fa74=1573965303";
		try {
			URL url = new URL(urlstr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Cookie", cookie);
			conn.connect();
			InputStreamReader in = new InputStreamReader(conn.getInputStream());
			BufferedReader buffer = new BufferedReader(in);
			String line = "";
			List<String> urlList = new ArrayList<>();
			while ((line = buffer.readLine()) != null) {
				if (line.contains("<p id=\"active_big1907")) {
					String m3u8 = strHandler(line,"id").substring(strHandler(line,"id").indexOf("_")+1);
					String videoName = strHandler(line,"title");
					map.put(m3u8,videoName);
					urlList.add("http://c.it211.com.cn/"+m3u8.substring(0, m3u8.indexOf(".m3u8"))+"/"+m3u8);
				}
			}
			return urlList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public String getName(String url) {
		String videoName = url.substring(url.lastIndexOf("/")+1, url.length());
		return map.get(videoName);
	}
	

	private String strHandler(String str, String property) {
		int propertyindex = str.indexOf(property);
		int beginIndex = str.indexOf("\"", propertyindex) + 1;
		int endIndex = str.indexOf("\"", beginIndex);
		return str.substring(beginIndex, endIndex);
	}

}
