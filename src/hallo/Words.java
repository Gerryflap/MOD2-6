package hallo;

import java.util.Scanner;

public class Words {
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		int i = 1;		
		Scanner tempScan;
		String nextLine;
		System.out.println("Input words : ");
		Boolean doorgaan = true;
		while(scanner.hasNextLine() && doorgaan){
			nextLine = scanner.nextLine();
			tempScan = new Scanner(nextLine);
			i = 1;
			while(tempScan.hasNext()){
				String next = tempScan.next();
				if (i == 1 && next.equals("end")){
					doorgaan = false;
					break;
				}
				System.out.println("Word "+ i + ": " + next);
				i += 1;
			}			
			tempScan.close();
			if (doorgaan){
				System.out.println("Input words : ");
			}
			
		}
		scanner.close();
	}

}
