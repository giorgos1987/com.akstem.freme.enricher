package com.mkyong.rest;

import java.util.ArrayList;
import java.util.Collection;





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
     
     
}
