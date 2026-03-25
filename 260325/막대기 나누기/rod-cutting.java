import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N + 1];
        int[] dp = new int[101];
        for(int i = 1; i <= N; i++) arr[i] = Integer.parseInt(st.nextToken());
        
        for(int i = 1; i <= N; i++) {
        	for(int j = i; j <= N; j += i) {
        		dp[j] = Math.max(dp[j], dp[j - i] + arr[i]);
        	}
        }
        int ans = Arrays.stream(dp).max().getAsInt();
        System.out.println(ans);
	}
}