package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BACK_2812 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Deque<Integer> dq = new ArrayDeque<>();
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String num = br.readLine();
        for (int i = 0; i < num.length(); i++) {
            int elem = num.charAt(i) - '0';
            if (dq.isEmpty()) {
                dq.push(elem);
            } else {
                int compare = dq.peek();
                if (compare >= elem) {
                    dq.push(elem);
                }
                else {
                    while (!dq.isEmpty() && K > 0) {
                        if (dq.peek() < elem) {
                            dq.pop();
                            K--;
                        } else break;
                    }
                    dq.push(elem);
                }
            }
        }
        while (K > 0) {
            dq.pop();
            K--;
        }
        StringBuilder answer = new StringBuilder();
        while (!dq.isEmpty()) {
            answer.append(dq.peekLast());
            dq.pollLast();
        }
        System.out.println(answer);
    }
}
