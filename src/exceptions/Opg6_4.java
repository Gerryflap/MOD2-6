package exceptions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Opg6_4 {
	public static void main(String[] args) throws IOException{
	BufferedReader test = new BufferedReader(new FileReader("iets.dat"));
	PrintWriter test2 = new PrintWriter(new FileWriter("iets.dat"));
	test.close();
	test2.close();
	//If you flush a writer, you write the version of the file in the memory to the file, so your changes are saved to the disk.
	//Flush after calling .println();
}}
