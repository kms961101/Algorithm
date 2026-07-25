import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static int[][] dist, dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        dp = new int[1 << 16][n];
        dist = new int[n][n];
        
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int i = 0; i < (1 << 16); i++) {
            for(int j = 0; j < n; j++) dp[i][j] = (int)1e9;
        }
        
        dp[1][0] = 0;
        
        for(int i = 0; i < (1 << n); i++) {
            for(int j = 0; j < n; j++) {
                // j번 지점이 방문한게 불가능하면 패스
                if(((i >> j) & 1) == 0) continue;
                
                for(int l = 0; l < n; l++) {
                    // 이미 l 지점 방문 했으면 패스
                    if(((i >> l) & 1) == 1) continue;
                    // j 번에서 l로 이동 불가하면 패스
                    if(dist[j][l] == 0) continue;
                    
                    dp[i + (1 << l)][l] = Math.min(dp[i + (1 << l)][l], dp[i][j] + dist[j][l]);
                }
            }
        }
        
        int ans = (int)1e9;
        
        for(int i = 0; i < (1 << n); i++) {
            int cnt = Integer.bitCount(i);
            // 1부터 k개 뽑았는지, 1번이 기본으로 선택 되어있는지 체크
            if(cnt != k + 1 || (i & 1) != 1) continue;
            for(int j = 0; j < n; j++) {
                if(dist[j][0] == 0) continue;
                ans = Math.min(ans, dp[i][j] + dist[j][0]);
            }
        }
        
        System.out.println(ans);
        
    }
}