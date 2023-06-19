import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static int[][] map, dp;
	static int INF = 11000000;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		dp = new int[n][(1 << n) - 1];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			Arrays.fill(dp[i], -1);
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(dfs(0, 1));
	}
	
	static int dfs(int city, int bit) {
		if(bit == (1 << n) - 1) {
			if(map[city][0] == 0) {	// 이런 경우는 거의 없겠지만, 혹시 발생하는 경우 예외 처리
				return INF;
			}
			return map[city][0];
		}
		
		if(dp[city][bit] != -1) return dp[city][bit];
		dp[city][bit] = INF;
		
		for(int i = 0; i < n; i++) {
			if((bit & (1 << i)) == 0 && map[city][i] != 0) {
				dp[city][bit] = Math.min(dp[city][bit], dfs(i, bit | (1 << i)) + map[city][i]);
			}
		}
		return dp[city][bit];
	}
}