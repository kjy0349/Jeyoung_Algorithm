import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BACK_1992 {
	static char[][] map;
	static StringBuilder sb;
	public static boolean chkBox(int x, int y, int size) {
		char start = map[x][y];
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (start != map[i][j]) return false;
			}
		}
		return true;
	}
	
	public static void findTree(int x, int y, int size) {
		int half = size / 2;
		
		if (chkBox(x, y, half)) {
			sb.append(Character.toString(map[x][y]));
		} else {
			sb.append("(");
			findTree(x, y, half);
			sb.append(")");
		}
		if (chkBox(x, y + half, half)) {
			sb.append(Character.toString(map[x][y + half]));
		} else {
			sb.append("(");
			findTree(x, y + half, half);
			sb.append(")");
		}
		if (chkBox(x + half, y, half)) {
			sb.append(Character.toString(map[x + half][y]));
		} else {
			sb.append("(");
			findTree(x + half, y, half);
			sb.append(")");
		}
		if (chkBox(x + half, y + half, half)) {
			sb.append(Character.toString(map[x + half][y + half]));
		} else {
			sb.append("(");
			findTree(x + half, y + half, half);
			sb.append(")");
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) map[i][j] = (char) (line.charAt(j));
		}

		if (chkBox(0, 0, N)) {
			sb.append(Character.toString(map[0][0]));
		} else {
			sb.append("(");
			findTree(0, 0, N);
			sb.append(")");
		}
		System.out.println(sb);
	}
}
