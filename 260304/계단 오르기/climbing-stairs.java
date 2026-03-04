import java.util.*;
import java.io.*;
public class Main {
    static int[] dp = new int[1001];
    static int N;
    static int mod = 10007;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        dp[0] = 1;
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;
        // 2칸, 3칸 전에서 올라오는 경우끼리 더한 값만 해당
        for(int i = 4; i <= N; i++){
            dp[i] = (dp[i - 2] + dp[i - 3]) % mod;
        }

        System.out.println(dp[N]);
    }
}