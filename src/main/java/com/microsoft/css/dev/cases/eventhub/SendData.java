package com.microsoft.css.dev.cases.eventhub;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.Gson;
import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.eventhubs.EventHubException;

public class SendData {
	
	
	public SendData() throws EventHubException, IOException {
		ehClient = EventHubClient.createSync(connectString, executorService);
		
		bigData = Files.lines(Paths.get("C:\\testdata.txt"), StandardCharsets.UTF_8).toString();
		
	}
	
	public void BeginSendData() throws EventHubException {
		
		for(int i=0; i< 1000; i++ ) {
			
			System.out.println("Message Id:" + i);
			String payload = "Message " + Integer.toString(i);
			StringBuilder builder = new StringBuilder();
			builder.append(payload);
			builder.append(bigData);
			
					
			byte[] payloadBytes = gson.toJson(builder.toString()).getBytes(Charset.defaultCharset());
			EventData sendEvent = EventData.create(payloadBytes);
			
			System.out.println("Prepare send out the Message......");
			ehClient.sendSync(sendEvent);
			
			System.out.println("End of Message sending......");
			
		}

		ehClient.closeSync();
		
	}
	
	
	private final Gson gson = new Gson();
	private final String connectString = "Endpoint=sb://testcaseforeventhub.servicebus.chinacloudapi.cn/;SharedAccessKeyName=testing;SharedAccessKey=IjTD2UipdcJHuRsgLW0atCN0dADMOaQomRr441Z9/f8=;EntityPath=testeventhub";

	private final EventHubClient ehClient;
	private final ExecutorService executorService = Executors.newFixedThreadPool(50);
	private final String bigData;
	
}
