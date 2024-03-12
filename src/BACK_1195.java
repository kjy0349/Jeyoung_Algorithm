package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BACK_1195 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        String b = br.readLine();
        int answer = Integer.MAX_VALUE;
        if (b.length() < a.length()) {
            String temp = a;
            a = b;
            b = temp;
        } // 긴 것을 b로 고정
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            sb.append('0');
        }
        sb.append(b);
        for (int i = 0; i < a.length(); i++) {
            sb.append('0');
        }
        int bStart = a.length();
        int bEnd = a.length() + b.length() - 1;
        outLoop:
        for (int i = 1; i <= bEnd; i++) {
            int bIdx = i;
            for (int j = 0; j < a.length(); j++) {
                char bChar = sb.charAt(bIdx + j);
                char aChar = a.charAt(j);
                if (bChar == '2' && aChar == bChar) {
                    continue outLoop;
                }
            }
            int diff = 0;
            if (bIdx + a.length() >= bStart && bIdx < bStart) { // 왼쪽으로 튀어나감
                diff = a.length() - (bIdx - 1 + a.length() - bStart + 1);
            } else if (bIdx >= bStart && bIdx - 1 + a.length() <= bEnd) { // 안김
                diff = 0;
            } else { // 오른쪽으로 튀어나감
                diff = bIdx - 1 + a.length() - bEnd;
            }
            answer = Math.min(answer, b.length() + diff);
        }
        if (answer == Integer.MAX_VALUE) answer = a.length() + b.length();
        System.out.println(answer);
    }
}
