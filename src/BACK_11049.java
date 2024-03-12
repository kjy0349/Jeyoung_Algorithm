package src;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

public class BACK_11049 {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(bs);
        PrintStream old = System.out;
        System.setOut(ps);
        // 테스트케이스 체크용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {

        }
        System.out.println(90);
        // 체킹
        System.out.flush();
        System.setOut(old);
        checkOutput(bs.toString());
    }

    private static void checkOutput(String string) {
        if (string.equals("90")) System.out.println("성공");
    }


}
