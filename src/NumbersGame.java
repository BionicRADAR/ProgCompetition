import java.util.ArrayList;


public class NumbersGame {

	public static void main(String[] args) {
		ArrayList<Integer> squares = new ArrayList<Integer>();
		for (int i = 10; i * i < 1000; i++) {
			squares.add(i * i);
		}
		for (int a = 100; a < 1000; a++) {
			for (Integer sq : squares) {
				int b = a + sq;
				if (b > 999)
					break;
				int testNo = b * b - a * a - a * b;
				if (testNo == -1 || testNo == 1) {
					System.out.println("The two three-digit integers are:  " + a + " " + b);
					return;
				}
			}
		}
	}
	
}
