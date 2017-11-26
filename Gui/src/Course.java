import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Course {
	public File schedule;
	public File loadFile;
	public String courseName, day;
	public int credit,startTime, time;
	public File file;
	public Scanner read;
	public String[] token = new String[6];
	private String addedString;
	private int count = 0;
	
	
	public void save(Object[][] saveData){
		int count = 0;
		String tempString = "";
		try {
			schedule = new File("myCourse.txt");
			FileWriter writer = new FileWriter(schedule);

			while(count <= saveData.length / 5){
				if(tempString.isEmpty()){
					tempString = saveData[count][0] + ":" + saveData[count][1] + ":" + saveData[count][2] + ":" + saveData[count][3] + ":"
							+ saveData[count][4];
				}else{
					tempString = tempString + "\n" +saveData[count][0] + ":" + saveData[count][1] + ":" + saveData[count][2] + ":" 
				+ saveData[count][3] + ":" + saveData[count][4];
				}
				count++;
			}
			writer.write(tempString);
			writer.close();
		} catch (IOException e) {
			System.out.println("no file");
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> load(){
		schedule = new File("myCourse.txt");
		ArrayList<String> passData = new ArrayList<String>();
		try {
			read = new Scanner(schedule);
			do{
				readNext();
				courseName = courseName.replaceAll(" ", "");
				passData.add(token[count]);
				passData.add(token[count+1]);
				passData.add(token[count+2]);
				passData.add(token[count+3]);
				passData.add(token[count+4]);
				//count += 5;
				if(addedString == null){
					addedString = token[0]+":"+token[1]+":"+token[2]+":"+token[3]+":"+token[4]+"\n";
				}else{
					addedString = addedString +":"+token[0]+":"+token[1]+":"+token[2]+":"+token[3]+":"+token[4]+"\n";
				}
				//System.out.println(addedString);
			}while(read.hasNext());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return passData;
	}
	
	public void setCourseName(String courseName){
		this.courseName = courseName;
	}
	
	public void setDay(String day){
		this.day = day;
	}
	
	public void setCredit(int credit){
		this.credit = credit;
	}
	
	public void setStartTime(int startTime){
		this.startTime = startTime;
	}
	
	public void setTime(int time){
		this.time = time;
	}

	public void readNext(){
		try {
			String temp = read.nextLine();
			tSeperator(temp);
		} catch (Exception e) {
		}
		
	}
	
	public void tSeperator(String scheduleLine){
		scheduleLine = scheduleLine.replaceAll(" ", "");
		token = scheduleLine.split(":");
		if(scheduleLine.equals("")){
			//day = "";
		}
		else if(scheduleLine.startsWith("//")){
			
			//day = "";
			
		}
		else if(token.length > 5){
			System.out.println("Irregular schedule line");
		}
		else{
			courseName = token[0];
			//course = st.nextToken() + "      ";s
			if(courseName.length() >= 4){
				courseName = courseName.substring(0, 3) + "     ";
			}
			credit = Integer.parseInt(token[1]);
			day = token[2];
			startTime = Integer.parseInt(token[3]);
			time = Integer.parseInt(token[4]);
		}
	}
	
}
