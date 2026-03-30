import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][][] dp = new int[n + 1][4][4];
        // 첫째날에 T를 받는 경우
        dp[1][1][0] = 1;
        // 첫째날에 B를 받는 경우
        dp[1][0][1] = 1;
        // 첫째날에 G를 받는 경우
        dp[1][0][0] = 1;
        int mod = 1000000007;
        // dp[i][t][b] => i일에 T를 t번 받고 B를 연속으로 b번 받은경우
        for(int i = 1; i < n; i++) {
        	for(int t = 0; t <= 2; t++) {
        		for(int b = 0; b <= 2; b++) {
        			// T 받는 경우 k는 연속이 끊김
        			dp[i + 1][t + 1][0] = (dp[i + 1][t + 1][0] + dp[i][t][b]) % mod;
        			// B 받는 경우
        			dp[i + 1][t][0] = (dp[i + 1][t][0] + dp[i][t][b]) % mod;
        			// K 받는 경우
        			dp[i + 1][t][b + 1] = (dp[i + 1][t][b + 1] + dp[i][t][b]) % mod;
        		}
        	}
        }
        
        int ans = 0;
        for(int i = 0; i <= 2; i++) {
        	for(int j = 0; j <= 2; j++) {
        		ans = (ans + dp[n][i][j]) % mod;
        	}
        }
        System.out.println(ans);
    }
}