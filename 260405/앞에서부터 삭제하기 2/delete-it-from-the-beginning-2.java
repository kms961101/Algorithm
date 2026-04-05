import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	PriorityQueue<Integer> pq = new PriorityQueue<>();
    	int[] arr = new int[N + 1];
    	int[] dp = new int[N + 1];
    	for(int i = 1; i <= N; i++) {
    		int num = Integer.parseInt(st.nextToken());
    		dp[i] = dp[i - 1] + num;
    		arr[i] = num;
    		pq.add(num);
    	}
    	double ans = 0;
    	for(int i = 1; i <= N - 2; i++) {
    		int sum = dp[N] - dp[i];
    		pq.remove(arr[i]);
    		
    		int first = pq.peek();
    		ans = Math.max(ans, (sum - first) / (N - i - 1));
    	}
    	
    	System.out.printf("%.2f", ans);
    }
}