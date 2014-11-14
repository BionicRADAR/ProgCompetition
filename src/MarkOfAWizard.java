import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class MarkOfAWizard {
	static Map<String, Node> nodes;
	public static void main(String[] args) {
		Scanner st = new Scanner(System.in);
		String output = "";
		while (true) {
			Node last = null;
			String line = "";
			line = st.nextLine();
			int numLines = Integer.parseInt(line);
			if (numLines == 0)
				break;
			
			nodes = new HashMap<String, Node>();
			for (int i = 0; i < numLines; i++) {
				line = st.nextLine();	
				Scanner s = new Scanner(line);
				String name = s.next();
				//System.out.print(name + " ");
				int numTunnels = s.nextInt();
				//System.out.print(numTunnels + " ");
				Node n = new Node(name, numTunnels);
				nodes.put(name, n);
				for (int j = 0; j < numTunnels; j++) {
					n.upNodes[j] = s.next();
					n.upTimes[j] = s.nextInt();
					//System.out.print(n.upNodes[j] + " " + n.upTimes[j] + " ");
				}
				//System.out.println();
				s.close();
				if (i == numLines - 1) {
					last = n;
				}
			}
			/*for (String s : nodes.keySet()) {//filling downNodes
				String[] up = nodes.get(s).upNodes;
				for (int i = 0; i < up.length; i++) {
					nodes.get(up[i]).downNodes.add(s);
				}
			}*/
			
			Node current = nodes.get("A");
			current.dist = 0;
			ArrayList<Node> queue = new ArrayList<Node>();
			while(current.upNodes.length > 0) { //fill in dists and pred
				for (int i = 0; i < current.upNodes.length; i++){
					Node n = nodes.get(current.upNodes[i]);
					//System.out.println(current.name + ": " + n.name);
					if (!n.inQueue) {
						queue.add(n);
						n.inQueue = true;
					}
					if (n.dist > current.dist + current.upTimes[i]) {
						n.dist = current.dist + current.upTimes[i];
						n.pred.clear();
						n.pred.add(current.name);
					} 
					else if (n.dist == current.dist + current.upTimes[i]) {
						n.pred.add(current.name);
					}
				}
				current = queue.remove(0);
			}
			queue = new ArrayList<Node>();
			for (String s : nodes.keySet()) {
				nodes.get(s).inQueue = false;
			}
			queue.add(last);
			last.inQueue = true;
			while (!queue.isEmpty()) { //fill succ
				current = queue.remove(0);
				for (String s : current.pred){
					if (!nodes.get(s).inQueue) {
						queue.add(nodes.get(s));
						nodes.get(s).inQueue = true;
					}
					nodes.get(s).succ.add(current.name);
				}
			}
			for (String s : nodes.keySet()) {
				nodes.get(s).inQueue = false;
			}
			last.numToClean = 0;
			last.cleanFound = true;
			output += last.dist + " " + nodes.get("A").findClean() + "\n";
			for (String s : nodes.keySet()) {
				//System.out.println(s + ": " + nodes.get(s).findClean());
				//System.out.println(nodes.get(s).succ);
				//System.out.println(nodes.get(s).pred);
			}
		}
		System.out.println(output);
		st.close();
	}
	
	static class Node {
		String[] upNodes;
		int[] upTimes;
		String name;
		int dist;
		int numToClean;
		ArrayList<String> pred;
		ArrayList<String> succ;
		//ArrayList<String> downNodes;
		boolean inQueue;
		boolean cleanFound;
		boolean cleanFlag;
		Node(String name, int numTunnels) {
			this.name = name;
			upNodes = new String[numTunnels];
			upTimes = new int[numTunnels];
			//downNodes = new ArrayList<String>();
			succ = new ArrayList<String>();
			dist = Integer.MAX_VALUE;
			pred = new ArrayList<String>();
			inQueue = false;
			numToClean = Integer.MAX_VALUE;
			cleanFound = false;
			cleanFlag = false;
		}
		int findClean() {
			if (cleanFound)
				return numToClean;
			boolean hasDiff = false;
			for (int i = 0; i < upNodes.length; i++) {
				if (!succ.contains(upNodes[i])) {
					hasDiff = true;
					continue;
				}
				int next = nodes.get(upNodes[i]).findClean();
				if (next != numToClean && cleanFlag)
					hasDiff = true;
				if (next < numToClean)
					numToClean = next;
				cleanFlag = true;
			}
			if (hasDiff)
				numToClean++;
			cleanFound = true;
			return numToClean;
		}
	}
}
