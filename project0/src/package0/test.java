package package0;

import java.util.Scanner;

public class test {
	Scanner Scanner = new Scanner(System.in);
	
	public test() {		
		RecordNumber Record = new RecordNumber();
		int command;
		
		while (true) {
			command = Scanner.nextInt();
			
			if (command == 1) {
				System.out.printf("%d\n", Record.GetDatum());
			}
			else if (command == 0) {
				System.out.printf("Quit Program\n");
				break;
			}
			else {
				Record = new RecordNumber();
			}
		}
	}
	
	public static void main(String[] args) {
		test test = new test();
	}
}
