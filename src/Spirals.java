import java.util.Scanner;

public class Spirals {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while (true) {
			System.out.print("Enter integer: ");
			String line = s.nextLine();
			int size = Integer.parseInt(line);
			if (size == 0)
				break;
			int currentNum = 1;
			int diagTotal = 1;
			for (int i = 2; i < size; i+=2) {
				for (int j = 0; j < 4; j++) {
					currentNum += i;
					diagTotal += currentNum;
				}
			}
			System.out.println("Diagonal sum = " + diagTotal);
		}
		s.close();
	}
	
}
