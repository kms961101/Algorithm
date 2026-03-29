import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        int[] red = new int[N * 2  + 1];
        int[] blue = new int[N * 2 + 1];
        int[][] dp = new int[N * 2 + 1][N * 2 + 1];
        int MIN_V = Integer.MIN_VALUE;
        
        for(int i = 1; i <= N * 2; i++) {
        	st = new StringTokenizer(br.readLine());
        	red[i] = Integer.parseInt(st.nextToken());
        	blue[i] = Integer.parseInt(st.nextToken());
        }
        
        for(int i = 0; i < N * 2 + 1; i++) Arrays.fill(dp[i], MIN_V);
        dp[0][0] = 0;
        
        for(int i = 1; i <= 2 * N; i++) {
        	for(int j = 0; j <= i; j++) {
                // 빨간색 고르는 경우
        		if(j > 0) {
        			dp[i][j] = Math.max(dp[i - 1][j - 1] + red[i], dp[i][j]);
        		}
                // 파란색 고르는 겨
        		if(i - j > 0) {
        			dp[i][j] = Math.max(dp[i - 1][j] + blue[i], dp[i][j]);
        		}
        	}
        }
        
        System.out.println(dp[N * 2][N]);
	}
}