import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] weight = new int[N + 1];
        int[] value = new int[N + 1];
        int[] dp = new int[M + 1];
        for(int i = 1; i <= N; i++) {
        	st = new StringTokenizer(br.readLine());
        	weight[i] = Integer.parseInt(st.nextToken());
        	value[i] = Integer.parseInt(st.nextToken());
        }
        
        for(int i = 1; i <= N; i++) {
        	for(int j = 1; j <= M; j++) {
        		if(j < weight[i]) continue;
        		dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
        	}
        }
        
        int max = Arrays.stream(dp).max().getAsInt();
        System.out.println(max);
    }
}