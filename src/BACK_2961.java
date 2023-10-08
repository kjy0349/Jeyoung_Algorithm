import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BACK_2961 {
	static int[][] ing;
	static int answer;
	public static void solution(int cnt, int tCnt, int fla1, int fla2) {
		if (cnt == ing.length) {
			if (answer > Math.abs(fla1 - fla2) && tCnt > 0) {
				answer = Math.abs(fla1- fla2);
			}
			return ;
		}
		solution(cnt + 1, tCnt + 1, fla1 == 0 ? ing[cnt][0] : fla1 * ing[cnt][0],
				fla2 == 0 ? ing[cnt][1] : fla2 + ing[cnt][1]);
		solution(cnt + 1, tCnt, fla1, fla2);
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ing = new int[N][2];
		answer = Integer.MAX_VALUE;
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0 ; j < 2; j++) ing[i][j] = Integer.parseInt(st.nextToken());
		}
		solution(0, 0, 0, 0);
		System.out.println(answer);
	}
}
