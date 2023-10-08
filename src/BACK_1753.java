import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BACK_1753 {
	static int INF = 10000000;
	static int[] d;
	static boolean[] visited;
	static ArrayList<ArrayList<Edge>> edges;
	
	public static int getMinIdx() {
		int min = INF;
		int idx = 0;
		for (int i = 1; i < d.length; i++) {
			if (d[i] < min && !visited[i] && d[i] != 0) {
				min = d[i];
				idx = i;
			}
		}
		return idx;
	}
	
	public static void dik(int start) {
		d[start] = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o1[1], o2[1]);
			}
		});
		pq.offer(new int[] {start, 0});
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int now = cur[0];
			int dist = cur[1];
			visited[now] = true;
			for (int i = 1; i < edges.get(now).size(); i++) {
				if (!visited[i] && d[i] > edges.get(now).get(i).weight + dist) {
					d[i] = edges.get(now).get(i).weight + dist;
					pq.offer(new int[] {i, d[i]});
				}
			}
		}
	}

	static class Edge {
		int target;
		int weight;
		Edge(int target, int weight) {
			this.target = target;
			this.weight = weight;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine());
		d = new int[V + 1];
		edges = new ArrayList<>();
		for (int i = 0; i < V + 1; i++) {
			edges.add(new ArrayList<>());
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges.get(u).add(new Edge(v, w));
		}
		visited = new boolean[V + 1];
		for (int i = 1; i < d.length; i++) d[i] = INF;
		dik(start);
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < d.length; i++) {
			if (d[i] == INF) sb.append("INF\n");
			else sb.append(d[i] + "\n");
		}
		System.out.println(sb);
	}
}
