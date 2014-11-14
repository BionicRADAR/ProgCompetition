import java.util.Scanner;


public class DayOfWeek {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
		while (true) {
			System.out.print("Enter date: ");
			String input = s.nextLine();
			String date = input;
			if (date.equals("00/00/0000"))
				break;
			int month = Integer.parseInt(date.substring(0, date.indexOf('/')));
			date = date.substring(date.indexOf('/') + 1);
			int day = Integer.parseInt(date.substring(0, date.indexOf('/')));
			int year = Integer.parseInt(date.substring(date.indexOf('/') + 1));
			int d = day;
			int m = 0;
			if (month >= 3)
				m = month - 2;
			else
				m = month + 10;
			int y = year % 100;
			if (month < 3)
				y--;
			int c = year / 100;
			int A = (13 * m - 1)/5;
			int B = (y/4);
			int C = (c/4);
			int D = A + B + C + d + y - 2 * c;
			int R = D % 7;
			System.out.println(input + " is a " + daysOfWeek[R]);
		}
		s.close();
	}
	
}
