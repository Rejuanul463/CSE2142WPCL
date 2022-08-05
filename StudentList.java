import java.io.*;
import java.text.*;
import java.util.*;
public class StudentList {
	public static void main(String[] args) {
//		Check arguments
		if(args[0].equals(Constant.showAll)) {
			System.out.println(Constant.loadingMassage);			
			try {
				String studentNames[] = readData().split(Constant.studentNameDivider);			
				for(String j : studentNames) { 
					System.out.println(j); 
				}
			} catch (Exception e) {	} 
			System.out.println(Constant.completeMassage);
		}
		else if(args[0].equals(Constant.randomName)) {
			System.out.println(Constant.loadingMassage);			
			try {
				String studentNames[] = readData().split(Constant.studentNameDivider);
				System.out.println(studentNames[new Random().nextInt(studentNames.length)]);
			} catch (Exception e) { } 
			System.out.println(Constant.completeMassage);			
		}
		else if(args[0].contains(Constant.add)) {
			System.out.println(Constant.loadingMassage);			
			try {
				writeData(args[0]);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(Constant.completeMassage);	
		}
		else if(args[0].contains(Constant.check)) {
			System.out.println(Constant.loadingMassage);			
			try {
				String studentNames[] = readData().split(Constant.studentNameDivider);
				for(int idx = 0; idx < studentNames.length ; idx++) {
					if(studentNames[idx].equals(args[0].substring(1))) {
						System.out.println(Constant.founded + idx);
						break;
					}
					if(idx == (studentNames.length-1)){
						System.out.println(Constant.notFound);
					}
				}
			} catch (Exception e) { } 
			System.out.println(Constant.completeMassage);			
		}
		else if(args[0].contains(Constant.checker)) {
			System.out.println(Constant.loadingMassage);			
			try {
				char studentNames[] = readData().toCharArray();			
				int count = 1;
				for(char c:studentNames) {
					if(c == ',') {
						count++;
					}
				}
				System.out.println(count + Constant.foundMassage);
			} catch (Exception e){ } 
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
		return bufferReader.readLine();
	}

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