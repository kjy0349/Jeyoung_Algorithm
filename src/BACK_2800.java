package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/*
    15%에서 틀릴만한 것 : 1. 중복 제거를 안함
    3%에서 틀린것 : 모름
 */

public class BACK_2800 {
    static class Parentheses {
        int index;
        char parentheses;
        Parentheses(int index, char parentheses) {
            this.index = index;
            this.parentheses = parentheses;
        }
    }

    static class Pair {
        int leftIdx;
        int rightIdx;
        Pair(int leftIdx, int rightIdx) {
            this.leftIdx = leftIdx;
            this.rightIdx = rightIdx;
        }

        @Override
        public String toString() {
            return leftIdx + " " + rightIdx;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        ArrayList<Pair> pairs = new ArrayList<>();
        Deque<Parentheses> stack = new ArrayDeque<>();
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '(') {
                stack.push(new Parentheses(i, '('));
            } else if (line.charAt(i) == ')'
                    && (!stack.isEmpty() && stack.peek().parentheses == '(')) {
                pairs.add(new Pair(stack.pop().index, i));
            }
        }
        Collections.sort(pairs, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o2.leftIdx - o1.leftIdx;
            }
        });
        StringBuilder sb = new StringBuilder();
        Set<String> set = new HashSet<>();
        int size = pairs.size();
        for (int i = 1; i < (1 << size); i++) {
            StringBuilder temp = new StringBuilder();
            Set<Integer> arr = new HashSet<>();
            for (int j = 0; j < size; j++) {
                if ((i & (1 << j)) != 0) {
                    Pair pair = pairs.get(j);
                    arr.add(pair.leftIdx);
                    arr.add(pair.rightIdx);
                }
            }
            for (int j = 0; j < line.length(); j++) {
                if (!arr.contains(j)) {
                    temp.append(line.charAt(j));
                }
            }
            String answer = temp.toString();
            if (set.contains(answer)) {
                continue;
            } else {
                set.add(answer);
                sb.append(answer).append('\n');
            }
        }
        System.out.print(sb);
    }
}
