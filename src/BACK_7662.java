package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BACK_7662 {
    static class Pelem {
        int elem;
        boolean isOut;
        Pelem(int elem) {
            this.isOut = false;
            this.elem = elem;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int test = 0; test < T; test++) {
            PriorityQueue<Pelem> maxQue = new PriorityQueue<>((x, y) -> {
                return Integer.compare(y.elem, x.elem);
            });
            PriorityQueue<Pelem> minQue = new PriorityQueue<>((x, y) -> {
                return Integer.compare(x.elem, y.elem);
            });
            int N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                String command = st.nextToken();
                int value = Integer.parseInt(st.nextToken());
                if (command.equals("I")) {
                    Pelem elem = new Pelem(value);
                    maxQue.offer(elem);
                    minQue.offer(elem);
                } else {
                    if (value > 0) {
                        while (!maxQue.isEmpty()) {
                            Pelem elem = maxQue.poll();
                            if (!elem.isOut) {
                                elem.isOut = true;
                                break;
                            }
                        }
                    } else {
                        while (!minQue.isEmpty()) {
                            Pelem elem = minQue.poll();
                            if (!elem.isOut) {
                                elem.isOut = true;
                                break;
                            }
                        }
                    }
                }
            }

            if (!maxQue.isEmpty() && !minQue.isEmpty()) {
                Pelem max = null;
                Pelem min = null;
                while (!minQue.isEmpty()) {
                    Pelem elem = minQue.poll();
                    if (!elem.isOut) {
                        min = elem;
                        break;
                    }
                }
                while (!maxQue.isEmpty()) {
                    Pelem elem = maxQue.poll();
                    if (!elem.isOut) {
                        max = elem;
                        break;
                    }
                }
                if (max == null || min == null) System.out.println("EMPTY");
                else System.out.println(max.elem + " " + min.elem);
            } else System.out.println("EMPTY");

        }
    }
}
