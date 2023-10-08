package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BACK_19539 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] trees = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < trees.length; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
        }
    }
}
