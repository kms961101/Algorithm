import java.io.*;
import java.util.*;


public class Main {
    static final int MAX_N = 500;
	static final int MAX_M = 250;
	static final int STATE = 2;
	static final int MIN_ANS = -500000;
	
	static final int NOT_BELONG = 0;
	static final int BELONG = 1;
	
	static int N, M;
	static int[][][] dp = new int[MAX_N + 1][MAX_M + 1][STATE];
	static int[] a = new int[MAX_N + 1];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) a[i] = Integer.parseInt(st.nextToken());
        
        for(int i = 0; i <= N; i++)
        	for(int j = 0; j <= M; j++) dp[i][j][BELONG] = dp[i][j][NOT_BELONG] = MIN_ANS;
        
        for(int i = 0; i <= N; i++) dp[i][0][NOT_BELONG] = 0;
        
        for(int i = 1; i <= N; i++) {
        	for(int j = 1; j <= M; j++) {
        		// 1. i번쨰를 구간으로 선택하는 경우
        		// 1.1 i번쨰를 이전 구간에 포함 시키는 경우
        		// dp[i - 1][j][BELONG] + a[i]
        		// 1.2 i번째를 새로운 구간으로 정하는 경우
        		// dp[i - 1][j - 1][NOT_BELONG] + a[i]
        		// 둘중 큰값으로 선택
        		dp[i][j][BELONG] = Math.max(dp[i - 1][j][BELONG] + a[i], dp[i - 1][j - 1][NOT_BELONG] + a[i]);
        		// 2. i번쨰를 구간으로 선택 안하는 경우는 둘중 큰값
        		dp[i][j][NOT_BELONG] = Math.max(dp[i - 1][j][BELONG], dp[i - 1][j][NOT_BELONG]);
        	}
        }
        
        System.out.println(Math.max(dp[N][M][BELONG], dp[N][M][NOT_BELONG]));
    }
}