package com.mpojeda84.mapr.connectedcarmaprtofirebase;

import com.mpojeda84.mapr.connectedcarmaprtofirebase.config.Configuration;
import com.mpojeda84.mapr.connectedcarmaprtofirebase.service.CarDataService;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;


public class Application {

	public static String table;
	public static String messagesTable;
	public static String firebase;
	public static int delay = 200;


	public static void main(String[] args) throws ParseException, InterruptedException {

		// reads arguments from terminal
		CommandLine commandLine = new DefaultParser().parse(Configuration.generateOptions(), args);

		table = commandLine.getOptionValue("t");
		firebase = commandLine.getOptionValue("f");
		delay = Integer.parseInt(commandLine.getOptionValue("d"));
		messagesTable = commandLine.getOptionValue("m");

		System.out.println("table: " + table);
		System.out.println("firebase: " + firebase);
		System.out.println("delay: " + delay);

		CarDataService service = new CarDataService();


		while(true) {
            service.sendAllToFirebase(); // sends current contents from DB to Firebase
            Thread.sleep(delay); // delays the execution <delay> milliseconds
        }

	}



}

