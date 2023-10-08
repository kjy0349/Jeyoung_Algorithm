package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BACK_4153 {
    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(br.readLine());
            int max = -1;
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            max = Math.max(a, b);
            max = Math.max(max, c);
            if (a == 0 && b == 0 && c == 0) break;
            if (max == a) {
                if ((int)Math.pow(a, 2) == (int)Math.pow(b, 2) + (int)Math.pow(c, 2)) sb.append("right\n");
                else sb.append("wrong\n");
            } else if (max == b) {
                if ((int)Math.pow(b, 2) == (int)Math.pow(a, 2) + (int)Math.pow(c, 2)) sb.append("right\n");
                else sb.append("wrong\n");
            } else {
                if ((int)Math.pow(c, 2) == (int)Math.pow(a, 2) + (int)Math.pow(b, 2)) sb.append("right\n");
                else sb.append("wrong\n");
            }
        }
        System.out.print(sb);
    }
}
