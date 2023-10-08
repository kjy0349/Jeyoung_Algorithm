import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BACK_28323 {
	static int answer;
	static int[] A;
	public static void solution(int startIdx) {
		boolean chk = A[startIdx] % 2 == 0 ? false : true;
		int sub_answer = 1;
		for (int i = startIdx + 1; i < A.length; i++) {
			if (chk && A[i] % 2 == 0) {
				sub_answer++;
				chk = false;
			}
			if (!chk && A[i] % 2 == 1) {
				sub_answer++;
				chk = true;
			}
		}
		if (sub_answer > answer) answer = sub_answer;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = new int[N];
		int oddIdx = -1, evenIdx = -1;
		answer = 0;
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			if (oddIdx == -1 && A[i] % 2 == 1) oddIdx = i;
			if (evenIdx == -1 && A[i] % 2 == 0) evenIdx = i;
		}
		if (oddIdx != -1) solution(oddIdx);
		if (evenIdx != -1) solution(evenIdx);
		System.out.println(answer);
	}
}
