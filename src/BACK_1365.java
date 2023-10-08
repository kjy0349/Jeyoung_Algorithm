package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BACK_1365 {
    static int last;
    static int[] list;
    public static void setIdx(int value) {
        if (value > list[last]) list[++last] = value;
        else {
            int left = 0;
            int right = last;
            int res = -1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (list[mid] < value) {
                    left = mid + 1;
                } else {
                    res = mid;
                    right = mid - 1;
                }
            }
            list[res] = value;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] lines = new int[N];
        for (int i = 0; i < lines.length; i++) lines[i] = Integer.parseInt(st.nextToken());
        list = new int[N];
        Arrays.fill(list, Integer.MAX_VALUE);
        list[0] = lines[0];
        last = 0;
        for (int i = 1; i < lines.length; i++) {
            setIdx(lines[i]);
        }
        System.out.println(N - (last + 1));
    }
}
