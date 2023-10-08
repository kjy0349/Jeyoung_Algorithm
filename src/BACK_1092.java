package src;

import java.util.*;
import java.io.*;

class BACK_1092 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Integer[] cr = new Integer[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i< cr.length;i++){
            cr[i] = Integer.parseInt(st.nextToken());
        }
        int M = Integer.parseInt(br.readLine());
        Integer[] box = new Integer[M];
        boolean[] visited = new boolean[M];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<box.length;i++){
            box[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(box, Collections.reverseOrder());
        Arrays.sort(cr, Collections.reverseOrder());
        int time = 1;
        int boxCnt = 0;
        while (true) {
            int tmp = boxCnt;
            int idx = 0;
            for (int j = 0; j < box.length; j++) {
                if (idx >= cr.length) break;
                if (!visited[j] && box[j] <= cr[idx]) {
                    visited[j] = true;
                    idx++;
                    boxCnt++;
                }
            }
            if (boxCnt == box.length) {
                break;
            }
            if (tmp == boxCnt) {
                time = -1;
                break;
            }
            time++;
        }
        System.out.println(time);
    }
}