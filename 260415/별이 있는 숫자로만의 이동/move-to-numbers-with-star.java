import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int N = Integer.parseInt(st.nextToken());
    	int K = Integer.parseInt(st.nextToken());
    	
    	int[][] map = new int[N + 1][N + 1];
    	int[][] prefix = new int[N + 1][N + 1];
    	for(int i = 1; i <= N; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 1; j <= N; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    			prefix[i][j] = prefix[i][j - 1] + map[i][j];
    		}
    	}
    	
    	int max = 0;
    	
    	for(int i = 1; i <= N; i++) {
    		for(int j = 1; j <= N; j++) {
    			int sum = 0;
    			for(int r = i - K; r <= i + K; r++) {
    				int c = K - Math.abs(i - r);
    				
    				if(1 <= r && r <= N) {
    					sum += prefix[r][Math.min(j + c, N)] - prefix[r][Math.max(j - c - 1, 0)];
    				}
    			}
    			max = Math.max(max, sum);
    		}
    	}
    	
    	System.out.println(max);
    	
    }
}