package ru.greatlarder.technicalassistant.services.net;

import ru.greatlarder.technicalassistant.domain.ScanObj;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class ScanNet {
	
	public List<ScanObj> start(String ipA){
		List<ScanObj> list = new ArrayList<>();
		if(ipA != null) {
			ConcurrentSkipListSet networkIps = scan(ipA, 254);
			networkIps.forEach(ip -> {
				try {
					ScanObj scanObj = new ScanObj();
					scanObj.setIp((String) ip);
					scanObj.setMac(GetMacAddress.getMacAddrHost((String) ip));
					//scanObj.setManufacturer(GetMacAddress.getDataByMac(GetMacAddress.getMacAddrHost((String) ip)));
					list.add(scanObj);
				} catch (IOException |InterruptedException e) {
					e.printStackTrace();
					Thread.currentThread().interrupt();
				}
			});
		}
		return list;
	}
	
	private ConcurrentSkipListSet scan(String firstIpInTheNetwork, int numOfIps) {
		
		ExecutorService executorService = Executors.newFixedThreadPool(20);
		final String networkId = firstIpInTheNetwork.substring(0, firstIpInTheNetwork.length() - 1);
		ConcurrentSkipListSet ipsSet = new ConcurrentSkipListSet<>();
		
		AtomicInteger ips = new AtomicInteger(0);
		while (ips.get() <= numOfIps) {
			String ip = networkId + ips.getAndIncrement();
			executorService.submit(() -> {
				try {
					InetAddress inAddress = InetAddress.getByName(ip);
					if (inAddress.isReachable(500)) {
						ipsSet.add(ip);
					}
				} catch (IOException e) {
				e.printStackTrace();
				}
			});
		}
		executorService.shutdown();
		try {
			executorService.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
		return ipsSet;
	}
}
