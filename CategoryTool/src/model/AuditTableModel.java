package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class AuditTableModel extends AbstractTableModel{
	
	private static final long serialVersionUID = -8783384299192509623L;
	
	public static final int COUNT_COLUMN = 0;
	public static final int DESCRIPTION_COLUMN = 1;
	

	private static final String[] COLUMNS = {"Count", "Description"};
	
	private static final String[] DESCRIPTIONS = {"# of mammogram cases",
												"# of BI-RADS category 4",
												"# of BI-RADS category 5"
	};
	
	private final List<Patient> rows;
	private AuditStats auditStats;
	
	public AuditTableModel(){
		rows = new ArrayList<Patient>();
		auditStats = new AuditStats();
	}

	public List<Patient> getRows() {
		return rows;
	}

	public AuditStats getAuditStats() {
		return auditStats;
	}

	public void setAuditStats(AuditStats auditStats) {
		this.auditStats = auditStats;
	}
	
	public void updateTable(){
		auditStats.calculateStats(rows);
		fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}

	@Override
	public int getRowCount() {
		return DESCRIPTIONS.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object value = null;
		
		if(columnIndex == COUNT_COLUMN){
			value = auditStats.getCounts()[rowIndex];
		}
		else if (columnIndex == DESCRIPTION_COLUMN){
			value = DESCRIPTIONS[rowIndex];
		}
		
		return value;
	}
	
	@Override
	public String getColumnName(int index){
		return COLUMNS[index];
	}

}
