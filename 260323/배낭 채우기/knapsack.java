import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] dp = new int[N + 1][M + 1];
		int[] weight = new int[10001];
		int[] value = new int[10001];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			weight[i] = Integer.parseInt(st.nextToken());
			value[i] = Integer.parseInt(st.nextToken());
		}
		
		//for(int i = 0; i <= N; i++) Arrays.fill(dp[i], -1);
		dp[0][0] = 0;
		int ans = 0;
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j <= M; j++) {
				if(j >= weight[i]) {
					dp[i][j] = Math.max(dp[i - 1][j - weight[i]] + value[i], dp[i - 1][j]);
				}
				else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		
		for(int i = 0; i <= M; i++) ans = Math.max(ans, dp[N][i]);
		
		System.out.println(ans);
    }
}