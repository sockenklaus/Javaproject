import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
public class MainMethod{
	public static void main(String[] args) {
		ArrayList<Integer> scores = new ArrayList<Integer>();
		//ArrayList<String> students = new ArrayList<String>();

		Scanner in = new Scanner(System.in);
		System.out.print("Please enter the FULL path of the file ");
		String fileName = in.nextLine();

		try {
			File data = new File(fileName);
			Scanner dat = new Scanner(data);

			if(dat.hasNextLine()){
				/**
				 * The first line of your input file contains (i assume) the number of students.
				 * You ignore this information. Why not use it?
				 */
				String line = dat.nextLine();
				/**
				 * Only parse the file if the first line contains an Integer, which should mean the number of students (i assume!)
				 * Of the first line contains anything else, the file has the wrong format so skip all the rest...
				 */
				if(isInteger(line)) {
					int numberOfStudents = Integer.parseInt(line);

					Student[] students = new Student[numberOfStudents];

					while(dat.hasNextLine()){
						line = dat.nextLine();
						if(isInteger(line)) {
							scores.add(Integer.parseInt(line));
						}
						else if(line.length() >= 2) {
							students.add(line);
						}
					}
				}
			}
			dat.close();
			in.close();
		}
		
		catch(FileNotFoundException e) {
			System.out.print("The file could not be found please enter a new path");
			
		}
		format(roster(scores, students));
	}
	
	/**
	 * Use comprehensible function names.
	 * A function name that returns a boolean should ask a question.
	 * 
	 * i.e.: Is this variable an Integer? Yes / No
	 * 
	 * @param var
	 * @return
	 */
	private static boolean isInteger(String var) {
		try {
			Integer.parseInt(var);
		}
		catch(NumberFormatException e) {
			/**
			 * You should return false in your catch-clause because that's the point where the error is handled
			 */
			return false;
		}
		catch(NullPointerException e) {
			/**
			 * Catch NullPointerException because this is thrown by parseInt() is var is null
			 */
			return false;
		}
		/**
		 * return true if no error was catched.
		 */
		return true;
		
	}

	public static Student[] roster(ArrayList<Integer> scores, ArrayList<String> names){
		Student[] roster = new Student[names.size()];
		int s = 0;
		for(int x = 0; x < names.size(); x++) {
			roster[x] = new Student(names.get(x), scores.get(s),scores.get((s)+1),scores.get((s)+2));
			s +=3;
		}
		return roster;
	}
		
		public static void format(Student[] print) {
			System.out.println("Name" + "\t" + "\t"+ "\t" + " \t Score 1" + "\t" + " \t Score 2" + "\t"+ " \t Score 3 \t \t" + "Total" );
			int highest = 0;
			int lowest = (print[0].ScoreOne + print[0].ScoreTwo + print[0].ScoreThree);
			String lowestName = ""; 
			String Highestname = "";
			int average = 0;
			for(int i = 0; i < 110;i++) {
				System.out.print("_");
			}
			System.out.println( " ");
			for(int x = 0; x < print.length;x++ ) {
				int total = (print[x].ScoreOne + print[x].ScoreTwo + print[x].ScoreThree);
				average = average + total;
				if(total > highest) {
					highest = total;
					Highestname = print[x].name;
				}
				if(total < lowest) {
					lowest = total;
					lowestName = print[x].name;
				}
				System.out.println(print[x].name + "\t  " + "  \t |"+ "\t  " + " "+ print[x].ScoreOne + "\t" + " \t" + " | \t  " + print[x].ScoreTwo + "\t" + "\t| \t  " + print[x].ScoreThree + "\t     | " +"  \t" + total);
			}
			average = (average/print.length);
			for(int i = 0; i < 110;i++) {
				System.out.print("_");
			}
			System.out.println(" ");
			System.out.println("There are " + print.length + " students in the class");
			System.out.println("The average total score is: " + average);
			System.out.println("The highest score set by " + Highestname + " is " + highest);
			System.out.println("The lowest score set by " + lowestName + " is " + lowest);
			for(int i = 0; i < 110;i++) {
				System.out.print("_");
			}
		}
	}