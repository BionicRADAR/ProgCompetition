import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class AMine {

	static Map<String, Node> nodes = new HashMap<String, Node>();
	static Set<Node> entrances = new HashSet<Node>();
	static Set<Node> exits = new HashSet<Node>();
	static ArrayList<Node> queue = new ArrayList<Node>();
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while (true) {
			int v = s.nextInt();
			int e = s.nextInt();
			s.nextLine();
			if (v == 0)
				break;
			nodes = new HashMap<String, Node>();
			entrances = new HashSet<Node>();
			exits = new HashSet<Node>();
			queue = new ArrayList<Node>();
			for (int i = 0; i < v; i++) {
				Scanner n = new Scanner(s.nextLine());
				char name = n.next().charAt(0);
				char inOut = n.next().charAt(0);
				int danger = n.nextInt();
				int numEdges = n.nextInt();
				nodes.put(name + "", new Node(name, inOut, danger, numEdges));
				n.close();
			}
			for (int i = 0; i < e; i++) {
				Scanner n = new Scanner(s.nextLine());
				Node node1 = nodes.get(n.next());
				Node node2 = nodes.get(n.next());
				int danger = n.nextInt();
				node1.addEdge(new Edge(node2, danger));
				node2.addEdge(new Edge(node1, danger));
				n.close();
			}
			for (Node n : entrances) {
				queue.add(n);
				n.isQueued = true;
				n.totalDan = n.danger;
			}
			Node exit = null;
			for (Node n : exits) {
				exit = n;
				break;
			}
			while (!queue.isEmpty()) {
				Node n = extractMin();
				for (Edge ed : n.edges) {
					Node other = ed.other;
					if (!other.isQueued) {
						queue.add(ed.other);
						other.isQueued = true;
					}
					if (other.isQueued && !queue.contains(other))
						continue;
					int newDan = n.totalDan + ed.danger + other.danger;
					if (newDan < other.totalDan) {
						other.totalDan = newDan;
						other.pred = n;
						if (exits.contains(other) && other.totalDan < exit.totalDan)
							exit = other;
					}
				}
			}
			System.out.print(exit.totalDan + " ");
			charList reverser = new charList();
			for (Node n = exit; n != null; n = n.pred) {
				reverser.add(n.name);
			}
			System.out.println(reverser.print());
		}
		s.close();
	}
	
	static Node extractMin() {
		int min, minPos;
		minPos = 0;
		min = queue.get(0).totalDan;
		for (int i = 1; i < queue.size(); i++) {
			if (queue.get(i).totalDan < min) {
				minPos = i;
				min = queue.get(i).totalDan;
			}
		}
		return queue.remove(minPos);
	}
	
	static class Node {
		char name;
		int danger;
		int numEdges;
		ArrayList<Edge> edges;
		boolean isQueued;
		Node pred;
		int totalDan;
		
		Node(char name, char inOut, int danger, int numEdges) {
			this.name = name;
			if (inOut == 'I')
				entrances.add(this);
			else if (inOut == 'O')
				exits.add(this);
			this.danger = danger;
			this.numEdges = numEdges;
			edges = new ArrayList<Edge>();
			isQueued = false;
			pred = null;
			totalDan = Integer.MAX_VALUE;
		}
		
		void addEdge(Edge e) {
			edges.add(e);
		}
	}
	
	static class Edge {
		Node other;
		int danger;
		Edge(Node other, int danger) {
			this.other = other;
			this.danger = danger;
		}
	}
	
	static class charNode {
		char datum;
		charNode next;
		charNode(char datum, charNode next) {
			this.datum = datum;
			this.next = next;
		}
	}
	
	static class charList {
		charNode head;
		charList() {
			head = null;
		}
		void add(char dat) {
			head = new charNode(dat, head);
		}
		String print() {
			String toReturn = "";
			for (charNode c = head; c != null; c = c.next) {
				toReturn += c.datum + " ";
			}
			return toReturn;
		}
	}
	
}
