package parser;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import metrics.ClassProperties;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;



public class Parser extends DefaultHandler {

	private static ArrayList<ClassProperties> classes = new ArrayList<ClassProperties>();
	
	// if we want to use map instead
//	public static HashMap<String,Integer>[] hmMethods;
//	public static HashMap<String,Integer>[] hmAttributes;
	
//	HashMap<String,Integer> m = new HashMap<String,Integer>();
//	HashMap<String,Integer> a = new HashMap<String,Integer>();
			
	public static ArrayList<ClassProperties> parse() {

		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			
			DefaultHandler handler = new DefaultHandler() {
				
				ClassProperties cp = new ClassProperties();

				@Override
				public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
					
					if (qName.equalsIgnoreCase("CompilationUnit")) {
					
						String name = attributes.getValue("name");
						String pName = attributes.getValue("handle");
						String pName2 = pName.substring(pName.indexOf('<')+1,pName.indexOf('{'));
						
						cp = new ClassProperties();
						cp.setClassName(name);	
						cp.setPackageName(pName2);
					}

					if (qName.equalsIgnoreCase("Metric") && attributes.getValue("id").equals("NOM")) {

						String str = attributes.getValue("value");
						int numMethods = Integer.parseInt(str);
						cp.setNumMethods(numMethods);
					}

					if (qName.equalsIgnoreCase("Metric") && attributes.getValue("id").equals("NOF")) {

						String str = attributes.getValue("value");
						int numAttributes = Integer.parseInt(str);
						cp.setNumAttributes(numAttributes);
					}	
					
				}

				public void endElement(String uri, String localName, String qName) throws SAXException {
					
					if (qName.equalsIgnoreCase("CompilationUnit")) {
						classes.add(cp);
					}
					
				}

				public void characters(char[] ch, int start, int length) {
				
				}
			};
			
			saxParser.parse("SweetHomeStructure.xml", handler);
			
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		
		return classes;
	}
	
	public static void showData() {
		
		System.out.println("Number of classes: " + classes.size());
		
		for (int i=0; i<classes.size(); i++) {
			
			ClassProperties cp = classes.get(i);
			
			String name = cp.getClassName();
			String pName = cp.getPackageName();
			int methods = cp.getNumMethods();
			int attributes = cp.getNumAttributes();
			
			System.out.println("Class name: " + name);
			System.out.println("Package name: " + pName);
			System.out.println("Number of methods: " + methods);
			System.out.println("Number of attributes: " + attributes);

			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		parse();
		showData();
	}
}