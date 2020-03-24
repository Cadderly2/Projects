package com.techelevator.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class AuditWriter {

	private String fileName = "";
	private File auditFile;
	private PrintWriter myPrintWriter;

	// Set up the PrintWriter

	public AuditWriter(String fileName) {
		super();
		this.fileName = fileName;
		auditFile = new File(fileName);

		try {
			myPrintWriter = new PrintWriter(new FileOutputStream(auditFile, true));
			{
				printLine("Audit File Started");
			}
		} catch (FileNotFoundException e) {
			System.out.println("The file writer has encountered an error: could not create " + fileName);
			e.printStackTrace();
		}
	}

	// Get current time and date and format it
	public String getNow() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss: a");														
		LocalDateTime now = LocalDateTime.now();
		return (dtf.format(now));
	}

	// Getters and Setters

	public String getFileName() {
		return fileName;
	}

	// Print what's passed in

	public void printLine(String toPrint) {
		myPrintWriter.println(getNow() + " " + toPrint);
		myPrintWriter.flush();
	}

	// Call this when the program is over

	public void closePrinter() {
		printLine("Audit File Stopped");
		myPrintWriter.flush();
		myPrintWriter.close();
		
	}
}
