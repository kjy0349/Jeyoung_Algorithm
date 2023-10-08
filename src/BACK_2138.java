/**

@author 김제영
@since 2023. 8. 4.
@performance
@category #
@note 중요한 점은, N 범위가 100,000이므로 N^2일 경우 100억번의 연산을 해야한다.
따라서 가능한 시간복잡도는 O(N) or O(NlogN)이다.
각각의 스위치를 누를지 말지 계산하기 위해서 완전탐색을 생각해보면, 2^100000번의 연산.
완탐도 불가능. 따라서 0번부터 최선의 선택을 한다고 생각하고 풀어본다.
0번 스위치를 누르거나 누르지 않았다는것이 정해지는 순간, 1번 스위치를 눌러야 할지 말아야 할지는
현재 1번 스위치를 눌렀을 때 0번 스위치가 target 배열의 0번 스위치와 같아지느냐가 목적이다.

따라서 0번 스위치를 누른 상태와, 누르지 않은 상태 각각에서 1번 스위치를 눌러야 하는 경우는 
현재 스위치 상태가 target 스위치의 0번 스위치의 상태와 다를 때 뿐이다.*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BACK_2138 {
	static boolean[] switchesZero; //0번 스위치 누름
	static boolean[] switchesNZero; //0번 스위치 안 누름
	static boolean[] target;
	public static void turnSwitch(int x, boolean[] switches) {
		if (x == 0) {
			switches[x] = !switches[x];
			if (x + 1 < switches.length) switches[x + 1] = !switches[x + 1];
		} else if (x == switches.length - 1) {
			switches[x] = !switches[x];
			if (x - 1 >= 0) switches[x - 1] = !switches[x - 1];
		} else {
			switches[x] = !switches[x];
			switches[x + 1] = !switches[x + 1];
			switches[x - 1] = !switches[x - 1];
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		switchesNZero = new boolean[N];
		target = new boolean[N];
		String sw = br.readLine();
		String tg = br.readLine();
		for (int i = 0; i < N; i++) {
			switchesNZero[i] = sw.charAt(i) == '0' ? false : true;
			target[i] = tg.charAt(i) == '0' ? false : true;
		}
		switchesZero = switchesNZero.clone(); // Zerom NZero 초기화
		turnSwitch(0, switchesZero);
		int nZero = 0;
		int zero = 1;
		int answer = Integer.MAX_VALUE;
		for (int i = 1; i < switchesZero.length; i++) {
			if (target[i - 1] != switchesZero[i - 1]) {
				turnSwitch(i, switchesZero);
				zero++;
			}
			if (target[i - 1] != switchesNZero[i - 1]) {
				turnSwitch(i, switchesNZero);
				nZero++;
			}
		}
		if (Arrays.equals(switchesNZero, target)) answer = nZero;
		if (Arrays.equals(switchesZero, target)) answer = Math.min(zero, answer);
		if (answer == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(answer);
	}
}
