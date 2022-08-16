import java.io.*;
import java.text.*;
import java.util.*;
public class StudentList {
	public static void main(String[] args) {
//		Check arguments
		// to print all the students name
		if(args[0].equals(Constant.showAll)) {
			System.out.println(Constant.loadingMassage);			
			try {
				//read data and split on a string array	
				String studentNames[] = readData().split(Constant.studentNameDivider);
				for(String studentName : studentNames) { 
					System.out.println(studentName); 
				}
			} catch (Exception e) {	} 
			System.out.println(Constant.completeMassage);
		}
		else if(args[0].equals(Constant.randomName)) {		// to print a Student name randomly
			System.out.println(Constant.loadingMassage);			
			try {
				String studentNames[] = readData().split(Constant.studentNameDivider);
				System.out.println(studentNames[new Random().nextInt(studentNames.length)]);
			} catch (Exception e) { } 
			System.out.println(Constant.completeMassage);			
		}
		// to add a name to the student list
		else if(args[0].contains(Constant.add)) {
			System.out.println(Constant.loadingMassage);			
			try {
				// writing data to file method on 76th line
				writeData(args[0]);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(Constant.completeMassage);	
		}
		else if(args[0].contains(Constant.check)) {		// to check if name in given argument exists
			System.out.println(Constant.loadingMassage);			
			try {
				String studentNames[] = readData().split(Constant.studentNameDivider);
				for(int index = 0; index < studentNames.length ; index++) {
					if(studentNames[index].equals(args[0].substring(1))) {
						System.out.println(Constant.founded + index);
						break;		//break if found the name
					}
					if(index == (studentNames.length-1)){
						System.out.println(Constant.notFound);
					}
				}
			} catch (Exception e) { } 
			System.out.println(Constant.completeMassage);			
		}
		else if(args[0].contains(Constant.counter)) {		//count the number of students name
			System.out.println(Constant.loadingMassage);			
			try {
				//split Student Names by ','
				String studentNames[] = readData().split(Constant.studentNameDivider);
				//length of the array is the number of student in the list
				System.out.println(studentNames.length + Constant.foundMassage);
			} catch (Exception e){ } 
			System.out.println(Constant.completeMassage);				
		}
		else{	// to handle if wrong argument passed
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