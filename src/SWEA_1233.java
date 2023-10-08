import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SWEA_1233 {
	static int operCnt;
	static int edgeCnt;
	public static void solution(String elem) {
		if (elem.equals("+") || elem.equals("-") || elem.equals("*") || elem.equals("/")) {
			operCnt++;
		} else edgeCnt++;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= 10; i++) {
			operCnt = 0;
			edgeCnt = 0;
			int N = Integer.parseInt(br.readLine());
			for (int j = 0; j < N; j++) {
				String[] input = br.readLine().split(" ");
				solution(input[1]);
			}
			sb.append("#");
			sb.append(i);
			sb.append(" ");
			if (edgeCnt - operCnt == 1) {
				sb.append("1");
			} else sb.append("0");
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
