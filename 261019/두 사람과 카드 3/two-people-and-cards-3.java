import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] arr;
    static int[][][][] dp;
    static boolean[] visited = new boolean[2001];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        dp = new int[N + 1][N + 1][N + 1][N + 1];
        
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        for(int i = 0; i <= N; i++) {
            for(int j = 0; j <= N; j++) {
                for(int k = 0; k <= N; k++) {
                    for(int l = 0; l <= M; l++) {
                        dp[i][j][k][l] = (int)2e9;
                    }
                }
            }
        }
        
        
        dp[0][0][0][0] = 0;
        
        for(int i = 0; i <= N; i++) {
            for(int j = 0; j <= N; j++) {
                for(int k = 0; k <= N; k++) {
                    for(int l = 0; l <= M; l++) {
                        int next = i + 1;
                        if(next == N + 1) continue;
                        
                        dp[next][next][k][l] = Math.min(dp[next][next][k][l], dp[i][j][k][l] + dist(j, next));
                        dp[next][j][next][l] = Math.min(dp[next][j][next][l], dp[i][j][k][l] + dist(k, next));
                        if(l != M) dp[next][j][k][l + 1] = Math.min(dp[i][j][k][l], dp[next][j][k][l + 1]);
                    }
                }
            }
        }
        
        int ans = (int)2e9;
        
        for(int j = 0; j <= N; j++) {
            for(int k = 0; k <= N; k++) {
                ans = Math.min(ans, dp[N][j][k][M]);
            }
        }
        
        System.out.println(ans);
        
    }
    
    static int dist(int i, int j) {
        if(i == 0) return 0;
        return Math.abs(arr[i] - arr[j]);
    }
}
