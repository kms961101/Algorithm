import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		int[] dp = new int[m + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			dp[arr[i]] = 1;
		}
		
		for(int i = 1; i <= m; i++) {
			for(int j = 0; j < n; j++) {
				if(i - arr[j] > 0) {
					dp[i] = Math.max(dp[i], dp[i - arr[j]] + 1);
				}
			}
		}
		
		System.out.println(dp[m] == 0 ? -1 : dp[m]);
		
    }
}