import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BACK_16927 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int[][] array = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) array[i][j] = Integer.parseInt(st.nextToken());
        }
        int gr_cnt = Math.min(N, M) / 2;
        ArrayList<ArrayList<Integer>> groups = new ArrayList<>();
        for (int i = 0; i < gr_cnt; i++) {
        	ArrayList<Integer> arr = new ArrayList<>();
        	for (int j = i; j <= M - 2 - i; j++) arr.add(array[i][j]);
        	for (int j = i; j <= N - 2 - i; j++) arr.add(array[j][M - 1 - i]);
        	for (int j = M - 1 - i; j > i; j--) arr.add(array[N - 1 - i][j]);
        	for (int j = N - 1 - i; j > i; j--) arr.add(array[j][i]);
        	groups.add(arr);
        }
        for (int i = 0; i < gr_cnt; i++) {
        	ArrayList<Integer> elem = groups.get(i);
        	int len = elem.size();
        	int index = R % len;
        	for (int j = i; j <= M - 2 - i; j++, index = (index + 1) % len) array[i][j] = elem.get(index);
        	for (int j = i; j <= N - 2 - i; j++, index = (index + 1) % len) array[j][M - 1 - i] = elem.get(index);
        	for (int j = M - 1 - i; j > i; j--, index = (index + 1) % len) array[N - 1 - i][j] = elem.get(index);
        	for (int j = N - 1 - i; j > i; j--, index = (index + 1) % len) array[j][i] = elem.get(index);
        }
        for (int i = 0; i < array.length; i++) {
        	for (int j = 0; j < array[0].length; j++) sb.append(array[i][j] + " ");
        	sb.append("\n");
        }
        System.out.println(sb);
    }
}