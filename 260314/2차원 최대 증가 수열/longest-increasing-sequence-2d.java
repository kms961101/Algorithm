import java.util.*;
public class Main {
    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                grid[i][j] = sc.nextInt();

        int MIN = Integer.MIN_VALUE;
        int [][] dp = new int[n][m];
        for(int i = 0; i < n; i++) Arrays.fill(dp[i], MIN);
        dp[0][0] = 1;
        
        
        for(int i = 0; i < n; i++) {
        	for(int j = 0; j < m; j++) {
        		// 이전에 방문한 곳중에 점프가 가능하면 갱신
        		for(int k = 0; k < i; k++) {
        			for(int l = 0; l < j; l++) {
        				if(dp[k][l] == MIN) continue;
        				if(grid[i][j] > grid[k][l]) dp[i][j] = Math.max(dp[i][j], dp[k][l] + 1);
        			}
        		}
        	}
        }

        int ans = MIN;
        for(int i = 0; i < n; i++) {
        	for(int j = 0; j < m; j++) {
        		ans = Math.max(ans, dp[i][j]);
        	}
        }
        System.out.println(ans);
    }
}