import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/*
수들을 먼저 int 배열로 만든 다음에
각 재귀마다 4번씩 돌리면서 하나씩 빼는걸로.
끝까지 갔을 때 min과 max와 함께 초기화.
 */
public class BACK_14888 {
    static long max = Long.MIN_VALUE;
    static long min = Long.MAX_VALUE;
    static int[] nums;
    static int[] op_cnts;
    static String[] ops = new String[]{"+", "-", "*", "/"};
    public static long calc(long sum, int oper, int opidx) {
        if (opidx == 0) return sum + oper;
        else if (opidx == 1) return sum - oper;
        else if (opidx == 2) return sum * oper;
        else return sum / oper;
    }

    public static void find_sol(int depth, long sum) {
        if (depth == nums.length) {
            if (sum > max) max = sum;
            if (min > sum) min = sum;
        } else {
            for (int j = 0; j < op_cnts.length; j++) {
                if (op_cnts[j] > 0) {
                    op_cnts[j]--;
                    find_sol(depth + 1, calc(sum, nums[depth], j));
                    op_cnts[j]++;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException { // + - * /
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        op_cnts = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        find_sol(  1, nums[0]);
        System.out.println(max);
        System.out.println(min);
    }
}
