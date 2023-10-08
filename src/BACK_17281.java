import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BACK_17281 {
	static int[][] scores;
	static Queue<Integer> players;
	static int answer;
	static int subSum;
	static int[] choosed;
	static int visited;
	static int iIdx;
	
	public static boolean hit(int inning, int idx) {
		int score = scores[inning][idx];
		if (score == 0) return false;
		else {
			int size = players.size();
			for (int i = 0; i < size; i++) {
				int result = score + players.poll();
				if (result >= 4) {
					subSum++;
				} else {
					players.offer(result);
				}
			}
			if (score == 4) {
				subSum++;
			} else {
				players.offer(score);
			}
			return true;
		}
	}
	
	public static void playInning(int inning) {
		int outCount = 0;
		players = new ArrayDeque<>();
		while (outCount < 3) {
			if (!hit(inning, choosed[iIdx])) outCount++;
			iIdx = (iIdx + 1) % scores[0].length;
		}
	}
	static int cnt = 0;
	public static void perm(int depth) {
		if (depth == scores[0].length) {
			subSum = 0;
			iIdx = 0;
			for (int i = 0; i < scores.length; i++) { // 시작타순 정한 후 게임 진행
				playInning(i);
			}
			if (subSum > answer) answer = subSum;
			return ;
		}
		for (int i = 1; i < scores[0].length; i++) {
			if ((visited & (1 << i)) == 0) {
				visited |= (1 << i);
				choosed[depth] = i;
				if (depth == 2) perm(depth + 2);
				else perm(depth + 1);
				visited ^= (1 << i);
			}				
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		scores = new int[N][9];
		StringTokenizer st;
		players = new ArrayDeque<>();
		answer = 0;
		for (int i = 0; i < scores.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < scores[0].length; j++) {
				scores[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		choosed = new int[scores[0].length];
		choosed[3] = 0; // 첫 타자를 3번으로 만듦(4번 타자)
		visited = 1;
		// 0번부터, 하나씩 다 골라보기.
		perm(0);
		System.out.println(answer);
	}
}
