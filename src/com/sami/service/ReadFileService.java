package com.sami.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.sami.model.TransactionModel;

public class ReadFileService {
	
	
	public Map<String, Long> readFileAndProcessTime(String filePath) throws IOException, ParseException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(
	             new FileInputStream(filePath), "UTF-8"));
		
		    String line = null;
		    HashMap<String, Long> timeTaken = new HashMap<String, Long>();
		    
		    List<TransactionModel> modelList = new ArrayList<TransactionModel>();

		    while ((line = br.readLine()) != null) {
		      String[] values = line.split(",");
		      TransactionModel model = new TransactionModel();
		      model.setTransactionId(values[0]);
		      model.setDateAndTime(parseAndConvertDate(values));
		      model.setStatus(values[3]);
		      if(!modelList.contains(model)) {
		    	  
		    	  modelList.add(model);
		      }
		      else {
		    	  long differenceInDuration =TimeUnit.MILLISECONDS.toMinutes
		    			  ( model.getDateAndTime().getTime() - modelList.get(modelList.indexOf(model)).getDateAndTime().getTime());
		    	  
		    	  timeTaken.put(model.getTransactionId(), differenceInDuration);
		    	  
		    	  
		    	  
		      }
		      
		     
		    }
		    if(modelList.size()!=timeTaken.size()) {
		    	
		    	for(TransactionModel s : modelList) {
		    		if(!timeTaken.containsKey(s.getTransactionId())) {
		    				timeTaken.put(s.getTransactionId(), Long.valueOf(0));
		    		}
		    		
		    	}
		    	
		    }
		    br.close();
		    return timeTaken;
		  }
	
	
	public Date parseAndConvertDate(String[] values) throws ParseException {
		
		SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd,hh:mm a");
	      StringBuilder builder = new StringBuilder();
	      String[] dateParser = values[1].split("–");
	     String parsedDate= builder.append(dateParser[0].trim()).append("-").append(dateParser[1].trim()).append("-").append(dateParser[2].trim()).toString();
	      
	      Date date = parseFormat.parse(parsedDate+","+values[2]);
	      
	      return date;
		
		
		
		
	}
}

