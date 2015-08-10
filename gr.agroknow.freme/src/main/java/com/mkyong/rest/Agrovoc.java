package com.mkyong.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





public class Agrovoc {
	
	 String uri;
	 String label;
	 static Collection<Agrovoc> agrovocs = new ArrayList<Agrovoc>();
	 
	 public String getAgrovocUri() { return uri; }
	 public void setAgrovocUri(String uri) {
			this.uri = uri;
	 }
	 
	 
     public String getAgrovocUriLabel() { return label; }     
     public void setAgrovocLabel(String label) {
			this.label = label;
	 }
     
     
     public Collection<Agrovoc> getAgrovocList() { return agrovocs; }     
     
     public void addToAgrovocList(Agrovoc agrovoc) {
			
    	 agrovocs.add(agrovoc);
    	 
    	 
	 }
     
     
     
   /*  
     static Map<String, Agrovoc> agrovocs = new HashMap<String, Agrovoc>();
     static Map<Long, Agrovoc> agrovocsLabels = new HashMap<Long, Agrovoc>();

   		public void addToURIAgrovocList(String id,Agrovoc agrovoc){
   			if(!agrovocs.containsKey(id));
   			agrovocs.put(id,agrovoc);
   			
   		}
   		
   		public static List<Agrovoc> getListAgrovocs(){
   			
   			return (List<Agrovoc>) agrovocs;
   			
   		} 
   		
   		public Agrovoc getAgrovoc(long id){
   			
   			Agrovoc h = agrovocs.get(id);
   			
   			return h;
   		} 
   		
     */
     
}
