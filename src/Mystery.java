import java.util.Scanner;


public class Mystery {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int numSets = Integer.parseInt(s.nextLine());
		String answer = "";
		for (int i = 0; i < numSets; i++) {
			s.nextLine();
			char[] charSet = s.nextLine().toCharArray();
			int numDirections = Integer.parseInt(s.nextLine());
			int position = 0;
			for (int j = 0; j < numDirections; j++) {
				position += s.nextInt();
				while (position < 0)
					position += charSet.length;
				position = position % charSet.length;
				answer += charSet[position];
			}
			s.nextLine();
			answer += "\n";
		}
		System.out.println(answer);
		s.close();
	}
	
}
