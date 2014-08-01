package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import model.AuditTableModel;
import model.Patient;
import model.PatientTableModel;
import model.StatisticsTableModel;

import controller.CategoryController;

public class CategoryView extends JPanel implements WindowListener{
	
	private static final long serialVersionUID = -624816261262115480L;
	
	private static final String ADD_ACTION = "add";
	private static final String LOAD_ACTION = "load";
	private static final String SAVE_ACTION = "save";
	private static final String EXIT_ACTION = "exit";
	
	private final ComboItem[] categories = {new ComboItem(-1, "N/A"),
													new ComboItem(0),
													new ComboItem(1),
													new ComboItem(2),
													new ComboItem(3),
													new ComboItem(4),
													new ComboItem(5),
													new ComboItem(6)};
	
	private final JTable patientTable;
	private final CategoryController controller;
	private JFrame frame;
	private final PatientTableModel patientTableModel;
	private final StatisticsTableModel statsTableModel;
	private final AuditTableModel auditTableModel;
	
	private final JButton saveButton;

	public CategoryView(){
		patientTableModel = new PatientTableModel();
		patientTable = new PatientTable(patientTableModel);
		
		statsTableModel = new StatisticsTableModel();
		auditTableModel = new AuditTableModel();
		
		controller = new CategoryController();
		
		ActionListener handler = new ActionHandler();
		
		
		saveButton = new JButton("Save");
		saveButton.setActionCommand(SAVE_ACTION);
		saveButton.addActionListener(handler);
		initialize();
	}


	@Override
	public void windowActivated(WindowEvent arg0) {
	}


	@Override
	public void windowClosed(WindowEvent arg0) {
	}


	@Override
	public void windowClosing(WindowEvent arg0) {
	}


	@Override
	public void windowDeactivated(WindowEvent arg0) {
	}


	@Override
	public void windowDeiconified(WindowEvent arg0) {
	}


	@Override
	public void windowIconified(WindowEvent arg0) {
	}


	@Override
	public void windowOpened(WindowEvent arg0) {
	}
	
	private void addAction(){
		
		JTextField patientName = new JTextField();
		JComboBox<ComboItem> screening = new JComboBox<ComboItem>(categories);
		JComboBox<ComboItem> diagnostic = new JComboBox<ComboItem>(categories);
		JComboBox<ComboItem> ultrasound = new JComboBox<ComboItem>(categories);
		JComboBox<ComboItem> biopsy = new JComboBox<ComboItem>(categories);
//		JTextField date = new JTextField();
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		final JComponent[] inputs = new JComponent[] {
				new JLabel("Patient Name"),
				patientName,
				new JLabel("Screening Category"),
				screening,
				new JLabel("Diagnostic Category"),
				diagnostic,
				new JLabel("Ultrasound Result"),
				ultrasound,
				new JLabel("Biopsy Result"),
				biopsy,
				new JLabel("Date of Imaging"),
				datePicker
		};
		
		int c = JOptionPane.showConfirmDialog(null, inputs, "Add Patient", JOptionPane.PLAIN_MESSAGE);
		
		if(c != JOptionPane.CLOSED_OPTION){
		
			if(patientName.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Cannot add patient with empty name", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(datePicker.getModel().getValue() == null){
				JOptionPane.showMessageDialog(null, "Cannot add patient with empty imaging date", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			else{
				Calendar cal = Calendar.getInstance();
				cal.set(datePicker.getModel().getYear(), datePicker.getModel().getMonth(), datePicker.getModel().getDay());
				Date imageDate = cal.getTime();
				
				Patient newPatient = new Patient(patientName.getText(), ((ComboItem)screening.getSelectedItem()).getValue(), ((ComboItem)diagnostic.getSelectedItem()).getValue(), ((ComboItem)ultrasound.getSelectedItem()).getValue(), ((ComboItem)biopsy.getSelectedItem()).getValue(), imageDate);
				
				newPatient.setAdded(true);
				controller.getPatients().add(newPatient);
				patientTableModel.add(newPatient);
				patientTableModel.updateTable();
			}
		}
		
	}
	
	private void loadAction(){
		
		controller.actionBrowseFile();
		
		patientTableModel.getRows().clear();
		patientTableModel.getRows().addAll(controller.getPatients());
		
		statsTableModel.getRows().clear();
		statsTableModel.getRows().addAll(controller.getPatients());
		
		auditTableModel.getRows().clear();
		auditTableModel.getRows().addAll(controller.getPatients());
		
		patientTableModel.updateTable();
		statsTableModel.updateTable();
		auditTableModel.updateTable();
	}
	
	private void saveAction(){
		
		controller.save();
		
		patientTableModel.getRows().clear();
		patientTableModel.getRows().addAll(controller.getPatients());
		
		statsTableModel.getRows().clear();
		statsTableModel.getRows().addAll(controller.getPatients());
		
		auditTableModel.getRows().clear();
		auditTableModel.getRows().addAll(controller.getPatients());

		
		patientTableModel.updateTable();
		statsTableModel.updateTable();
		auditTableModel.updateTable();
		
	}
	
	private void exitAction(){
		System.exit(0);
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CategoryView window = new CategoryView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new MenuFrame();
		frame.setBounds(100, 100, 100, 100);
		frame.setSize(1200, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*
		 * Default record loaded
		 */
			String recordPath = System.getProperty("user.home")+File.separator+"default_record.xml"; 
			controller.setRecordsLocation(recordPath); //need standard user location for windows/mac/linux
			controller.loadRecords();
			patientTableModel.getRows().addAll(controller.getPatients());
			
			statsTableModel.getRows().addAll(controller.getPatients());
			statsTableModel.updateTable();
			
			auditTableModel.getRows().addAll(controller.getPatients());
			auditTableModel.updateTable();
		/*
		 * 
		 */
		JPanel statPanel = new JPanel();
		JPanel outcomePanel = new JPanel();
		outcomePanel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createLineBorder(Color.BLACK),
                "Patient Outcome Data",
                TitledBorder.LEFT,
                TitledBorder.TOP));
		
		JTable statsTable = new JTable(statsTableModel);
		statsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		statsTable.setOpaque(false);
		((DefaultTableCellRenderer)statsTable.getDefaultRenderer(Object.class)).setOpaque(false);
		statsTable.getColumnModel().getColumn(1).setMinWidth(200);
		statsTable.getColumnModel().getColumn(1).setMaxWidth(200);
		
		statsTable.setPreferredScrollableViewportSize(new Dimension(600,(int)statsTable.getPreferredSize().getHeight()));
		statsTable.setFillsViewportHeight(true);

		outcomePanel.add(new JScrollPane(statsTable));
		
		JPanel auditPanel = new JPanel();
		auditPanel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createLineBorder(Color.BLACK),
                "Audit Pathology Summary",
                TitledBorder.LEFT,
                TitledBorder.TOP));
//		auditPanel.setBorder (BorderFactory.createTitledBorder("Audit Pathology Summary"));
		
