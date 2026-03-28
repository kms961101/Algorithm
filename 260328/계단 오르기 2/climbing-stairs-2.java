import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N + 1];
        int[][] dp = new int[4][N + 1];
        for(int i = 1; i <= N; i++) arr[i] = Integer.parseInt(st.nextToken());
        for(int i = 0; i <= 3; i++) Arrays.fill(dp[i], Integer.MIN_VALUE);
        for(int i = 0; i <= N; i += 2) dp[0][i] = arr[i];
        
        for(int i = 0; i <= 3; i++) {
        	for(int j = 1; j <= N; j++) {
        		// 1칸 넘어서 높이 j가 되는 경우
        		if(i > 0) dp[i][j] = Math.max(dp[i - 1][j - 1] + arr[j],dp[i][j]);
        		// 2칸 넘어서 높이 j가 되는 경우
        		if(j - 2 >= 0) dp[i][j] = Math.max(dp[i][j - 2] + arr[j], dp[i][j]);
        	}
        }
        int ans = 0;
        for(int i = 0; i <= 3; i++) {
        	int max = Arrays.stream(dp[i]).max().getAsInt();
        	ans = Math.max(ans, max);
        }
        System.out.println(ans);
	}
}