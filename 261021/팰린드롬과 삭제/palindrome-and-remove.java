import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] temp = br.readLine().toCharArray();
        int n = temp.length;
        char[] arr = new char[n + 1];
        for(int i = 1; i <= n; i++) arr[i] = temp[i - 1];
        
        int[][] dp = new int[n + 1][n + 1];
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= n; j++) {
                dp[i][j] = (int)2e9;
            }
        }
        
        // 구간이 1일때
        for(int i = 0; i <= n; i++) dp[i][i] = 0;
        
        // 구간이 2일때 같으면 0, 틀리면 지워야 하니 1
        for(int i = 0; i < n; i++) {
            dp[i][i + 1] = arr[i] == arr[i + 1] ? 0 : 1;
        }
        
        for(int gap = 3; gap <= n; gap++) {
            for(int i = 1; i <= n - gap + 1; i++) {
                int j = i + gap - 1;
                
                // i ~ j -1 사용하고 마지막 버리기
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                // i + 1 ~ j 사용하고 맨 앞에 버리기
                dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);
                // 동일하면 이전 값 사용
                if(arr[i] == arr[j])
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
            }
        }
        
        System.out.println(dp[1][n]);
    }
    
}
