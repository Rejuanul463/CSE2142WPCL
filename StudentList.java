import java.io.*;
import java.text.*;
import java.util.*;
public class StudentList {
	public static void main(String[] args) {
//		Check arguments
		if(args[0].equals(Constant.showAll)) {
			System.out.println(Constant.loadingMassage);			
			try {
				String nameLine = readData();
				String studentNames[] = nameLine.split(Constant.studentNameDivider);			
				for(String j : studentNames) { 
					System.out.println(j); 
				}
			} catch (Exception e) {

			} 
			System.out.println(Constant.completeMassage);
		}
		else if(args[0].equals(Constant.randomName)) {
			System.out.println(Constant.loadingMassage);			
			try {
				String studentName = readData();
				String studentNames[] = studentName.split(Constant.studentNameDivider);	
				Random random = new Random();
				int len = random.nextInt(studentNames.length);
				System.out.println(studentNames[len]);
			} catch (Exception e) {

			} 
			System.out.println(Constant.completeMassage);			
		}
		else if(args[0].contains(Constant.add)) {
			System.out.println(Constant.loadingMassage);			
			try {
				String nameLine = readData();
				BufferedWriter bufferedWriter = new BufferedWriter(
					new FileWriter(Constant.fileName, false));
				String addName = args[0].substring(1);
				Date date = new Date();
				DateFormat dateFormat = new SimpleDateFormat(Constant.dateFormat);
				String formatDate= dateFormat.format(date);
				bufferedWriter.write(nameLine + Constant.studentNameDivider +addName+"\nList last updated on "+formatDate);
				bufferedWriter.flush();
				bufferedWriter.close();
			} catch (Exception e) {
				e.printStackTrace();
			}			
			System.out.println(Constant.completeMassage);	
		}
		else if(args[0].contains(Constant.check)) {
			System.out.println(Constant.loadingMassage);			
			try {
				String studentNameLine = readData();
				String studentNames[] = studentNameLine.split(Constant.studentNameDivider);	
				boolean done = false;
				String argumentValue = args[0].substring(1);
				for(int idx = 0; idx<studentNames.length && !done; idx++) {
					if(studentNames[idx].equals(argumentValue)) {
						System.out.println(Constant.founded);
						done=true;
					}
				}
			} catch (Exception e) {

			} 
			System.out.println(Constant.completeMassage);				
		}
		else if(args[0].contains(Constant.checker)) {
			System.out.println(Constant.loadingMassage);			
			try {
				String studentName = readData();
				char studentNames[] = studentName.toCharArray();			
				boolean in_word = false;
				int count=0;
				for(char c:studentNames) {
					if(c == ',') {
						if (!in_word) {	
							count++; 
							in_word = true;	
						}
						else { 
							in_word = false;
						}			
					}
				}
				System.out.println(count + Constant.foundMassage);
			} catch (Exception e){

			} 
			System.out.println(Constant.completeMassage);				
		}
		else{
			System.out.println(Constant.waPassed);
		}
	}

	static String readData() throws Exception{
		BufferedReader bufferReader = new BufferedReader(
					new InputStreamReader(
						new FileInputStream(Constant.fileName))); 
		String nameLine = bufferReader.readLine();
		return nameLine;
	}
}