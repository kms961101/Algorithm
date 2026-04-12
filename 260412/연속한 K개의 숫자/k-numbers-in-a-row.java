import java.io.*;
import java.util.*;

public class Main {
    static int N, K, B;
	static boolean[] isNum;
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	K = Integer.parseInt(st.nextToken());
    	B = Integer.parseInt(st.nextToken());
    	
    	int[] arrA = new int[N + 1];
    	int[] arrb = new int[N + 1];
    	int[] sumA = new int[N + 1];
    	int[] sumB = new int[N + 1];
    	int[] sum = new int[N + 1];
    	isNum = new boolean[N + 1];
    	
    	HashMap<Integer, Integer> count = new HashMap<>();
    	for(int i = 0; i < B; i++) {
    		int num = Integer.parseInt(br.readLine());
    		isNum[num] = true;
    		arrA[num] = num;
    		count.put(num, 1);
    	}
    	
    	for(int i = 1; i <= N; i++) {
    		if(!isNum[i]) {
    			arrb[i] = i;
    		}
    	}
    	
    	int prev = 0;
    	int cnt = 0;
    	int min = Integer.MAX_VALUE;
    	for(int i = 1; i <= N; i++) {
    		sumA[i] = sumA[i - 1] + arrA[i];
    		sumB[i] = sumB[i - 1] + arrb[i];
    		sum[i] = sum[i - 1] + i;
    		if(prev != sumA[i]) {
    			prev = sumA[i];
    			cnt++;
    			if(count.containsKey(sumA[i])) {
    				count.put(sumA[i], Math.min(cnt, count.get(sumA[i])));
    			}
    			else
    				count.put(sumA[i], cnt);
    		}
    	}
    	
    	for(int i = 1; i < N - K; i++) {
    		int a = sum[i + K - 1] - sum[i - 1];
    		int b = sumB[i + K - 1] - sumB[i - 1];
    		int diff = a - b;
    		if(!count.containsKey(diff)) continue;
    		min = Math.min(min, count.get(diff));
    	}
    	
    	System.out.println(min);
    }
}