import java.util.ArrayList;
import java.util.Scanner;

public class CrashAndGorelians {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String answer = "";
		while (true) {
			int numGos = Integer.parseInt(s.nextLine());
			if (numGos == 0)
				break;
			ArrayList<Double> xs = new ArrayList<Double>();
			ArrayList<Double> ys = new ArrayList<Double>();
			ArrayList<Double> rs = new ArrayList<Double>();
			for (int i = 0; i < numGos; i++) {
				String nextGo = s.nextLine();
				Scanner go = new Scanner(nextGo);
				double x = go.nextInt();
				double y = go.nextInt();
				double r = go.nextInt();
				int numJoins = 1;
				double joinX = x;
				double joinY = y;
				double joinR = r;
				boolean changeMade = true;
				while (changeMade) {
					changeMade = false;
					for (int j = 0; j < xs.size(); j++) {
						double dist = (x - xs.get(j)) * (x - xs.get(j)) + (y - ys.get(j)) * (y - ys.get(j));
						if (dist < r * r || dist < rs.get(j) * rs.get(j)) {
							joinX += xs.get(j);
							joinY += ys.get(j);
							joinR = Math.sqrt(joinR * joinR + rs.get(j) * rs.get(j));
							numJoins++;
							changeMade = true;
							rs.remove(j);
							xs.remove(j);
							ys.remove(j);
							j--;
						}
					}
					joinX /= numJoins;
					joinY /= numJoins;
					x = joinX;
					y = joinY;
					r = joinR;
					numJoins = 1;
				}
				xs.add(x);
				ys.add(y);
				rs.add(r);
				go.close();
			}
			answer += xs.size() + "\n";
		}
		System.out.print(answer);
		s.close();
	}
}
