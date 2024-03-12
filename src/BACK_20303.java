package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BACK_20303 {
    /*
        1. 친구가 없는 애도 있을 수도 있다. M으로 연관되어있지 않은 경우, weight가 1인 Bag 추가해야됨
        2. 7%에서 틀린다... 예제 2 0 2, 12 -> 답이 2이어야 함
                        if (i - bag.weight >= 0) { 92번째 줄 조건문 실패
     */
    static class Bag {
        int weight;
        int candy;
        Bag(int weight, int candy) {
            this.weight = weight;
            this.candy = candy;
        }

        @Override
        public String toString() {
            return weight + " " + candy;
        }
    }

    public static ArrayList<Bag> findBags(int[] candies, ArrayList<ArrayList<Integer>> adjList) {
        // 가방 찾기
        ArrayList<Bag> retArr = new ArrayList<>();
        boolean[] visited = new boolean[candies.length];
        for (int i = 1; i < adjList.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                int candy = candies[i];
                int weight = 1;
                Queue<Integer> que = new ArrayDeque<>();
                que.offer(i);
                while (!que.isEmpty()) {
                    int target = que.poll();
                    for (int elem : adjList.get(target)) {
                        if (!visited[elem]) {
                            que.offer(elem);
                            visited[elem] = true;
                            weight++;
                            candy += candies[elem];
                        }
                    }
                }
                retArr.add(new Bag(weight, candy));
            }
        }
        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                retArr.add(new Bag(1, candies[i]));
            }
        }
        return retArr;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
        int[] candies = new int[N + 1];
        adjList.add(new ArrayList<>());
        for (int i = 1; i <= N; i++) { // candies -> 해당 인간이 갖고 있는 사탕 수, adjList -> 친구 관계
            candies[i] = Integer.parseInt(st.nextToken());
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            adjList.get(start).add(end);
            adjList.get(end).add(start);
        }
        
        ArrayList<Bag> bags = findBags(candies, adjList); // K는 들 수 있는 가방 최대 무게
        int[] dp = new int[K]; // K 무게를 만든다. N번째 가방을 써서
        for (int bIdx = 0; bIdx < bags.size(); bIdx++) {
            Bag bag = bags.get(bIdx);
            for (int i = K - 1; i >= bag.weight; i--) {
                dp[i] = Math.max(dp[i], dp[i - bag.weight] + bag.candy);
            }
        }
        System.out.println(dp[K - 1]);
    }
}
