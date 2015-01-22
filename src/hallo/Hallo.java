package hallo;

import java.util.Scanner;

public class Hallo {
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		System.out.println("What's your name? : ");
		if(scanner.hasNextLine()){
			System.out.println("Hello "+scanner.nextLine());
		}
		scanner.close();
	}
}
