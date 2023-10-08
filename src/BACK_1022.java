import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BACK_1022 {
	public static void find_sol(int x, int y, int value) {
		
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] r1 = {inputs[0], inputs[1]};
		int[] r2 = {inputs[2], inputs[3]};
		int N = Math.max(Math.max(r1[0], r2[0]), Math.max(r1[1], r2[1]));
		int[][] paper = new int[N][N];
		find_sol(0, 0, 1);
	}
}
