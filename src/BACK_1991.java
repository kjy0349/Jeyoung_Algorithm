import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BACK_1991 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N;
	static int[][] tree;
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		tree = new int[26][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int parent = st.nextToken().charAt(0) - 'A';
			int childL = st.nextToken().charAt(0) - 'A';
			int childR = st.nextToken().charAt(0) - 'A';
			childL = (childL == '.' - 'A') ? -1 : childL;
			childR = (childR == '.' - 'A') ? -1 : childR;
			tree[parent][0] = childL;
			tree[parent][1] = childR;
		}
		solution();
		System.out.println(sb);
	}
	
	static void solution() {
		preOrder(0);
		sb.append('\n');
		inOrder(0);
		sb.append('\n');
		postOrder(0);
	}
	
	static void preOrder(int x) {
		if (x == -1) return;
		sb.append((char)(x + 'A'));
		preOrder(tree[x][0]);
		preOrder(tree[x][1]);
	}
	static void inOrder(int x) {
		if (x == -1) return;
		inOrder(tree[x][0]);
		sb.append((char)(x + 'A'));
		inOrder(tree[x][1]);
	}
	static void postOrder(int x) {
		if (x == -1) return;
		postOrder(tree[x][0]);
		postOrder(tree[x][1]);
		sb.append((char)(x + 'A'));
	}
}

