import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AMine {

	static Map<String, Node> nodes = new HashMap<String, Node>();
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while (true) {
			int v = s.nextInt();
			int e = s.nextInt();
			if (v == 0)
				break;
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
		}
		s.close();
	}
	
	static class Node {
		char name;
		boolean isEntrance = false;
		boolean isExit = false;
		int danger;
		int numEdges;
		ArrayList<Edge> edges;
		
		Node(char name, char inOut, int danger, int numEdges) {
			this.name = name;
			if (inOut == 'I')
				isEntrance = true;
			else if (inOut == 'O')
				isExit = true;
			this.danger = danger;
			this.numEdges = numEdges;
			edges = new ArrayList<Edge>();
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
	
}
