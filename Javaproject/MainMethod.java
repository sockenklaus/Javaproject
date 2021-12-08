import java.util.Scanner;
import java.io.*;
public class MainMethod{
	public static void main(String[] args) {

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
					
					/**
					 * I do the parsing and printing in one step.
					 * although neglible in the real world, my method is much faster because 
					 * i only iterate once (one step per line of input file), while you iterate three times (?)
					 * to achive the same...
					 * 
					 * first i define all variables i will use as follows:
					 */
					int numberOfStudents = Integer.parseInt(line);
					int highestScore = 0;
					String highestStudent = "";
					int lowestScore = Integer.MAX_VALUE;
					String lowestStudent = "";
					int totalScore = 0;

					/**
					 * col, sum and currentName are only temporary to save values used in a single line of output.
					 */
					int col = 0;
					int sum = 0;
					String currentName = ""; 

					/**
					 * print your header
					 */
					System.out.println("Name" + "\t \t \t" + "Score 1" + "\t \t" + "Score 2" + "\t \t"+ "Score 3" + "\t \t" + "Total" );

					while(dat.hasNextLine()){
						line = dat.nextLine();

						/**
						 * If the current line contains an integer, print it, add the value to the row-sum
						 * and increase col by one
						 */
						if(isInteger(line)) {
							System.out.print("\t \t" + line);
							sum += Integer.parseInt(line);
							col += 1;
						}
						/**
						 * if there's no integer we assume a string.
						 * Print string and save it in variable
						 * 
						 * Attention: this will fail if the file provided contains line other
						 * than integer or string, for example float.
						 */
						else {
							currentName = line;
							System.out.print(currentName);
						}

						/**
						 * If col reaches 3, which means we arrive at the end of our  output,
						 * check if the current sum is a new high or low and save the corresponding
						 * name.
						 * 
						 * Also print sum, save total (used for average) and reset sum and col.
						 */
						if(col == 3){
							if(sum > highestScore) {
								highestScore = sum;
								highestStudent = currentName;
							}
							if(sum < lowestScore) {
								lowestScore = sum;
								lowestStudent = currentName;
							}

							System.out.print("\t \t"+sum);
							System.out.println();

							totalScore += sum;
							col = 0;
							sum = 0;
						}
					}

					/**
					 * simply print the footer.
					 * i removed your vertical lines because thats two unnecessary loops in my eyes.
					 */
					System.out.println();
					System.out.println("There are " + numberOfStudents + " students in the class");
					System.out.println("The average total score is: " + totalScore / numberOfStudents);
					System.out.println("The highest score set by " + highestStudent + " is " + highestScore);
					System.out.println("The lowest score set by " + lowestStudent+ " is " + lowestScore);
				}
			}
			dat.close();
			in.close();
		}
		
		catch(FileNotFoundException e) {
			System.out.print("The file could not be found please enter a new path");
			
		}
		// format(roster(scores, students));
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

	/**
	 * 
	 * Following stuff is not necessary. You are just iterating over the same data over and over again...
	 * 
	 */


	/* public static Student[] roster(ArrayList<Integer> scores, ArrayList<String> names){
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
		} */
	}