package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;


public class PatientTableModel extends AbstractTableModel{
	
	private static final long serialVersionUID = -5722147852749124699L;
	
	public static final int DELETED_COLUMN = 0;
	public static final int PATIENT_NAME_COLUMN = 1;
	public static final int SCREENING_CATEGORY_COLUMN = 2;
	public static final int DIAGNOSTIC_CATEGORY_COLUMN = 3;
	public static final int ULTRASOUND_COLUMN = 4;
	public static final int BIOPSY_RESULT = 5;
	public static final int DATE_COLUMN = 6;
	public static final int ADDED_COLUMN = 7;

	private static final String[] COLUMNS = {"", "Name", "Screening", "Diagnostic", "Ultrasound", "Biopsy Result", "Date", ""};
	
//	private static final Class<?>[] TYPES = { Boolean.class, String.class, Integer.class, Integer.class, String.class };
	
	private final List<Patient> rows;
	
	/**
	 * Create a new empty file upload table model
	 */
	public PatientTableModel(){
		rows = new ArrayList<Patient>();
	}

	public List<Patient> getRows() {
		return rows;
	}

	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}

	@Override
	public int getRowCount() {
		return rows.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Patient row = rows.get(rowIndex);
		Object value = null;
		if(columnIndex == DELETED_COLUMN)
			value = row.getDeleted();
		else if(columnIndex == PATIENT_NAME_COLUMN)
			value = row.getName();
		else if(columnIndex == SCREENING_CATEGORY_COLUMN){
			/*if(row.getScreening_category() == -1){
				value = "N/A";
			}
			else{
				value = row.getScreening_category();
			}*/
			value = row.getScreening_category();
		}
		else if(columnIndex == DIAGNOSTIC_CATEGORY_COLUMN){
			/*if(row.getDiagnostic_category() == -1){
				value = "N/A";
			}
			else{
				value = row.getDiagnostic_category();
			}*/
			value = row.getDiagnostic_category();
		}
		else if(columnIndex == ULTRASOUND_COLUMN){
			/*if(row.getUltrasound() == -1){
				value = "N/A";
			}
			else{
				value = row.getUltrasound();
			}*/
			value = row.getUltrasound();
		}
		else if(columnIndex == BIOPSY_RESULT){
			/*if(row.getBiopsy_result() == -1){
				value = "N/A";
			}
			else{
				value = row.getBiopsy_result();
			}*/
			value = row.getBiopsy_result();
		}
		else if(columnIndex == DATE_COLUMN)
			value = row.getDate();
		else
			value = row.getAdded();
		
		return value;
	}
	
	@Override
	public String getColumnName(int index){
		return COLUMNS[index];
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
	    if (columnIndex == DELETED_COLUMN)
	        return Boolean.class;
	    else if (columnIndex == PATIENT_NAME_COLUMN)
	        return String.class;
	    else if (columnIndex == SCREENING_CATEGORY_COLUMN)
	        return Integer.class;
	    else if (columnIndex == DIAGNOSTIC_CATEGORY_COLUMN)
	        return Integer.class;
	    else if(columnIndex == ULTRASOUND_COLUMN)
			return Integer.class;
	    else if(columnIndex == BIOPSY_RESULT)
	    	return Integer.class;
	    else if (columnIndex == DATE_COLUMN)
	        return Date.class;
	    
	    return super.getColumnClass(columnIndex);
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
	    return (col == DELETED_COLUMN); 
	}
	
	/**
	 * Adds the specified row to this model
	 * @param row - The row to add
	 * @return - True
	 */
	public boolean add(Patient row){
		rows.add(row);
		fireTableDataChanged();
		
		return true;
	}
	
	/**
	 * Removes the item at the given index
	 * @param index - The index of the element to remove
	 * @return - The element that was removed
	 */
	public Patient remove(int index){
		Patient row = rows.remove(index);
		fireTableRowsDeleted(index, index);
		
		return row;
	}
	
	@Override
	public void setValueAt(Object obj, int rowIndex, int columnIndex){
		if (columnIndex == DELETED_COLUMN) {
	        rows.get(rowIndex).setDeleted((Boolean)obj);
	    }
		fireTableDataChanged();
	}
	
	public void updateTable(){
		fireTableDataChanged();
	}

}
