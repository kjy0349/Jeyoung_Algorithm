package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_5658 {
    static Deque<Character> dque;
    static Set<Integer> set;

    public static int getNum(char elem) {
        if (elem <= '9' && elem >= '0') return elem - '0';
        else if (elem >= 'A' && elem <= 'F') return elem - 'A' + 10;
        else return -1;
    }
    public static void findAns(int length) {
        int idx = 0;
        int tmp = 0;
        for (char elem : dque) {
            tmp += (int)Math.pow(16, length - idx - 1) * getNum(elem);
            if ((idx + 1) % length == 0) {
                idx = 0;
                set.add(tmp);
                tmp = 0;
            }
            else idx++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int test = 1; test <= T; test++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            set = new TreeSet<>(Collections.reverseOrder());
            dque = new ArrayDeque<>();
            String line = br.readLine();
            for (int i = 0; i < line.length(); i++) dque.offer(line.charAt(i));
            int length = N / 4;
            findAns(length);
            for (int i = 0; i < length; i++) {
                dque.offerFirst(dque.pollLast());
                findAns(length);
            }
            int idx = 1;
            for (int elem : set) {
                if (idx == K) {
                    sb.append("#").append(test).append(" ").append(elem).append("\n");
                    break;
                }
                idx++;
            }
        }
        System.out.print(sb);
    }
}
