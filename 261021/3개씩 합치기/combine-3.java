import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n + 1];
        int[][] dp = new int[n + 1][n + 1];
        
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i < n; i++) Arrays.fill(dp[i], (int)2e9);
        
        for(int i = 0; i < n; i++) {
            dp[i][i] = 0;
            if(i + 1 != n)
                dp[i][i + 1] = 0;
        }
        
        
        
        for(int gap = 2; gap <= n; gap++) {
            for(int i = 0; i <= n - gap; i++) {
                int j = i + gap - 1;
                
                for(int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + arr[i] * arr[k] * arr[j]);
                }
            }
        }
        
        System.out.println(dp[0][n - 1]);
        
    }
    
}
