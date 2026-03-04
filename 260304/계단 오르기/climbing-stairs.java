import java.util.*;
import java.io.*;
public class Main {
    static int[] dp = new int[1001];
    static int N;
    static int INF = 987654321;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        Arrays.fill(dp, INF);
        dp[0] = 0;
        move(0);
        System.out.println(dp[N] == INF ? 0 : dp[N]);
    }

    static void move(int num){
        if(num + 2 <= N && dp[num + 2] > dp[num] + 1){
            dp[num + 2] = dp[num] + 1;
            move(num + 2);
        }

        if(num + 3 <= N && dp[num + 3] > dp[num] + 1){
            dp[num + 3] = dp[num] + 1;
            move(num + 3);
        }
    }
}