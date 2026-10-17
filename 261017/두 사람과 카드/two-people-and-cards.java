import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] arr;
    static int[][] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        dp = new int[N + 1][N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            Arrays.fill(dp[i], (int)2e9);
        }
        Arrays.fill(dp[0], (int)2e9);
        dp[0][0] = 0;
        
        for(int i = 0; i <= N; i++) {
            for(int j = 0; j <= N; j++) {
                int next = Math.max(i, j) + 1;
                if(next == N + 1) continue;
                
                dp[next][j] = Math.min(dp[next][j], dp[i][j] + dist(i, next));
                dp[i][next] = Math.min(dp[i][next], dp[i][j] + dist(j, next));
            }
        }
        
        int ans = (int)2e9;
        for(int i = 0; i < N; i++) ans = Math.min(ans, dp[i][N]);
        
        System.out.println(ans);
    }
    
    static int dist(int i, int j) {
        if(i == 0) return 0;
        return Math.abs(arr[i] - arr[j]);
    }
}
