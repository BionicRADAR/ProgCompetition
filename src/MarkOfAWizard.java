import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class MarkOfAWizard {
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String line = "";
			try {
				line = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			int numLines = Integer.parseInt(line);
			if (numLines == 0)
				break;
			
			Map<String, Node> nodes = new HashMap<String, Node>();
			for (int i = 0; i < numLines; i++) {
				try {
					line = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				Scanner s = new Scanner(line);
				String name = s.next();
				int numTunnels = s.nextInt();
				Node n = new Node(name, numTunnels);
				nodes.put(name, n);
				for (int j = 0; j < numTunnels; j++) {
					n.upNodes[j] = s.next();
					n.upTimes[j] = s.nextInt();
				}
				s.close();
			}
			Node current = nodes.get("A");
			current.dist = 0;
			ArrayList<Node> queue = new ArrayList<Node>();
			while(current.upNodes.length > 0) {
				for (int i = 0; i < current.upNodes.length; i++){
					Node n = nodes.get(current.upNodes[i]);
					if (!n.inQueue) {
						queue.add(n);
						n.inQueue = true;
					}
					if (n.dist >= current.dist + current.upTimes[i]); {
						n.dist = current.dist + current.upTimes[i];
						n.pred.add(current.name);
					}
					current = queue.remove(0);
				}
			}
		}
	}
	
	static class Node {
		String[] upNodes;
		int[] upTimes;
		String name;
		int dist;
		ArrayList<String> pred;
		boolean inQueue;
		Node(String name, int numTunnels) {
			this.name = name;
			upNodes = new String[numTunnels];
			upTimes = new int[numTunnels];
			dist = Integer.MAX_VALUE;
			pred = new ArrayList<String>();
			inQueue = false;
		}
	}
}
