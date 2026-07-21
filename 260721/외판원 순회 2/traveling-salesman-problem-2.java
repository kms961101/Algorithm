import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] dist, dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dist = new int[n][n];
        dp = new int[1 << 16][n];
        
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int i = 0; i < (1 << n); i++) {
            for(int j = 0; j < n; j++) {
                dp[i][j] = (int)1e9;
            }
        }
        
        
        dp[1][0] = 0;
        
        for(int i = 0; i < (1 << n); i++) {
            for(int j = 0; j < n; j++) {
                // i => j 방문이 불가하면 패스
                if(((i >> j) & 1) == 0)
                    continue;
                
                for(int k = 0; k < n; k++) {
                    // k번 지점을 방문한적 있다면 패스
                    if(((i >> k) & 1) == 1) continue;
                    // 가는 길이 없으면 패스
                    if(dist[j][k] == 0) continue;
                    
                    dp[i + (1 << k)][k] = Math.min(dp[i + (1 << k)][k], dp[i][j] + dist[j][k]);
                }
                
            }
        }
        int ans = (int)1e9;
        for(int i = 0; i < n; i++) {
            if(dist[i][0] == 0) continue;
            ans = Math.min(ans, dp[(1 << n) -1][i] + dist[i][0]);
        }
        
        System.out.println(ans);
    }
}