import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RepaintManager;
 
public class GuiScheduler implements ActionListener{
	private JFrame frame;
	private JPanel centerPanel, eastPanel,centerFirst,centerSecond,center, tablePanel;
	private JButton addButton, listButton, loadButton, saveButton;
	private JTextField courseName,credit,start,time,showCourse;
	//private JTextArea content;
	private JComboBox day;
	private String[] comboType = {"","Mon", "Tue", "Wed","Thu","Fri"};
	private String addedString;
	private List list;
	private Course course;
	private JTable contentTable;
	private Object[][] rowData;
	private String[] columnName = {"과목명","학점","요일","시작교시","시간"};
	private int columnCount = 0;
	
	GuiScheduler(){
		this.centerPanel = new JPanel();
		this.eastPanel = new JPanel();
		this.addButton = new JButton("Add");
		this.listButton = new JButton("List");
		this.loadButton = new JButton("Load");
		this.saveButton = new JButton("Save");
		this.centerFirst = new JPanel();
		this.center = new JPanel();
		this.centerSecond = new JPanel();
		this.courseName = new JTextField();
		this.credit = new JTextField();
		this.day = new JComboBox(comboType);
		this.start = new JTextField();
		this.time = new JTextField();
		this.showCourse = new JTextField("My Courses");
		//this.content = new JTextArea();
		this.rowData = new Object[5][100];
		this.contentTable = new JTable(rowData,columnName);
		this.addedString = new String();
		this.list = new List();
		this.course = new Course(); 
		this.tablePanel = new JPanel();
	}
	
	void CreateScheduler(){
		frame = new JFrame("GUI Schduler");
		frame.setSize(500, 500);
		frame.setLayout(new BorderLayout());
		frame.add(centerPanel,BorderLayout.CENTER);
		center.setLayout(new GridLayout(3,1));
		centerPanel.setLayout(new BorderLayout());
		centerFirst.setLayout(new GridLayout(1,5));
		centerFirst.add(new JLabel("    과목명"));
		centerFirst.add(new JLabel("     학점"));
		centerFirst.add(new JLabel("     요일"));
		centerFirst.add(new JLabel("   시작교시"));
		centerFirst.add(new JLabel("     시간"));
		center.add(centerFirst);
		
		centerSecond.setLayout(new GridLayout(1,5));
		centerSecond.add(courseName);
		centerSecond.add(credit);
		centerSecond.add(day);
		centerSecond.add(start);
		centerSecond.add(time);
		center.add(centerSecond);
		
		JScrollPane scrollPane = new JScrollPane(contentTable);   //Create Content Table
		contentTable.setFillsViewportHeight(true);
		tablePanel.setLayout(new BorderLayout());
		tablePanel.add(contentTable.getTableHeader(), BorderLayout.PAGE_START);
		tablePanel.add(contentTable, BorderLayout.CENTER);
		
		
		showCourse.setEditable(false);
		//content.setEditable(false);
		center.add(showCourse);
		centerPanel.add(center,BorderLayout.NORTH);
		centerPanel.add(tablePanel,BorderLayout.CENTER);
		
		frame.add(eastPanel,BorderLayout.EAST);
		eastPanel.setLayout(new GridLayout(4,1));
		eastPanel.add(addButton);
		eastPanel.add(listButton);
		eastPanel.add(loadButton);
		eastPanel.add(saveButton);
		addButton.addActionListener(this);
		listButton.addActionListener(this);
		loadButton.addActionListener(this);
		saveButton.addActionListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.pack();
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == addButton){
			addedString = addedString + courseName.getText() +"    "+ credit.getText() + "    "+
			day.getSelectedItem().toString() + "    "+ start.getText() 
			+"    "+ time.getText() + "\n";
			//content.setText(addedString);
			rowData[columnCount][0] = courseName.getText();
			rowData[columnCount][1] = credit.getText();
			rowData[columnCount][2] = day.getSelectedItem().toString();
			rowData[columnCount][3] = start.getText();
			rowData[columnCount][4] = time.getText();
			columnCount++;
			frame.repaint();
			
			course.setCourseName(courseName.getText());
			course.setCredit(Integer.parseInt(credit.getText()));
			course.setDay(day.getSelectedItem().toString());
			course.setStartTime(Integer.parseInt(start.getText()));
			course.setTime(Integer.parseInt(time.getText()));
		}
		else if(e.getSource() == listButton){
			list.showList();
			
			list.courseTable.addMouseListener(new MouseAdapter() {
			    public void mousePressed(MouseEvent me) {
			        JTable table =(JTable) me.getSource();
			        Point p = me.getPoint();
			        int row = table.rowAtPoint(p);
			        if (me.getClickCount() == 2) {
			            // your valueChanged overridden method 
			        	courseName.setText(list.courseName);
			        	credit.setText(Integer.toString(list.credit));
			        	day.setSelectedItem(list.day);
			        	start.setText(Integer.toString(list.startTime));
			        	time.setText(Integer.toString(list.time));
			        }
			    }
			});
		}
		else if(e.getSource() == loadButton){
			int count = 0;
			columnCount = 0;
			ArrayList<String> loadData = new ArrayList<String>();
			loadData = course.load();
			while(count < loadData.size()){
				rowData[columnCount][0] = loadData.get(count);
				rowData[columnCount][1] = loadData.get(count+1);
				rowData[columnCount][2] = loadData.get(count+2);
				rowData[columnCount][3] = loadData.get(count+3);
				rowData[columnCount][4] = loadData.get(count+4);
				columnCount++;
				count += 5;
			}
			frame.repaint();
		}
		else if(e.getSource() == saveButton){
			course.save(rowData);
		}
	}
	
}