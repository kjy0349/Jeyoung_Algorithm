import java.util.*;
import java.io.*;
class Main {
    static class IndexedNum{
        int index;
        int num;
        public IndexedNum(int index, int num){
            this.index = index;
            this.num = num;
        }
    }
    public static void main (String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] answer = new int[N+1];
        Arrays.fill(answer, -1);
        Deque<IndexedNum> stack = new ArrayDeque<IndexedNum>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            IndexedNum cur = new IndexedNum(i, Integer.parseInt(st.nextToken()));
            if (!stack.isEmpty()){
                if (stack.peek().num < cur.num) {
                    while (!stack.isEmpty() && stack.peek().num < cur.num){
                        IndexedNum temp = stack.pop();
                        answer[temp.index] = cur.num;
                    }
                }
            }
            stack.push(cur);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++){
            sb.append(answer[i]).append(" ");
        }
        System.out.println(sb.toString());
    }
}