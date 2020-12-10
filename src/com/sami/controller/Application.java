package com.sami.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.OptionalDouble;

import com.sami.service.ReadFileService;

public class Application {

	public static void main(String[] args) throws IOException, ParseException  {
		
		
		ReadFileService service = new ReadFileService();
		
		//Please input the file name here to read
		
		Map<String,Long> finalResult = service.readFileAndProcessTime("D:\\\\FileName.txt");
		
		for(Map.Entry<String, Long> e : finalResult.entrySet()) {
			
			if(e.getValue()!=Long.valueOf(0))
			System.out.println("Time Taken by Transaction "+ e.getKey() + " to complete is "+e.getValue()+" Minutes");
			if(e.getValue()==Long.valueOf(0)) 
			
			{
				
				System.out.println("Transaction with ID "+ e.getKey()+" still in Process ");
			}
			
		}
		OptionalDouble averageTimeTaken =finalResult.values().stream().filter(e->e!=Long.valueOf(0)).mapToDouble(a->a).average();
		System.out.println("Average Time taken by All Transactions which has Completed is "+averageTimeTaken.getAsDouble()+" Minutes");
		
		
		
	}
		

	}


