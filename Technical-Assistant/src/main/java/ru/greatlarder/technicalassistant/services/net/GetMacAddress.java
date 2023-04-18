package ru.greatlarder.technicalassistant.services.net;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

public class GetMacAddress {

	public static String getMacAddrHost(String host) throws IOException, InterruptedException {
		boolean ok = ping3(host);
		if (ok) {
			InetAddress address = InetAddress.getByName(host);
			String ip = address.getHostAddress();
			return runProgramWithCatchingOutput("arp -a " + ip);
		}
		return null;
	}
	
	
	public static boolean ping3(String host) throws IOException, InterruptedException {
		boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");
		
		ProcessBuilder processBuilder = new ProcessBuilder("ping", isWindows ? "-n" : "-c", "1", host);
		Process proc = processBuilder.start();
		
		int returnVal = proc.waitFor();
		return returnVal == 0;
	}
	
	public static String runProgramWithCatchingOutput(String param) throws IOException {
		Process p = Runtime.getRuntime().exec(param);
		BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		while ((line = input.readLine()) != null) {
			if (!line.trim().equals("")) {
				line = line.substring(1);
				String mac = extractMacAddr(line);
				if (!mac.isEmpty()) {
					return mac;
				}
			}
			
		}
		return null;
	}
	
	public static String extractMacAddr(String str) {
		String arr[] = str.split("   ");
		for (String string : arr) {
			if (string.trim().length() == 17) {
				return string.trim().toUpperCase();
			}
		}
		return "";
	}
	public static String getDataByMac(String macAddress) {
		String baseURL = "http://api.macvendors.com/";
		try {
			StringBuilder result = new StringBuilder();
			URL url = new URL(baseURL + macAddress);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			rd.close();
			return result.toString();
		} catch (FileNotFoundException e) {
			return "N/A";
		} catch (IOException e) {
			return null;
		}
	}
}
