import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int MIN_V = 1000009;
        int[] arr = new int[N + 1];
        int[][] dp = new int[N + 1][K + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) arr[i] = Integer.parseInt(st.nextToken());
        for(int i = 0; i <= N; i++) Arrays.fill(dp[i], -MIN_V);
        if(arr[1] >= 0) dp[1][0] = arr[1];
        else dp[1][1] = arr[1];
        int max = arr[1];
        for(int i = 2; i <= N; i++) {
        	// 양수 일때
        	if(arr[i] >= 0) {
        		dp[i][0] = Math.max(dp[i - 1][0] + arr[i], arr[i]);
        		max = Math.max(max, dp[i][0]);
        		
        		for(int j = 1; j <= K; j++) {
        			if(dp[i - 1][j] == -MIN_V) continue;
        			dp[i][j] = dp[i - 1][j] + arr[i];
        			max = Math.max(max, dp[i][j]);
        		}
        	}
        	// 음수  일때
        	else {
        		dp[i][1] = Math.max(dp[i - 1][0] + arr[i], arr[i]);
        		max = Math.max(max, dp[i][1]);
        		
        		for(int j = 2; j <= K; j++) {
        			if(dp[i - 1][j - 1] == -MIN_V) continue;
        			dp[i][j] = dp[i - 1][j - 1] + arr[i];
        			max = Math.max(max, dp[i][j]);
        		}
        	}
        }
        
        System.out.println(max);
    }
}