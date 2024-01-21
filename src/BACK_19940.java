package src;

import java.io.*;
import java.util.Arrays;

public class BACK_19940 {
    static int minDepth;
    static int[] answer;
    static int N;
    public static void bfs(int[] cnt, int depth, int now) {
        if (depth > minDepth) return;
        if (now == N && minDepth > depth) {
            answer = cnt.clone();
            minDepth = depth;
            return;
        }
        int diff = N - now;
        if (diff >= 60) {
            int divide = diff / 60;
            int add = divide * 60;
            cnt[0] += divide;
            bfs(cnt, depth + divide, now + add);
            cnt[0] -= divide;
            cnt[0] += divide + 1;
            bfs(cnt, depth + divide + 1, now + add + 60);
            cnt[0] -= divide + 1;
        }
        if (diff >= 10) {
            int divide = diff / 10;
            int add = divide * 10;
            cnt[1] += divide;
            bfs(cnt, depth + divide, now + add);
            cnt[1] -= divide;
            cnt[1] += divide + 1;
            bfs(cnt, depth + divide + 1, now + add + 10);
            cnt[1] -= divide + 1;
        }
        if (diff <= -10) {
            int divide = diff / -10;
            int add = divide * -10;
            cnt[2] += divide;
            bfs(cnt, depth + divide, Math.max(now + add, 0));
            cnt[2] -= divide;
            cnt[2] += divide + 1;
            bfs(cnt, depth + divide + 1, Math.max(now + add - 10, 0));
            cnt[2] -= divide + 1;
        }
        if (diff > 0) {
            int divide = diff;
            int add = divide;
            cnt[3] += divide;
            bfs(cnt, depth + divide, now + add);
            cnt[3] -= divide;
            cnt[3] += divide + 1;
            bfs(cnt, depth + divide + 1, now + add + 1);
            cnt[3] -= divide + 1;
        }
        if (diff < 0) {
            int divide = diff / -1;
            int add = divide * -1;
            cnt[4] += divide;
            bfs(cnt, depth + divide,  Math.max(now + add, 0));
            cnt[4] -= divide;
            cnt[4] += divide + 1;
            bfs(cnt, depth + divide + 1,  Math.max(now + add - 1, 0));
            cnt[4] -= divide + 1;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < T; i++) {
            minDepth = Integer.MAX_VALUE;
            N = Integer.parseInt(br.readLine());
            bfs(new int[5], 0, 0);
            for (int elem : answer) {
                bw.write(elem + " ");
            }
            bw.newLine();
        }
        bw.flush();
    }
}
