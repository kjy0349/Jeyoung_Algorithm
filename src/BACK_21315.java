package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BACK_21315 {
    public static void shuffle(int[] cards, int K) {
        int range = cards.length;
        for (int i = 1; i <= K + 1; i++) {
            int cnt = (int)Math.pow(2, K - i + 1);
            int[] aCards = new int[1000];
            int idx = 0;
            for (int j = range-cnt; j < range; j++) {
                aCards[idx++] = cards[j];
                cards[j] = 0;
            }
            for (int j = 0; j < cards.length; j++) {
                if (cards[j] != 0) {
                    aCards[idx++] = cards[j];
                }
                cards[j] = aCards[j];
            }
            range = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] target = new int[N];
        int[] origin = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < target.length; i++) {
            target[i] = Integer.parseInt(st.nextToken());
            origin[i] = i + 1;
        }
        for (int i = 1; (int)Math.pow(2, i) < N; i++) {
            for (int j = 1; (int)Math.pow(2, j) < N; j++) {
                int[] cards = origin.clone();
                shuffle(cards, i);
                shuffle(cards, j);
                if (Arrays.equals(cards, target)) {
                    System.out.println(i + " " + j);
                    return ;
                }
            }
        }
    }
}
