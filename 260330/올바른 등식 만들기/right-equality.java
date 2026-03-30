import java.io.*;
import java.util.*;

public class Main {
    static int MAX_M = 40;
	static int MIN_M = 0;
	static int offSet = 20;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] dp = new int[MAX_M + 1][MAX_M + 1];
        int[] arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        M += offSet;
        dp[0][offSet] = 1;
        
        for(int i = 1; i <= N; i++) {
        	for(int j = MIN_M; j <= MAX_M; j++) {
        		//1. 값을 빼는 경우
        		if(j + arr[i] <= MAX_M) {
        			dp[i][j] += dp[i - 1][j + arr[i]];
        		}
        		//2. 값을 더하는 경우
        		if(j - arr[i] >= MIN_M) {
        			dp[i][j] += dp[i - 1][j - arr[i]];
        		}
        	}
        }
        
        System.out.println(dp[N][M]);
    }
}