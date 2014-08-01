package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import model.AuditStats;
import model.Patient;
import model.RecordReader;
import model.RecordWriter;

public class CategoryController {
	
	//test!
	
	private List<Patient> patients = new ArrayList<Patient>(0);
	
	private RecordReader reader = new RecordReader();
	private RecordWriter writer = new RecordWriter();
	
	private AuditStats audits = new AuditStats();
	
	private String recordsLocation = "";
	
	public AuditStats getAudits() {
		return audits;
	}
	public void setAudits(AuditStats audits) {
		this.audits = audits;
	}
	public List<Patient> getPatients() {
		return patients;
	}
	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}
	public String getRecordsLocation() {
		return recordsLocation;
	}
	public void setRecordsLocation(String recordsLocation) {
		this.recordsLocation = recordsLocation;
	}
	
	public void loadRecords(){
		try {
			
			reader.readRecord(recordsLocation);
			
			this.setPatients(reader.getPatients());
			
			audits.calculateStats(patients);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void save(){
		try{
			
			writer.updateRecords(patients, recordsLocation);
			
		}catch(Exception e){
			System.out.println("Error occured while trying to save the records");
			e.printStackTrace();
		}
		
		loadRecords();
		
		this.setPatients(reader.getPatients());
			
	}
	
	public void actionBrowseFile(){
		JFileChooser chooser = new JFileChooser("");
    	chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    	chooser.setMultiSelectionEnabled(false);
    	chooser.removeChoosableFileFilter(chooser.getAcceptAllFileFilter());
//    	chooser.setFileFilter(new FileFilter(){
//			
//			public boolean accept(File f) {
//				return f.getName().toLowerCase().endsWith(".xml") || f.isDirectory();
//			}
//			
//			public String getDescription() {
//				return "XML_FILES_ONLY";
//			}
//    		
//    	});
	    int returnVal = chooser.showOpenDialog(new JFrame());
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	if(chooser.getSelectedFile().getAbsoluteFile().toString().toLowerCase().endsWith(".xml")){
	    		setRecordsLocation(chooser.getSelectedFile().getAbsolutePath());
	    	}
	    }
	    
	    loadRecords();
	}
	
	public void calcStats(){
		StatsCalculator calc = new StatsCalculator();
		calc.calcCatStats(patients);
	}
}
