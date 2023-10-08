import java.io.*;

public class SWEA_1954 {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[][] arr;
    public static void dfs(int x, int y, int dir, int start) {
        if (start <= arr.length * arr[0].length) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (nx >= 0 && ny >= 0 && nx < arr.length && ny < arr[0].length) {
                if (arr[nx][ny] == 0) {
                    arr[nx][ny] = start;
                    dfs(nx, ny, dir, start + 1);
                } else dfs(x, y, (dir + 1) % 4, start);
            } else {
                dfs(x, y, (dir + 1) % 4, start);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int test = 1; test <= T; test++) {
        	int N = Integer.parseInt(br.readLine());
        	arr = new int[N][N];
        	int start = 1;
        	dfs(0, -1, 0, start);
        	bw.write("#" + test + "\n");
        	for (int i = 0; i < arr.length; i++) {
        		for (int j = 0; j < arr[0].length - 1; j++) bw.write(arr[i][j] + " ");
        		bw.write(arr[i][arr[0].length - 1] + "\n");
        	}       	
        }
        bw.flush();
    }
}