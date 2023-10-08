import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BACK_20055 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] belt = new int[2 * N];
        boolean[] isRobot = new boolean[2 * N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) belt[i] = Integer.parseInt(st.nextToken());
        int z_count = 0;
        int gen = 0;
        while (z_count < K)
        {
            int last = belt[2 * N - 1];
            System.arraycopy(belt, 0, belt, 1, belt.length - 1);
            belt[0] = last;
            
            System.arraycopy(isRobot, 0, isRobot, 1, isRobot.length - 1);
            isRobot[0] = false;
            if (isRobot[N - 1]) isRobot[N - 1] = false;
            for (int i = N - 2; i >= 0; i--) {
                boolean elem = isRobot[i];
                if (elem && !isRobot[i + 1] && belt[i + 1] > 0) {
                    isRobot[i] = false;
                    isRobot[i + 1] = true;
                    belt[i + 1]--;
                    if (belt[i + 1] == 0) z_count++;
                }
            }
            if (isRobot[N - 1]) isRobot[N - 1] = false;
            if (belt[0] > 0) {
                isRobot[0] = true;
                belt[0]--;
                if (belt[0] == 0) z_count++;
            }
            gen++;
        }
        System.out.println(gen);
    }
}
