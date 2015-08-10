package com.mkyong.rest;

import gr.agroknow.manipulation.jsonld.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


//import com.google.gson.Gson;
import com.sun.jersey.api.client.ClientResponse;
public class ReadXMLFile {
	
	static Document doc;
	
	public static Document getDocument() {
		return doc;
	}

	public void setDocument(Document doc) {
		this.doc = doc;
	}
	
	/* function createAgrovoElement 
	 * args filemath: xml file of agrisap
	 * return void
	 * takes as parameter filepath of xml file
	 * read all ags:resource nodes and appends new agrovoc elements
	 * with values based on calling freme service
	 * 
	 * 
	 * 
	 * */
	
	
	public static Boolean createAgrovoElement(String filepath ) {
		
		ClientResponse clResponse;
		ParseResponse res = new ParseResponse();
		Document doc = getDocument();
		
		String abstr;
		String uri="";
		Boolean isAgResourse =false;
		//String label="";
		Agrovoc agrovc = new Agrovoc();
		//agrovc.setAgrovocLabel(label);
		agrovc.setAgrovocUri(uri);
		ArrayList<String> myAgrovoURIlist = new  ArrayList<String>();
	    Map<String, Agrovoc> agrovocsUri = new HashMap<String, Agrovoc>();

	//	ArrayList<String> SubjectsList = new  ArrayList<String>();
		//Collection<Agrovoc> agrovocs = new ArrayList<Agrovoc>();
		//agrovocs.add(agrovc);
		String outputClResp;
			
	    try {
	 /*
		File file = new File(filepath);	 
		DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
	                             .newDocumentBuilder();		
		 doc = dBuilder.parse(file);
		*/	    	
		//optional, but recommended
		//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();
		//ags:resources
		//Element root = doc.getDocumentElement();
		//Node root = doc.getFirstChild();
		
		NodeList listResourses = doc.getElementsByTagName("ags:resource");//doc.getChildNodes();//list resources
		//for each resource
		for (int k = 0; k < listResourses.getLength(); k++) {  
			 Node ags_resource = listResourses.item(k);		
				//System.out.println("node name " + ags_resource.getNodeName() + "k==="+ k);
			// myAgrovoURIlist = null;			
			 NodeList listResourse = ags_resource.getChildNodes();
			 String dcterms_abst ="";
			//for each element
				for (int i = 0; i < listResourse.getLength(); i++) {
		                   Node node = listResourse.item(i);
		                   //System.out.println(node.getNodeName());
				   if ("dc:description".equals(node.getNodeName())) {		
/////////					   System.out.println(node.getNodeName() + " inside if i -dcdescription");
					   //OKdcterms_abst = node.getTextContent();
					   isAgResourse = true;
					   NodeList descriptionList = node.getChildNodes();
					   
					   for (int j = 0; j < descriptionList.getLength(); j++) {
						   Node nodeAbstr = descriptionList.item(j);
///////						   System.out.println(nodeAbstr.getNodeName() + " -dcabstractttt");
						   if ("dcterms:abstract".equals(nodeAbstr.getNodeName())) 
							   dcterms_abst = nodeAbstr.getTextContent();
					   }
					   //call the freme utilities
					  // FREMEClientAgrovoc fremeClient= new FREMEClientAgrovoc();
					   //postText
					  // fremeClient.postText(node.getTextContent(), "en", "en");
				   }//dc:description
		      
				   /* explode to ; 
				    * */
				}//end for each element
				
			abstr =  dcterms_abst;//get abstract			
			////////////////////////////////////////////////////			
			//abstr =   getXMLabstract(ags_resource);//get abstract			
			/*
			 * test out
			 * */
			System.out.println("call freme client");					
			//call the freme utilities
		    FREMEClientAgrovoc fremeClient= new FREMEClientAgrovoc();
			   //postText
		    clResponse = fremeClient.postText(abstr,"en","en");	 //outputClResp = clResponse.getEntity(String.class);	    
		    clResponse.bufferEntity();
		    outputClResp = clResponse.getEntity(String.class);
	////		System.out.println("clientresponse" + outputClResp); //System.out.println("ParseResponse");	    
			myAgrovoURIlist = (ArrayList<String>) res.getAgrovoc(outputClResp);	    
		    
			//check client response
	     	//  if (clResponse!=null){}    
		    //Do some staff here with jsonld
		    //extract agrovocs
		    //parse content of response	    
						    /*
							for (Agrovoc agrvcitem : agrovocs) {
					            // agrovoc elements
					            Element dc_subject = doc.createElement("dc:subject");            
					            Element newAgrovoc = doc.createElement("ags:subjectThesaurus");
					            
					            dc_subject.appendChild(doc.createTextNode(agrvcitem.getAgrovocUri()));
					            newAgrovoc.appendChild(dc_subject);          
					            ags_resource.appendChild(dc_subject);
					        }*/
		
			 // if(myAgrovoURIlist!=null)  {
		    	//Create elements dc:subject with AGROVOC uri
			    for (int i = 0; i < myAgrovoURIlist.size(); i++) {
					//System.out.println("list" + mylist.get(i).toString());
			   // 	System.out.println("\""+ myAgrovoURIlist.get(i).toString()+"\":" + "\"\"");
					System.out.println("\""+ myAgrovoURIlist.get(i).toString()+"\":");
					// agrovoc elements
		            Element dc_subject = doc.createElement("dc:subject");            
		            Element newAgrovoc = doc.createElement("ags:subjectThesaurus");
		            //enrich with agrovoc
		            
		            newAgrovoc.appendChild(doc.createTextNode(myAgrovoURIlist.get(i).toString()));//doc.createTextNode(mylist.get(i).toString())
		            newAgrovoc.setAttribute("scheme","ags:AGROVOC");
		            newAgrovoc.setAttribute("xml:lang","eng");
		            dc_subject.appendChild(newAgrovoc);         
		            ags_resource.appendChild(dc_subject);
				}
			 // }//check if response exists 
								
						/*
						NodeList nList = doc.getElementsByTagName("ags:resource");
						for (int temp = 0; temp < nList.getLength(); temp++) {			 
							Node nNode = nList.item(temp);	 
							System.out.println("\nCurrent Element :" + nNode.getNodeName());	 
							if (nNode.getNodeType() == Node.ELEMENT_NODE) {	 
								Element eElement = (Element) nNode;
					 
							//	System.out.println("Staff id : " + eElement.getAttribute("id"));
							//	System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());			
					 
							}//if
						}//if		
						*/		
						/*if (doc.hasChildNodes()) {	 
							printNote(doc.getChildNodes());	 
						}*/
			  }//listresourses , for each resource
			
	    } catch (Exception e) {
		   System.out.println(e.getMessage());
	    }
	 
	    
	    return isAgResourse; //return if it has description
	    
	    
	    
	  }//end 
	 


