package com.mkyong.rest;

import gr.agroknow.config.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javassist.convert.Transformer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;




public class StartFreme {

	public static void main(String[] args) {
		
		//ParamManager.getInstance().setParam( args ) ;
		//File inputDirectory = new File( ParamManager.getInstance().getInputFolder() ) ;
	//	File inputDirectory = new File( "C:\\Users\\papou_000\\Desktop\\agroknow\\test_list\\ru_test.xml") ;
	//	FileReader fr = null ;		
		 String filepath = "C:\\Users\\papou_000\\Desktop\\agroknow\\test_list\\ru_test.xml";
	//	for (String filepath: inputDirectory.list() )
		//{
	
	      try {  	  
		     DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
     	     DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		     Document doc = docBuilder.parse(filepath);
		
		     ReadXMLFile readxml = new  ReadXMLFile();
		     readxml.setDocument(doc); //pass the xml document to parser
		     readxml.createAgrovoElement(filepath );
		     
		   //call the freme utilities
		  // FREMEClientAgrovoc fremeClient= new FREMEClientAgrovoc();
		   //postText
		  // fremeClient.postText(node.getTextContent(), "en", "en");		
		//http://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/
		  // write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File("C:\\Users\\papou_000\\Desktop\\agroknow\\test_list\\1.xml"));
				transformer.transform(source, result);
		 
				System.out.println("Agroknow AKStem Enrichment Done");
			 
	    } catch (ParserConfigurationException pce) {
		 pce.printStackTrace();
	    } catch (TransformerException tfe) {
		 tfe.printStackTrace();
	    } catch (IOException ioe) {
		 ioe.printStackTrace();
	    } catch (SAXException sae) {
		 sae.printStackTrace();
	    }
		
	}
	
	
//	}
	
	
}
