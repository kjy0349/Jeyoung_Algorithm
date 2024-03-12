package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class BACK_2668 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            int value = Integer.parseInt(br.readLine());
            map.put(i, value);
        }
        Set<Integer> answer = new TreeSet<>();

        outLoop:
        for (int i = 1; i <= N; i++) {
            boolean[] visited = new boolean[N + 1];
            int start = i;
            int next = map.get(start);
            if (start == next) {
                answer.add(next);
                continue;
            }
            if (answer.contains(next)) continue;
            Set<Integer> temp = new HashSet<>();
            visited[start] = true;
            temp.add(next);
            while (map.containsKey(next) && !visited[next]) {
                visited[next] = true;
                int before = next;
                next = map.get(next);
                if (next == start && before != next) {
                    temp.add(next);
                    answer.addAll(temp);
                    continue outLoop;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(answer.size()).append("\n");
        for (int elem : answer) sb.append(elem).append("\n");
        System.out.print(sb);
    }
}
