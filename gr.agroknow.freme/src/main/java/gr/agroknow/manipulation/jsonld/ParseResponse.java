package gr.agroknow.manipulation.jsonld;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//import org.apache.log4j.BasicConfigurator;

//import com.fasterxml.jackson.core.JsonGenerationException;
//import com.github.jsonldjava.core.JsonLdError;
//import com.github.jsonldjava.core.JsonLdOptions;
//import com.github.jsonldjava.core.JsonLdProcessor;
//import com.github.jsonldjava.utils.JsonUtils;






//import org.codehaus.jackson.JsonGenerationException;
//import org.codehaus.jackson.JsonParseException;
//import org.codehaus.jackson.JsonProcessingException;
//import org.codehaus.jackson.map.JsonMappingException;
//import org.codehaus.jackson.map.ObjectMapper;












import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.client.ClientResponse;
import org.json.simple.JSONArray;
//object
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.JsonObject;

//import com.google.gson.Gson;





public class ParseResponse {
	//best to return agrovoc object
	/*ClientResponse responseObj JSONObject responseObj*/
    public static ArrayList<String>  getAgrovoc(String responseObj) throws JsonParseException, JsonMappingException, IOException, ParseException{
	//public static void main(String[] args)  {	
	
    	JSONParser parser = new JSONParser();	 	
    	List<String> agrovocUriList = new ArrayList<String>();
    	//agrovocUriList = null;
		try {
    	 System.out.print("*********getAgrovoc()**************** ");
		//FILE Object obj = parser.parse(new FileReader("C:\\Users\\papou_000\\Desktop\\agroknow\\rest\\responseOK.json"));
    	//FILE JSONObject jsonObject = (JSONObject) obj;
    	 Object obj =  parser.parse(responseObj) ;
         JSONObject jsonObject = (JSONObject) obj;				
		// loop array
				JSONArray graph = (JSONArray) jsonObject.get("@graph");//jsonObject
				System.out.print("@@@@graph ");
				Iterator<String> iterator = graph.iterator();
				String valuelabel = null ;
				String termUri =null;
				while (iterator.hasNext()) {			
					 Object context = iterator.next();					 
					 JSONObject	 cntx = (JSONObject) context;   
			         termUri = (String) cntx.get("termInfoRef");
			         agrovocUriList.add(termUri);
			        // valuelabel = (String) termUri.get("anchorOf");
			          System.out.println("termURI "+ termUri + "------- get now anchorOf :::::::::::");			    			         
			         /* JSONArray anchorOf  =(JSONArray) cntx.get("anchorOf");
			           
			      ///    System.out.print("++++++++++anchorOf "+ anchorOf.toJSONString() + "-------  ");	
				          //Iterator<String> iteratorAnchor = anchorOf.iterator();
				          int y =0 ;
				          for(int j = 1; j < anchorOf.size(); j++) {
				        	     valuelabel = (String) anchorOf.get(0);
				        	    System.out.println("labelllllllllllllllll" + valuelabel);
				        	}
						   while (iteratorAnchor.hasNext()) {
							   if(y !=0){
							 Object value = iteratorAnchor.next();						 
							 JSONObject	 label = (JSONObject) value;
							 valuelabel = (String) label.get("@value");
							// agrovocUriList.add(valuelabel);
							  System.out.print("valuelabel "+ valuelabel + "-------888888888 ");
							   }
							   y++;
							   System.out.print("yyyyy "+ y + "2222222 ");
						   }
				         System.out.print(termUri + " " + valuelabel + "************************* " );
			        	*/
			           
			           
			           
			         /* last modification
			          *  posible solution to copy the cntx to other object in order to cast
			          *  
			          *  */   
				//}//if first  
					
					
//					 if(termUri != null){
//					 System.out.print(termUri);
//					 }
					 
				}//end of while
				//get label
				for(int i = 1; i < graph.size(); i++) {
					///Object o =new Object();
					////////o = parser.parse(graph.get(i).toString());
					JSONObject p = (JSONObject) graph.get(i);
					 System.out.println("-----//////----" );
					//// JSONArray labels = (JSONArray) p.get("anchorOf");
					// loop array
					 String arrayLabel = p.get("anchorOf").toString();
					 System.out.println("labelllllllllllllllll" + p.get("anchorOf").toString());
					// JSONArray l = (JSONArray) new  JSONValue().parse(arrayLabel);
					 
					 String[] toLabel1 = arrayLabel.split("\\:");
					 valuelabel = toLabel1[1];
					 String[] toLabel2 = valuelabel.split("\\,");
					 valuelabel = toLabel2[0];
					 valuelabel = valuelabel.replaceAll("^\"|\"$", "");//remove double quotes
					 System.out.println("valuelabel 0000000 " + valuelabel);
	        	}
				
				
				
		

		
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		agrovocUriList.removeAll(Collections.singleton(null));
		return (ArrayList<String>) agrovocUriList;
	 
		
		
	}
    	
    	
	

}
