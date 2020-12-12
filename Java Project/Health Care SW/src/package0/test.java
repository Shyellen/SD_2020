package package0;

import java.util.Scanner;

public class test {
	Scanner Scanner = new Scanner(System.in);
	
	public test() {
		String Username = "Test";
		RecordNumber Record = new RecordNumber(Username);
		int command;
		
		while (true) {
			command = Scanner.nextInt();
			
			if (command == 1) {
				System.out.printf("Username: %s\nDatetime: %04d-%02d-%02d-%02d-%02d-%02d\nDatum: %d\n", 
						Record.username, Record.year, Record.month, Record.date, Record.hour, Record.minute, Record.second, Record.datum);
			}
			else if (command == 0) {
				System.out.printf("Quit Program\n");
				break;
			}
			else {
				Record = new RecordNumber(Username);
			}
		}
	}
	
	public static void main(String[] args) {
		test test = new test();
	}
}
