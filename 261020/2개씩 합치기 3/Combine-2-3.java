import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] arr, prefix;
    static int[][] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        arr = new int[N + 1];
        for(int i = 1; i <= N; i++) arr[i] = Integer.parseInt(st.nextToken());

        dp = new int[N + 1][N + 1];
        prefix = new int[N + 1];
        
        for(int i = 1; i <= N; i++){
            prefix[i] = prefix[i - 1] + arr[i];
        }

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                dp[i][j] = (int)2e9;
            }
        }

        for(int i = 1; i <= N; i++) dp[i][i] = 0;

        for(int gap = 2; gap <= N; gap++){
            for(int i = 1; i <= N - gap + 1; i++){
                int j = i + gap - 1;
                for(int k = i; k < j; k++){
                    int cost = dp[i][k] + dp[k + 1][j] + prefix[j] - prefix[i] + arr[i];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        System.out.println(dp[1][N]);

    }
}