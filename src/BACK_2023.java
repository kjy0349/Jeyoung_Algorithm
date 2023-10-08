import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

public class BACK_2023 {
	static Set<Integer> primes;
	public static boolean isPrime(int n) {
		if (n >= 2) {
			for (int i = 2; i <= Math.sqrt(n); i++) {
				if (n % i == 0) return false;
			}			
			return true;
		} else return false;
	}
	
	public static boolean isWow(int n) {
		for (int i = 0; i < 8 && n > 0; i++) {
			
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		primes.add(2);
		primes.add(3);
		primes.add(5);
		primes.add(7);
		StringBuilder sb = new StringBuilder();
		for (int i = (int)Math.pow(10, N - 1); i < (int)Math.pow(10,  N); i++) {
			if (isWow(i)) {
				sb.append(Integer.toString(i));
				sb.append("\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
	}
}
