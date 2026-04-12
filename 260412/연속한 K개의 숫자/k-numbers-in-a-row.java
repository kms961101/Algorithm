import java.io.*;
import java.util.*;

public class Main {
    static int N, K, B;
	static int[] prefixSum, arr;
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	K = Integer.parseInt(st.nextToken());
    	B = Integer.parseInt(st.nextToken());
    	
    	arr = new int[N  + 1];
    	prefixSum = new int[N + 1];
    	
    	for(int i = 0; i < B; i++) {
    		int num = Integer.parseInt(br.readLine());
    		arr[num] = 1;
    	}
    	
    	for(int i = 1; i <= N; i++) {
    		prefixSum[i] = prefixSum[i - 1] + arr[i];
    	}
    	
    	int min = Integer.MAX_VALUE;
    	for(int i = 1; i < N - K + 1; i++)
    		min = Math.min(min, prefixSum[i + K - 1] - prefixSum[i]);
    	
    	System.out.println(min);
    }
}