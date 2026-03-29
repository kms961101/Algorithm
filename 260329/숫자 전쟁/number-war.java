import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] a = new int[N + 1];
        int[] b = new int[N + 1];
        int[][] dp = new int[N + 1][N + 1];
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	for(int i = 1; i <= N; i++) a[i] = Integer.parseInt(st.nextToken());
    	st = new StringTokenizer(br.readLine());
    	for(int i = 1; i <= N; i++) b[i] = Integer.parseInt(st.nextToken());
    	
    	for(int i = 0; i <= N; i++) Arrays.fill(dp[i], -1);
    	dp[0][0] = 0;
    	
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < N; j++) {
    			if(dp[i][j] == -1) continue;
    			// 1. 첫번째 카드가 작은 경우
    			if(a[i + 1] < b[j + 1]) {
    				dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][j]);
    			}
    			
    			// 2. 두번째 카드가 작은 경우
    			if(a[i + 1] > b[j + 1]) {
    				dp[i][j + 1] = Math.max(dp[i][j + 1], dp[i][j] + b[j + 1]);
    			}
    			
    			// 3. 둘 다 버리는 경우
    			dp[i + 1][j + 1] = Math.max(dp[i + 1][j + 1], dp[i][j]);
    		}
    	}
    	int ans = 0;
    	for(int i = 0; i <= N; i++) {
    		ans = Math.max(ans, dp[i][N]);
    		ans = Math.max(ans, dp[N][i]);
    	}
    	
    	System.out.println(ans);
    }
}