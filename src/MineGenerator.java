import java.util.ArrayList;
import java.util.Random;


public class MineGenerator {
	public static void main(String[] args) {
		Random r = new Random(32457);
		int numIn = 3;
		int numOut = 3;
		int range = 6;
		int maxDanger = 50;
		int numVertices = 94;
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for (int i = 0; i < numVertices; i++) {
			vertices.add(new Vertex((char) (33 + i), r.nextInt(maxDanger), 'N'));
			if (vertices.get(i).name == 160)
				vertices.get(i).name = 64;
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
				if (i < range)
					randBack = i;
				Edge back = new Edge(vertices.get(i - r.nextInt(randBack - 1) - 1), v, r.nextInt(maxDanger));
				v.edges.add(back);
				back.other(v).edges.add(back);
				edges.add(back);
			}
			for (int j = i + 1; j < vertices.size() && j < i + range; j++) {
				if (r.nextBoolean()) {
					Edge newEdge = new Edge(v, vertices.get(j), r.nextInt(maxDanger));
					v.edges.add(newEdge);
					vertices.get(j).edges.add(newEdge);
					edges.add(newEdge);
				}
			}
		}
		System.out.println(numVertices + " " + edges.size());
		for (Vertex v : vertices) {
			System.out.println(v);
		}
		for (Edge e : edges) {
			System.out.println(e);
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
			edges = new ArrayList<Edge>();
		}
		
		public String toString() {
			return name + " " + inOut + " " + danger + " " + edges.size();
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
