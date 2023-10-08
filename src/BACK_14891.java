package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BACK_14891 {
    static int NUM = 10000000;
    public static String spin(String str) { // 시계
        int num = Integer.parseInt(str);
        int last = num % 10;
        num /= 10;
        num += last * NUM;
        return String.format("%08d", num);
    }
    public static String spinRev(String str) { // 반시계
        int num = Integer.parseInt(str);
        int first = num / NUM;
        num %= NUM;
        num *= 10;
        num += first;
        return String.format("%08d", num);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] gears = new String[4];
        for (int i = 0; i < 4; i++) {
            gears[i] = br.readLine();
        }
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[] commands = new int[4];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int target = Integer.parseInt(st.nextToken()) - 1;
            int spin = Integer.parseInt(st.nextToken());
            Arrays.fill(commands, 0);
            commands[target] = spin;
            for (int j = target + 1; j < 4; j++) { // 오른쪽
                char comp = gears[j - 1].charAt(2);
                char me = gears[j].charAt(6);
                if (me == comp) break;
                else commands[j] = commands[j - 1] * -1;
            }
            for (int j = target - 1; j >= 0; j--) { // 왼쪽
                char me = gears[j].charAt(2);
                char comp = gears[j + 1].charAt(6);
                if (me == comp) break;
                else commands[j] = commands[j + 1] * -1;
            }
            for (int j = 0; j < 4; j++) {
                if (commands[j] != 0) {
                    if (commands[j] == 1) gears[j] = spin(gears[j]);
                    else gears[j] = spinRev(gears[j]);
                }
            }
        }
        int answer = 0;
        for (int i = 0; i < gears.length; i++) if (gears[i].charAt(0) == '1') answer += (int)Math.pow(2, i);
        System.out.println(answer);
    }
}
