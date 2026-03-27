import java.io.*;
import java.util.*;

public class Main {
    static int n;
	static int[] arr, dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        dp = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.fill(dp, -1);
        dp[1] = arr[1];
        
        for(int i = 2; i <= n; i++) {
        	dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);
        }
        
        int ans = Arrays.stream(dp).max().getAsInt();
        System.out.println(ans);
    }
}