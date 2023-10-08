package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class BACK_9019_2 {
    static Set<Integer> poss; // 그냥 돌려서 찾을 수 없을 때 가능한 후보들을 모아놓음
    static int subDepth; // Pos를 찾을 때 필요한 최소 depth
    static String target; // pos를 이용해 찾은 가능한 문자열
    static StringBuilder sb; // 출력용
    static String answer; // 출력용 sb를 변환한 answer
    static int tNum; // 타겟 수
    static boolean[] visited;
    public static void findPos(int num, int depth) {
        if (depth < subDepth && poss.contains(num)) {
            subDepth = depth;
            target = Integer.toString(num);
            answer = sb.toString();
        } else if (depth < subDepth){
            if (!visited[(num * 2) % 10000]) {
                visited[(num * 2) % 10000] = true;
                sb.append("D");
                findPos((num * 2) % 10000, depth + 1);
                sb.deleteCharAt(sb.length() - 1);
            }
            if (!visited[num == 0 ? 9999 : num - 1]) {
                visited[num == 0 ? 9999 : num - 1] = true;
                sb.append("S");
                findPos(num == 0 ? 9999 : num - 1, depth + 1);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public static void findSpin(char[] num, int depth) {
        if (answer.equals("") && Integer.parseInt(new String(num)) == tNum) {
            answer += sb.toString();
            return ;
        }
        if (depth == num.length) {
            return ;
        }
        // 왼쪽으로 돌리기
        char[] left = num.clone();
        char tmp = left[0];
        for (int j = 1; j < num.length; j++) {
            left[j - 1] = left[j];
        }
        left[left.length - 1] = tmp;
        if (Integer.parseInt(new String(left)) == 0) left = new char[]{'9', '9', '9', '9'};
        sb.append('L');
        findSpin(left, depth + 1);
        sb.deleteCharAt(sb.length() - 1);
        // 오른쪽으로 돌리기
        char[] right = num.clone();
        tmp = right[right.length - 1];
        for (int j = 0; j < num.length - 1; j++) {
            right[j + 1] = right[j];
        }
        right[0] = tmp;
        if (Integer.parseInt(new String(right)) == 0) right = new char[]{'9', '9', '9', '9'};
        sb.append('R');
        findSpin(right, depth + 1);
        sb.deleteCharAt(sb.length() - 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int test = 0; test < T; test++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            char[] end = st.nextToken().toCharArray();
            tNum = Integer.parseInt(new String(end));
            poss = new HashSet<>();
            sb = new StringBuilder();
            subDepth = 30000;
            visited = new boolean[10000];
            answer="";
            // 일단 시작수로 되는지 먼저 찾아보고 -> 안 찾아지면 find 해보기
            findSpin(Integer.toString(start).toCharArray(), 0);
            if (answer.equals("")) {
                sb.setLength(0);
                // 안 찾아질 때
                for (int i = 0;i < end.length; i++) {
                    poss.add(Integer.parseInt(new String(end)));
                    char tmp = end[0];
                    for (int j = 1; j < end.length; j++) {
                        end[j - 1] = end[j];
                    }
                    end[end.length - 1] = tmp;
                }
                // 타겟 수 까지 구하기
                findPos(start, 0);
                visited[start] = true;
                findSpin(target.toCharArray(), 0);
                System.out.println(answer);
            } else System.out.println(answer);
        }
    }
}