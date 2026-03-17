import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] arr = new int[n + 1];
		boolean[][] dp = new boolean[101][10001];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		dp[0][0] = true;
		for(int i = 1; i <= n; i++) {
			for(int j = 0; j <= m; j++) {
				// 현재 번호를 선택해서 m이 된 경우
				if(j >= arr[i] && dp[i - 1][j - arr[i]]) dp[i][j] = true;
				// 이전에 이미 j값이 성공 하면 안뽑아도 성공
				if(dp[i - 1][j]) dp[i][j] = true;
			}
		}
		
		if(dp[n][m]) System.out.println("Yes");
		else System.out.println("No");
		
    }
}