	/*A function that grabs the abstract from AgrisAP original file  
	 * in order to post to freme services.
	 * 
	 * Input  Node ags:resource
	 * Output String abastract 
	 * 
	 * 
	 * 
	 */
	private static String getXMLabstract(Node ags_resource){
		
		String dcterms_abstract ="";
		NodeList list = ags_resource.getChildNodes();
		 
		for (int i = 0; i < list.getLength(); i++) {
                   Node node = list.item(i);
 
		   // get the salary element, and update the value
		   if ("dcterms:abstract".equals(node.getNodeName())) {		
			   dcterms_abstract = node.getTextContent();
			   //call the freme utilities
			  // FREMEClientAgrovoc fremeClient= new FREMEClientAgrovoc();
			   //postText
			  // fremeClient.postText(node.getTextContent(), "en", "en");		   
			   /*
			    * test
			    * 
			    * */
			   System.out.println(dcterms_abstract);
			   
		   }
      
		}
		
		return dcterms_abstract;
				
		
	}
	
	

	
	
	  //get xml 
	  private static void printNote(NodeList nodeList) {
	 
	    for (int count = 0; count < nodeList.getLength(); count++) {
	 
		Node tempNode = nodeList.item(count);
	 
		// make sure it's element node.
		if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
	 
			// get node name and value
			System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
			System.out.println("Node Value =" + tempNode.getTextContent());
	 
			if (tempNode.hasAttributes()) {
	 
				// get attributes names and values
				NamedNodeMap nodeMap = tempNode.getAttributes();
	 
				for (int i = 0; i < nodeMap.getLength(); i++) {
	 
					Node node = nodeMap.item(i);
					System.out.println("attr name : " + node.getNodeName());
					System.out.println("attr value : " + node.getNodeValue());
	 
				}
	 
			}
	 
			if (tempNode.hasChildNodes()) {
	 
				// loop again if has child nodes
				printNote(tempNode.getChildNodes());
	 
			}
	 
			System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");
	 
		}
	 
	    }
	 
	  }
	 

}
