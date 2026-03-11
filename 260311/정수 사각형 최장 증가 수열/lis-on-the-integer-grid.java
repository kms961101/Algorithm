import java.util.*;
import java.io.*;
public class Main {
    static int N;
	static int[][] map, dp;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N];
        
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 0; j < N; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        		dp[i][j] = 1;
        	}
        }
        
        for(int i = 0; i < N; i++) {
        	for(int j = 0; j < N; j++) {
        		dfs(i, j);
        	}
        }
        int max = 0;
        for(int i = 0; i < N; i++) {
        	for(int j = 0; j < N; j++) {
        		max = Math.max(max, dp[i][j]);
        	}
        }
        System.out.println(max);
    }
	
	static void dfs(int x, int y) {
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(isIn(nx, ny) && map[nx][ny] > map[x][y] && dp[nx][ny] < dp[x][y] + 1) {
				dp[nx][ny] = dp[x][y] + 1;
				dfs(x, y);
			}
		}
	}
	
	static boolean isIn(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}
}