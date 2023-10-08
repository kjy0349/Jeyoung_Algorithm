import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BACK_1931 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] chks = new int[N][2];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			chks[i][0] = Integer.parseInt(st.nextToken());
			chks[i][1] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(chks, (o1, o2) -> Integer.compare(o1[1], o2[1]));
	}
}
