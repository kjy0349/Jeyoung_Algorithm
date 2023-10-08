package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class BACK_1863 {
	static Deque<Integer> stack;
	static int[] map;
	static int[][] input;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		stack = new ArrayDeque<>();
		int N = Integer.parseInt(br.readLine());
		input = new int[N][2];
		int answer = 0;
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			input[i][0] = Integer.parseInt(st.nextToken());
			input[i][1] = Integer.parseInt(st.nextToken());
		}
		map = new int[input[N - 1][0] + 1];
		for (int i = 0; i < N - 1; i++) {
			int target = input[i + 1][0];
			for (int j = input[i][0]; j < target; j++) {
				map[j] = input[i][1]; 
			}
		}
		map[input[N - 1][0]] = input[N - 1][1];
//		System.out.println(Arrays.toString(map));
		for (int i = 1; i < map.length; i++) {
			if (map[i] == 0) stack.clear();
			else {
				if (stack.isEmpty()) { // 비어있으면, 넣고 answer++
					stack.push(map[i]);
					answer++;
				} else {
					int target = stack.peek(); // 기존 스택 값이랑 비교
					if (target < map[i]) { // 기존 스택에 있는 빌딩 높이보다 큰 빌딩 들어오면, ++
						answer++;
						stack.push(map[i]);
					} else if (target > map[i]) {
						while (!stack.isEmpty()) {
							target = stack.peek();
							if (target > map[i]) stack.pop();
							else break;
						}
						if (stack.isEmpty()) { // 기존 스택에 나보다 큰 건물들만 있었을 경우.
							stack.push(map[i]);
							answer++;
						} else {
							target = stack.peek(); // 나보다 작거나 같은 건물이 있었을 경우
							if (target < map[i]) { // 작은 건물이 있을 때만 새롭게 더해줌.
								stack.push(map[i]);
								answer++;
							}
						}
					}
				}
//				System.out.print(stack);
//				System.out.println(" " + answer);
			}
		}
		System.out.println(answer);
	}
}
