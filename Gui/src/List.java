import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;



public class List extends Course{
	private JFrame listFrame;
	private JTextField title;
	public JTable courseTable;
	private String[] columnName = {"怨쇰ぉ紐�","����","����","����援���","��媛�"};
	private JPanel tablePanel;
	private Object[][] rowData;
	private File listFile;
	private int count = 0;
	private String courseLine;
	
	List(){
		this.rowData = new Object[5][100];
		this.title = new JTextField("List of Courses - Click to select");
		this.courseTable = new JTable(rowData,columnName);
		this.tablePanel= new JPanel();
	}
	
	public void showList(){
		listFrame = new JFrame("CourseList");
		listFrame.setSize(500, 500);
		listFrame.setLocation(500, 0);
		listFrame.setLayout(new BorderLayout());
		
		getList("courseList.txt");
		rowData[count][0] = courseName;
		rowData[count][1] = credit;
		rowData[count][2] = day;
		rowData[count][3] = startTime;
		rowData[count][4] = time;
		count++;
		do{
			readNext();
			
			if(startTime > 9){
				System.out.println("Unresolved hour");
				break;
			}
			else{
				rowData[count][0] = courseName;
				rowData[count][1] = credit;
				rowData[count][2] = day;
				rowData[count][3] = startTime;
				rowData[count][4] = time;
				count++;
			}
		}while(read.hasNext());

		courseTable.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent me) {
		        JTable table =(JTable) me.getSource();
		        Point p = me.getPoint();
		        int row = table.rowAtPoint(p);
		        if (me.getClickCount() == 2) {
		            // your valueChanged overridden method 
		        	courseName = (String) rowData[row][0];
		        	credit = (int) rowData[row][1];
		        	day = rowData[row][2].toString();
		        	startTime = (int) rowData[row][3];
		        	time = (int) rowData[row][4];
		        }
		    }
		});
		
		title.setEditable(false);
		listFrame.add(title,BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane(courseTable);
		courseTable.setFillsViewportHeight(true);
		tablePanel.setLayout(new BorderLayout());
		tablePanel.add(courseTable.getTableHeader(), BorderLayout.PAGE_START);
		tablePanel.add(courseTable, BorderLayout.CENTER);
		
		
		listFrame.add(tablePanel,BorderLayout.CENTER);
		
		listFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		listFrame.setVisible(true);
	}
	
	
	public void getList(String fileName){
		listFile = new File(fileName);
		try {
			read = new Scanner(listFile);
			readNext();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		
	
}
