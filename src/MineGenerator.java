import java.util.ArrayList;
import java.util.Random;


public class MineGenerator {
	public static void main(String[] args) {
		Random r = new Random(238947);
		int numIn = 4;
		int numOut = 4;
		int range = 5;
		int maxDanger = 50;
		int numVertices = 20;
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for (int i = 0; i < numVertices; i++) {
			vertices.add(new Vertex((char) ('A' + i), r.nextInt(maxDanger), 'N'));
			if (i < numIn)
				vertices.get(i).inOut = 'I';
			else if (i >= numVertices - numOut)
				vertices.get(i).inOut = 'O';
		}
		for (int i = 0; i < vertices.size(); i++) {
			Vertex v = vertices.get(i);
			if (v.inOut == 'O')
				continue;
			if (v.edges.isEmpty() && v.inOut != 'I') {
				int randBack = range; 
				if (i > range)
					randBack = i;
				Edge back = new Edge(vertices.get(i - r.nextInt(randBack - 1) - 1), v, r.nextInt(maxDanger));
				v.edges.add(back);
				back.other(v).edges.add(back);
			}
		}
	}
	
	static class Vertex {
		int danger;
		ArrayList<Edge> edges;
		char inOut, name;
		Vertex(char name, int danger, char inOut) {
			this.danger = danger;
			this.inOut = inOut;
			this.name = name;
		}
		
		public String toString() {
			return name + " " + inOut + " " + edges.size() + " " + danger;
		}
	}
	
	static class Edge {
		int danger;
		Vertex v1, v2;
		Edge(Vertex v1, Vertex v2, int danger) {
			this.v1 = v1;
			this.v2 = v2;
			this.danger = danger;
		}
		Vertex other(Vertex v) {
			if (v == v1)
				return v2;
			return v1;
		}
		
		public String toString() {
			return v1.name + " " + v2.name + " " + danger;
		}
	}
}
