package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BACK_13305 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dist = new int[N - 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            dist[i] = Integer.parseInt(st.nextToken());
        }
        int[] vtx = new int[N];
        st = new StringTokenizer(br.readLine());
        int before = Integer.parseInt(st.nextToken());
        vtx[0] = before;
        long answer = (long) vtx[0] * dist[0];
        for (int i = 1; i < N - 1; i++) {
            vtx[i] = Integer.parseInt(st.nextToken());
            if (before > vtx[i]) {
                before = vtx[i];
            }
            answer += (long) before * dist[i];
        }
        System.out.println(answer);
    }
}
