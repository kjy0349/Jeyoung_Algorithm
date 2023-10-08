import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BACK_16935 {
	static int[][] arr;
	static int[][] new_arr;
	static int N, M;
	static StringBuilder sb;
	public static void print() {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length - 1; j++) {
				sb.append(arr[i][j] + " ");
			}
			sb.append(arr[i][arr[0].length - 1]);
			sb.append("\n");
		}
		System.out.println(sb);
	}
	public static void one() {
		new_arr = new int[arr.length][arr[0].length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				new_arr[arr.length - i - 1][j] = arr[i][j];
			}
		}
		arr = new_arr;
	}
	
	public static void two() {
		new_arr = new int[arr.length][arr[0].length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				new_arr[i][arr[0].length - j - 1] = arr[i][j];
			}
		}
		arr = new_arr;
	}
	
	public static void three() {
		new_arr = new int[arr[0].length][arr.length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				new_arr[j][arr.length - 1 - i] = arr[i][j];
			}
		}
		arr = new_arr;
	}
	
	public static void four() {
		new_arr = new int[arr[0].length][arr.length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				new_arr[arr[0].length - 1 - j][i] = arr[i][j];
			}
		}
		arr = new_arr;
	}
	
	public static void five() {
		new_arr = new int[arr.length][arr[0].length];
		for (int i = 0; i < arr.length / 2; i++) {
			for (int j = 0; j < arr[0].length / 2; j++) {
				new_arr[i][j + (arr[0].length / 2)] = arr[i][j];
			}
		}
		for (int i = 0; i < arr.length / 2; i++) {
			for (int j = arr[0].length / 2; j < arr[0].length; j++) {
				new_arr[i + (arr.length / 2)][j] = arr[i][j];
			}
		}
		for (int i = arr.length / 2; i < arr.length; i++) {
			for (int j = arr[0].length / 2; j < arr[0].length; j++) {
				new_arr[i][j - (arr[0].length / 2)] = arr[i][j];
			}
		}
		
		for (int i = arr.length / 2; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length / 2; j++) {
				new_arr[i - (arr.length / 2)][j] = arr[i][j];
			}
		}
		arr = new_arr;
	}
	
	public static void six() {
		new_arr = new int[arr.length][arr[0].length];
		for (int i = 0; i < arr.length / 2; i++) {
			for (int j = 0; j < arr[0].length / 2; j++) {
				new_arr[i + (arr.length / 2)][j] = arr[i][j];
			}
		}
		for (int i = 0; i < arr.length / 2; i++) {
			for (int j = arr[0].length / 2; j < arr[0].length; j++) {
				new_arr[i][j - (arr[0].length / 2)] = arr[i][j];
			}
		}
		for (int i = arr.length / 2; i < arr.length; i++) {
			for (int j = arr[0].length / 2; j < arr[0].length; j++) {
				new_arr[i - (arr.length / 2)][j] = arr[i][j];
			}
		}
		
		for (int i = arr.length / 2; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length / 2; j++) {
				new_arr[i][j + (arr[0].length / 2)] = arr[i][j];
			}
		}
		arr = new_arr;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < arr.length ; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < arr[0].length ; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		sb = new StringBuilder();
		int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		for (int num : nums) {
			if (num == 1) one();
			else if (num == 2) two();
			else if (num == 3) three();
			else if (num == 4) four();
			else if (num == 5) five();
			else if (num == 6) six();
		}
		print();
	}
}
