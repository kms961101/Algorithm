import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int K = 10;
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n + 1];
        int[][][] dp = new int[n + 1][n + 1][K + 1];
        boolean[][][] pos = new boolean[n + 1][n + 1][K + 1];
        
        for(int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(st.nextToken());
            
        for(int i = 0; i < n; i++) {
            pos[i][i][arr[i]] = true;
            if(i + 1 != n) {
                pos[i][i + 1][Math.abs(arr[i] - arr[i + 1])] = true;
                dp[i][i + 1][Math.abs(arr[i] - arr[i + 1])] = arr[i] + arr[i + 1];
            }
                
        }
        
        for(int gap = 2; gap <= n; gap++) {
            for(int i = 0; i <= n - gap; i++) {
                int j = i + gap - 1;
                
                for(int k = i; k < j; k++) {
                    // 왼쪽
                    for(int x = 0; x <= K; x++) {
                        if(!pos[i][k][x]) continue;
                        // 오른쪽
                        for(int y = 0; y <= K; y++) {
                            if(!pos[k + 1][j][y]) continue;
                            
                            int cost = dp[i][k][x] + dp[k + 1][j][y] + x + y;
                            pos[i][j][Math.abs(x - y)] = true;
                            dp[i][j][Math.abs(x - y)] = Math.max(dp[i][j][Math.abs(x - y)], cost);
                        }
                    }
                }
            }
        }
        
        int ans = 0;
        
        for(int x = 0; x <= K; x++) {
            ans = Math.max(ans, dp[0][n - 1][x]);
        }
        
        System.out.println(ans);
        
    }
    
}
