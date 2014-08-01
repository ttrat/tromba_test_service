package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import org.jdom2.*;
import org.jdom2.input.*;
import org.jdom2.output.*;

public class RecordWriter {
	
	private Document document = new Document();
	private SAXBuilder builder = new SAXBuilder();
	
	private final String PATIENT_ELEMENT_NAME = "patient";
	
	public void updateRecords(List<Patient> patients, String recordPath) throws IOException, JDOMException{
		File input = new File(recordPath);
		
		if(!input.exists()){
        	input.createNewFile();
        	String myxml = "<records></records>";
        	document = builder.build(new StringReader(myxml));
        }
		
		else{
			document = builder.build(input);
		}
		
		for(Patient patient : patients){
			
			if(patient.getAdded() == Boolean.TRUE && patient.getDeleted() == Boolean.FALSE){
				this.addPatient(patient, recordPath);
			}
			else if(patient.getAdded() == Boolean.FALSE && patient.getDeleted() == Boolean.TRUE){
				this.removePatient(patient.getName(), recordPath);
			}
			
		}
		
		writeToFile(document, recordPath);
	}
	
	public void addPatient(Patient patient, String recordPath) throws IOException, JDOMException {
	        File input = new File(recordPath);
	        
	        if(!input.exists()){
	        	input.createNewFile();
	        	String myxml = "<records></records>";
	        	document = builder.build(new StringReader(myxml));
	        }
	        
	        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	        
	        Element element = new Element(PATIENT_ELEMENT_NAME);
	        element.setAttribute("name", patient.getName());
	        element.setAttribute("screening", Integer.toString(patient.getScreening_category()));
	        element.setAttribute("diagnostic", Integer.toString(patient.getDiagnostic_category()));
	        element.setAttribute("ultrasound", Integer.toString(patient.getUltrasound()));
	        element.setAttribute("biopsy", Integer.toString(patient.getBiopsy_result()));
	        element.setAttribute("date", formatter.format(patient.getDate()));
	        
	        document.getRootElement().addContent(element);
	    }
	
	public void removePatient(String name, String recordPath) throws JDOMException, IOException{
    	
    	List<Element> elements = document.getRootElement().getChildren(PATIENT_ELEMENT_NAME);
    	
    	 for ( Iterator<Element> iter = elements.iterator(); iter.hasNext(); )
         {
	         Element element = iter.next();
	         if(element.getAttribute("name").getValue().equals(name)){
	        	 iter.remove();
	        	 document.getRootElement().removeContent(element);
	         }
         }
	}
	
	private static void writeToFile(Document document, String filePath) throws IOException{
		OutputStream out = null;
        
        try{
        	out = new FileOutputStream(filePath);
        	
        	XMLOutputter serializer = new XMLOutputter();
        	serializer.setFormat(Format.getPrettyFormat());
        	serializer.output(document, out);
             
        }catch(Exception e){
        	e.printStackTrace();
        }
        finally{
        	out.close();
        }
	}
	
}