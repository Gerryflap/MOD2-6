
public class Printer {
	public static void main(String[] args){
		System.out.printf("Arguments: %n");
		for (int i = 0; i < args.length; i++){
			System.out.printf("%2d %s%n", i, args[i]);
		}
	}
}
