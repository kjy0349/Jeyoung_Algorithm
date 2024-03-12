package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Queue;

public class BACK_1918 {
    public static void pushAlphabets(Deque<Character> stack, char operand, String operand2, char operator) {
        stack.push('(');
        stack.push(operand);
        stack.push(operator);
        for (int i = 0; i < operand2.length(); i++) {
            stack.push(operand2.charAt(i));
        }
        stack.push(')');
    }
    public static String findMulDiv(String original) {
        StringBuilder sb = new StringBuilder();
        Deque<String> stack = new ArrayDeque<>();
        String operator = " ";
        for (int i = 0; i < original.length(); i++) {
            char elem = original.charAt(i);
            if (operator != " ") { // 연산자 찾은게 있으면
                String operand = stack.pop();
                operator = ' ';
            } else if (elem == '*' || elem == '/') {
                operator = elem;
            } else {
                stack.push(elem);
            }
        }
        stack = stack.reversed();
        return sb.toString();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String original = br.readLine();
        String after = findMulDiv(original);
        System.out.println(after);
    }
}
