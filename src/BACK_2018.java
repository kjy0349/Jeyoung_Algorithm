import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BACK_2018 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int sub_sum = 0;
		int i, j;
		i = 0;
		j = 1;
		int count = 0;
		while (i != j) {
			if (sub_sum < N) {
				sub_sum += j;
				j++;
			} else if (sub_sum == N) {
				if (i != 0) count++;
				sub_sum -= i;
				i++;
			} else {
				sub_sum -= i;
				i++;
			}
		}
		System.out.println(count);
	}
}
