import java.util.Scanner;

public class JiggleYourSquiggle {
	public static void main(String[] args) {
		Scanner s = new Scanner((System.in));
		while(true) {
			System.out.print("Enter squiggle: ");
			String squiggle = s.nextLine();
			if (squiggle.equals("QUIT"))
				return;
			char[] sq = squiggle.toCharArray();
			for(int i = 0; i < sq.length; i++) {
				char c = sq[i];
				if (isVowel(c)) {
					for (int j = i; j >= 0; j--) {
						c = sq[j];
						c = (char) (c + j + 1);
						while (c > 90)
							c -= 26;
						sq[j] = c;
					}
					i = -1;
				}
			}
			System.out.println(String.valueOf(sq));
		}
	}

	private static boolean isVowel(char c) {
		return (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U');
	}
}
