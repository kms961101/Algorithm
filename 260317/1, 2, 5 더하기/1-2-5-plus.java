import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int mod = 10007;
        int[] dp = new int[n + 1];
        int[] arr = {1, 2, 5};
        dp[0] = 1;
        for(int i = 1; i <= n; i++) {
        	for(int j = 0; j < 3; j++) {
        		if(i - arr[j] < 0) continue;
        		dp[i] = (dp[i] + dp[i - arr[j]]) % 10007;
        	}
        }
        
        System.out.println(dp[n]);
    }
}