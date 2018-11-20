package com.microsoft.css.dev.cases.eventhub;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.microsoft.azure.eventhubs.ConnectionStringBuilder;
import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.eventhubs.EventHubException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.Instant;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;


public class EventHubTesting {

	public static void main(String[] args) throws EventHubException, ExecutionException, InterruptedException, IOException {

		final Gson gson = new Gson();
		
		final ConnectionStringBuilder connStr = new ConnectionStringBuilder()
		        .setNamespaceName("Your Event Hubs namespace name")
		        .setEventHubName("Your event hub")
		        .setSasKeyName("Your policy name")
		        .setSasKey("Your primary SAS key");
		
		
		
		
		String payload = "Message " + Integer.toString(i);
		byte[] payloadBytes = gson.toJson(payload).getBytes(Charset.defaultCharset());
		EventData sendEvent = EventData.create(payloadBytes);

		final EventHubClient ehClient = EventHubClient.createSync(connStr.toString(), executorService);
		ehClient.sendSync(sendEvent);

		// close the client at the end of your program
		ehClient.closeSync();
		
		
		
	}
	

}
