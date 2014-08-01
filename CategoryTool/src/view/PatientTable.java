package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import model.PatientTableModel;

public class PatientTable extends JTable implements TableCellRenderer{

	private static final long serialVersionUID = 9100680929990317995L;
	
	private static final int ROW_HEIGHT = 20;
	
	public PatientTable(PatientTableModel model){
		super(model);

		if(model == null)
			throw new IllegalArgumentException("model == null");
		
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setFillsViewportHeight(true);
		setRowHeight(ROW_HEIGHT);
		setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		setRowSelectionAllowed(false);
		setColumnSelectionAllowed(false);
		setCellSelectionEnabled(false);
		getTableHeader().setReorderingAllowed(false);
		setAutoCreateRowSorter(true);
		
		setColumnRenderer(getColumnModel(), this);
		
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		TableCellRenderer r = null;

		//convert the view indices into the model
		row = row > -1 ? convertRowIndexToModel(row) : row;
		column = convertColumnIndexToModel(column);

		if(row > -1){
			r = getDefaultRenderer(value.getClass());
		}
		else{
			r = getTableHeader().getDefaultRenderer();
		}

		return r.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}
	
	
	/**
	 * Sets the column renderer for the specified model
	 * @param model - The column model
	 * @param renderer - The renderer to use
	 */
	private void setColumnRenderer(TableColumnModel model, TableCellRenderer renderer){
		int columnCount = model.getColumnCount();
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		
		ClassLoader cl = this.getClass().getClassLoader();

		for(int i = 0; i < columnCount; i++){
			TableColumn column = model.getColumn(i);
			column.setCellRenderer(renderer);
			column.setHeaderRenderer(renderer);
			
			if(i == 0){
				column.setMaxWidth(55);
				column.setMinWidth(25);
				column.setHeaderRenderer(iconHeaderRenderer);
				column.setHeaderValue(new TextAndIcon("", new ImageIcon(cl.getResource("images/icon_delete_sm_02.png"))));
			}
			else if(i==2 || i==3 || i==4 || i==5){
				column.setCellRenderer(centerRenderer);
				column.setMaxWidth(75);
			}
			else if(i == 7){
				column.setMaxWidth(0);
				column.setMinWidth(0);
				column.setPreferredWidth(0);
			}
		}
	}
	
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
	    {
	        Component c = super.prepareRenderer(renderer, row, column);
	        
	        if(getValueAt(row, 0) == Boolean.TRUE){
		        Map attributes = c.getFont().getAttributes();
		        attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
	
		        c.setForeground(Color.RED);
		        c.setFont(new Font(attributes));
		        
//		        c.setEnabled(Boolean.FALSE);
	        }
	        else if(getValueAt(row, 7) == Boolean.TRUE){
	        	c.setForeground(Color.GREEN);
	        }
	        else{
	        	c.setForeground(Color.BLACK);
	        }

	        return c;
	    }
	
	// This customized renderer can render objects of the type TextandIcon
	TableCellRenderer iconHeaderRenderer = new DefaultTableCellRenderer() {
	    public Component getTableCellRendererComponent(JTable table, Object value,
	            boolean isSelected, boolean hasFocus, int row, int column) {
	        // Inherit the colors and font from the header component
	        if (table != null) {
	            JTableHeader header = table.getTableHeader();
	            if (header != null) {
	                setForeground(header.getForeground());
	                setBackground(header.getBackground());
	                setFont(header.getFont());
	            }
	        }

	        if (value instanceof TextAndIcon) {
	            setIcon(((TextAndIcon)value).icon);
	            setText(((TextAndIcon)value).text);
	        } else {
	            setText((value == null) ? "" : value.toString());
	            setIcon(null);
	        }
	        setBorder(UIManager.getBorder("TableHeader.cellBorder"));
	        setHorizontalAlignment(JLabel.CENTER);
	        return this;
	    }
	};
	
	class TextAndIcon {
	    TextAndIcon(String text, Icon icon) {
	        this.text = text;
	        this.icon = icon;
	    }
	    String text;
	    Icon icon;
	}
}
