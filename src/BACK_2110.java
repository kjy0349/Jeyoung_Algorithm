package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BACK_2110 {
    static int[] vil;
    static int C;
    static int cnt;
    static int answer;
    static boolean[] visited;
    public static void solution(int start, int end, int idx) {
        if (cnt == C) return;
        answer = Math.min(vil[idx] - vil[start], vil[end] - vil[idx]);
        int left, right, lefDis, rigDis;
        left = right = lefDis = rigDis = 0;
        if (idx + 1 < vil.length) {
            right = Arrays.binarySearch(vil, idx + 1, end, (vil[idx + 1] + vil[end]) / 2);
            if (right < 0) { // 못 찾았음
                right = (right + 1) * -1;
                right--;
            } else right--;
            rigDis = Math.min(vil[end] - vil[right], vil[right] - vil[idx + 1]);
        }
        if (idx - 1 > 0) {
            left = Arrays.binarySearch(vil, start, idx - 1, (vil[start] + vil[idx - 1]) / 2);
            if (left < 0) { // 못 찾았음
                left = (left + 1) * -1;
                left--;
            } else left--;
            lefDis = Math.min(vil[idx - 1] - vil[left], vil[left] - vil[start]);
        }
        cnt++;
        if (lefDis < rigDis) {
            solution(start, idx - 1, left);
        } else solution(idx+1, end, right);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        vil = new int[N];
        visited = new boolean[N];
        
        for (int i = 0; i < N; i++) {
            int elem = Integer.parseInt(br.readLine());
            vil[i] = elem;
        }

        Arrays.sort(vil);
        cnt = 0;
        C -= 2; // 양 쪽 끝에 찍기

        int start = 0;
        int end = vil.length - 1;
        int idx = Arrays.binarySearch(vil, (vil[start] + vil[end]) / 2);
        if (idx < 0) { // 못 찾았음
            idx = (idx + 1) * -1;
            idx--;
        } else idx--;
        solution(start, end, idx);
        System.out.println(answer);
    }
}
