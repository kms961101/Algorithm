import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] dp = new int[M + 1];
		int[] weight = new int[10001];
		int[] value = new int[10001];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			weight[i] = Integer.parseInt(st.nextToken());
			value[i] = Integer.parseInt(st.nextToken());
		}
		
		int ans = 0;
		for(int i = 1; i <= N; i++) {
			for(int j = M; j >= weight[i]; j--) {
				dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
			}
		}
	
		for(int i = 0; i <= M; i++) ans = Math.max(ans, dp[i]);
		System.out.println(ans);
    }
}