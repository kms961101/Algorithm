import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }


        int [] dp = new int[n];
        for(int i = 0; i < n; i++){
            int num = arr[i];
            if(num == 0 && dp[i] == 0) break; 
            for(int j = i + 1; j <= i + num; j++){
                if(j >= n) continue;
                dp[j] = Math.max(dp[j], dp[i] + 1);
            }
        }

        int ans = 0;
        for(int i = 0; i < n; i++) ans = Math.max(ans, dp[i]);
        System.out.println(ans);
    }
}