import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int MIN = Integer.MIN_VALUE;
        int [] dp = new int[n];
        Arrays.fill(dp, MIN);
        dp[0] = 0;
        
        for(int i = 1; i < n; i++) {
        	for(int j = 0; j < i; j++) {
        		if(dp[j] == MIN) continue;
        		if(j + arr[j] >= i) {
        			dp[i] = Math.max(dp[i], dp[j] + 1);
        		}
        	}
        }

        int ans = MIN;
        for(int i = 0; i < n; i++) ans = Math.max(ans, dp[i]);
        System.out.println(ans);
    }
}