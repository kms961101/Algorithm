import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] temp = br.readLine().toCharArray();
        int n = temp.length;
        char[] arr = new char[n + 1];
        for(int i = 1; i <= n; i++) arr[i] = temp[i - 1];
        
        boolean[][] dp = new boolean[n + 1][n + 1];
        int ans = 0;
        for(int i = 1; i < n; i++) {
            if(arr[i] == arr[i + 1]) dp[i][i + 1] = true;
            if(dp[i][i + 1]) ans = 2;
            dp[i][i] = true;
        }
        
        for(int gap = 3; gap <= n; gap++) {
            for(int i = 1; i <= n - gap + 1; i++) {
                int j = i + gap - 1;
                if(dp[i + 1][j - 1] && arr[i] == arr[j]) dp[i][j] = true;
                if(dp[i][j]) ans = gap;
            }
        }
        
        System.out.println(ans);
    }
    
}