		/*Object [][] tempAuditModel = {{"# of Mammogram Cases:",controller.getAudits().getNumCases()},{"# of BI-RADS category 4:",controller.getAudits().getNumCatFour()}};
		JTable auditTable = new JTable(tempAuditModel);
		auditPanel.add(auditTable);*/
		
		JTable auditTable = new JTable(auditTableModel);
//		auditTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		auditTable.setOpaque(false);
		((DefaultTableCellRenderer)auditTable.getDefaultRenderer(Object.class)).setOpaque(false);
		auditTable.getColumnModel().getColumn(0).setMinWidth(50);
		auditTable.getColumnModel().getColumn(0).setMaxWidth(50);
		
		auditTable.setPreferredScrollableViewportSize(new Dimension(600,(int)auditTable.getPreferredSize().getHeight()));
		auditTable.setFillsViewportHeight(true);
		auditTable.setShowGrid(false);
		
		auditPanel.add(new JScrollPane(auditTable));
//		auditPanel.add(new JLabel("This is a placeholder..."));
		
		statPanel.setLayout(new BoxLayout(statPanel, BoxLayout.Y_AXIS));
		statPanel.add(outcomePanel);
		statPanel.add(auditPanel);
		
		JScrollPane tablePane = new JScrollPane(patientTable);
	    
		frame.getContentPane().add(tablePane, BorderLayout.CENTER);
		frame.getContentPane().add(statPanel, BorderLayout.EAST);
		
	}
	
	//handles all the input
	private class ActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String action = e.getActionCommand();
			
			if(action.equals(ADD_ACTION))
				addAction();
			else if(action.equals(LOAD_ACTION))
				loadAction();
			else if(action.equals(SAVE_ACTION))
				saveAction();
			else if(action.equals(EXIT_ACTION))
				exitAction();
		}
	}
	
	private class MenuFrame extends JFrame{
		
		private static final long serialVersionUID = -5022817404550375422L;

		public MenuFrame() {
	        initUI();
		}

	    public final void initUI() {
	
	        JMenuBar menubar = new JMenuBar();
	
	        JMenu file = new JMenu("File");
	        file.setMnemonic(KeyEvent.VK_F);
	        
	        ActionListener handler = new ActionHandler();
	
	        JMenuItem exitMenuItem = new JMenuItem("Exit");
	        exitMenuItem.setMnemonic(KeyEvent.VK_E);
	        exitMenuItem.setToolTipText("Exit application");
	        exitMenuItem.setActionCommand(EXIT_ACTION);
	        exitMenuItem.addActionListener(handler);
	        
	        JMenuItem loadMenuItem = new JMenuItem("Load Record...");
	        loadMenuItem.setMnemonic(KeyEvent.VK_E);
	        loadMenuItem.setToolTipText("Load existing record file");
	        loadMenuItem.setActionCommand(LOAD_ACTION);
	        loadMenuItem.addActionListener(handler);
	        
	        JMenuItem saveMenuItem = new JMenuItem("Save Record...");
	        saveMenuItem.setMnemonic(KeyEvent.VK_E);
	        saveMenuItem.setToolTipText("Save changes to this record file");
	        saveMenuItem.setActionCommand(SAVE_ACTION);
	        saveMenuItem.addActionListener(handler);
	        
	        JMenuItem addMenuItem = new JMenuItem("Add Patient...");
	        addMenuItem.setMnemonic(KeyEvent.VK_E);
	        addMenuItem.setToolTipText("Add new patient to this record file");
	        addMenuItem.setActionCommand(ADD_ACTION);
	        addMenuItem.addActionListener(handler);
	
	        file.add(addMenuItem);
	        file.add(loadMenuItem);
	        file.add(saveMenuItem);
	        file.add(exitMenuItem);
	
	        menubar.add(file);
	
	        setJMenuBar(menubar);
	
	        setTitle("Imaging Category Tool");
	        setSize(300, 200);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	    }
	}
	
	private class ComboItem{
	    private Integer value;
	    private String label;

	    public ComboItem(Integer value, String label) {
	        this.value = value;
	        this.label = label;
	    }
	    
	    public ComboItem(Integer value){
	    	this.value = value;
	    	this.label = value.toString();
	    }

	    public Integer getValue() {
	        return this.value;
	    }

	    public String getLabel() {
	        return this.label;
	    }

	    @Override
	    public String toString() {
    		return label;
	    }
	    
	}

}
