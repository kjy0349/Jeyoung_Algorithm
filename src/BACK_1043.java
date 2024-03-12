package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BACK_1043 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 첫번째 입력 N은 사람 수, M 은 파티 수
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 진실을 아는 사람들 입력 받기
        st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());
        Queue<Integer> realMans = new ArrayDeque<>();
        if (num != 0) {
            for (int i = 0; i < num; i++) {
                realMans.offer(Integer.parseInt(st.nextToken()));
            }
        }

        // 파티에 속한 사람, 해당 사람이 속한 파티 저장하는 배열 초기화
        ArrayList<ArrayList<Integer>> partyArr = new ArrayList<>(M + 1);
        ArrayList<ArrayList<Integer>> peopleArr = new ArrayList<>(N + 1);
        for (int i = 0; i < N + 1; i++) {
            peopleArr.add(new ArrayList<>());
        }
        for (int i = 0; i < M + 1; i++) {
            partyArr.add(new ArrayList<>());
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int pNum = Integer.parseInt(st.nextToken());
            for (int j = 0; j < pNum; j++) {
                int people = Integer.parseInt(st.nextToken());
                partyArr.get(i).add(people); // 파티에 속한 사람 추가
                peopleArr.get(people).add(i); // 해당 사람이 속한 파티 추가
            }
        }


        boolean[] visited = new boolean[M + 1];
        while (!realMans.isEmpty()) {
            int people = realMans.poll(); // 사람
            ArrayList<Integer> parties = peopleArr.get(people); // 진실맨이 속한 파티들 인덱스 가져오기
            for (int party : parties) {
                if (!visited[party]) {
                    visited[party] = true;
                    ArrayList<Integer> attenders = partyArr.get(party);
                    realMans.addAll(attenders);
                }
            }
        }
        int answer = 0;
        for (int i = 1; i <= M; i++) {
            if (!visited[i]) answer++;
        }
        System.out.println(answer);
    }
}
