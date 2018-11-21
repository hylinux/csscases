package com.microsoft.css.dev.cases.eventhub;



import com.microsoft.azure.eventhubs.EventHubException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;


public class EventHubTesting {

	public static void main(String[] args) throws EventHubException, ExecutionException, InterruptedException, IOException {

		ExecutorService service = Executors.newFixedThreadPool(2000);
		
		for(int i=0; i<=2000; i++ ) {
			Runnable runnable = () -> {
				System.out.println( "Current Thread Id:" + Thread.currentThread().getId());
				try {
					SendData senders = new SendData();
					senders.BeginSendData();
				} catch (EventHubException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} ;
			};
			
			service.execute(runnable);
		}
		
		service.shutdown();
		
	}
	

}
