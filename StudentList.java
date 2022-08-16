import java.io.*;
import java.text.*;
import java.util.*;
public class StudentList {
	public static void main(String[] args) {
//		Check arguments
		if(args[0].equals(Constant.showAll)) {											// to print all the students name
			System.out.println(Constant.loadingMassage);			
			try {
				String studentNames[] = readData().split(Constant.studentNameDivider);	//read data and split on a string array		
				for(String studentName : studentNames) { 
					System.out.println(studentName); 
				}
			} catch (Exception e) {	} 
			System.out.println(Constant.completeMassage);
		}
		else if(args[0].equals(Constant.randomName)) {									// to print a Student name randomly
			System.out.println(Constant.loadingMassage);			
			try {
				String studentNames[] = readData().split(Constant.studentNameDivider);	//read data and split on a string array
				System.out.println(studentNames[new Random().nextInt(studentNames.length)]);
			} catch (Exception e) { } 
			System.out.println(Constant.completeMassage);			
		}
		else if(args[0].contains(Constant.add)) {										// to add a name to the student list
			System.out.println(Constant.loadingMassage);			
			try {
				writeData(args[0]);														// writing data to file method on 76th line
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(Constant.completeMassage);	
		}
		else if(args[0].contains(Constant.check)) {										// to check if name in given argument exists
			System.out.println(Constant.loadingMassage);			
			try {
				String studentNames[] = readData().split(Constant.studentNameDivider);
				for(int index = 0; index < studentNames.length ; index++) {
					if(studentNames[index].equals(args[0].substring(1))) {
						System.out.println(Constant.founded + index);
						break;															//break if found the name
					}
					if(index == (studentNames.length-1)){
						System.out.println(Constant.notFound);
					}
				}
			} catch (Exception e) { } 
			System.out.println(Constant.completeMassage);			
		}
		else if(args[0].contains(Constant.counter)) {									//count the number of students name
			System.out.println(Constant.loadingMassage);			
			try {
				char studentNames[] = readData().toCharArray();							//split all the charecters in a charecter array
				int count = 1;
				for(char charecter:studentNames) {
					if(charecter == ',') {
						count++;
					}
				}
				System.out.println(count + Constant.foundMassage);
			} catch (Exception e){ } 
			System.out.println(Constant.completeMassage);				
		}
		else{																			// to handle if wrong argument passed
			System.out.println(Constant.wrongArgumentPassed);
		}
	}
	//In order to read data;
	static String readData() throws Exception{
		BufferedReader bufferReader = new BufferedReader(
			new InputStreamReader(
				new FileInputStream(Constant.fileName)));
		return bufferReader.readLine();
	}
	//In order to write data on student list
	static void writeData(String args)throws Exception{
		String nameLine = readData();
				BufferedWriter bufferedWriter = new BufferedWriter(
					new FileWriter(Constant.fileName, false));
		String formatDate= new SimpleDateFormat(Constant.dateFormat).format(new Date());
		bufferedWriter.write(nameLine + Constant.studentNameDivider + args.substring(1) + "\nList last updated on " + formatDate);
		bufferedWriter.flush();
		bufferedWriter.close();
	}
}