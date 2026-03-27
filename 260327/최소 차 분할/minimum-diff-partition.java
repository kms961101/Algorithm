import java.io.*;
import java.util.*;

public class Main {
    static int n, total, min = 987654321;
	static int MAX_N = 100, MAX_M = 100000;
	static int[] arr;
	static boolean[][] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        dp = new boolean[MAX_N + 1][MAX_M + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) arr[i] = Integer.parseInt(st.nextToken());
        total = Arrays.stream(arr).sum();
        
        for(int i = 0; i < MAX_N; i++) Arrays.fill(dp[i], false);
        dp[0][0] = true;
        
        for(int i = 1; i <= n; i++) {
        	for(int m = 1; m <= total; m++) {
        		// 1. i번째 선택하는 경우
        		if(m - arr[i] >= 0 && dp[i - 1][m - arr[i]]) dp[i][m] = true;
        		// 2. i번 선택 안해도 무게 m이 가능할 경우
        		if(dp[i - 1][m]) dp[i][m] = true;
        	}
        }
        
        for(int i = 1; i <= total; i++) {
        	if(dp[n][i]) min = Math.min(min, Math.abs(i - (total - i)));
        }
        
        System.out.println(min);
    
    }
}