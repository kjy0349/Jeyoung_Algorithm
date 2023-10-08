import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BACK_1074 {
	static int cnt;
	static int answer;
	static int R, C;
	public static void solution(int r, int c, int size) {
		if (size == 1) {
			return ;
		}
		int half = size / 2;
		if (r < half && c < half) {
			solution(r, c, half);			
		} else if (r >= half && c < half) {
			cnt += half * half * 2;
			solution(r + half, c, half);			
		} else if (r < half && c >= half) {
			cnt += half * half;
			solution(r, c + half, half);
		} else if (r >= half && c >= half) {
			cnt += half * half * 3;
			solution(r + half, c + half, half);
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		cnt = 0;
		answer = 0;
		System.out.println(answer);
	}
}
