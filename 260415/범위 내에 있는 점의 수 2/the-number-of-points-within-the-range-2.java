import java.io.*;
import java.util.*;

public class Main {
    final static int MAX_N = 1000001;
	static int N, Q;
	static int[] arr = new int[MAX_N];
	static int[] prefix = new int[MAX_N];
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	Q = Integer.parseInt(st.nextToken());
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < N; i++) {
    		int idx = Integer.parseInt(st.nextToken());
    		arr[idx] = 1;
    	}
    	
    	for(int i = 1; i < MAX_N; i++) prefix[i] = prefix[i - 1] + arr[i];
    	
    	for(int i = 0; i < Q; i++) {
    		st = new StringTokenizer(br.readLine());
    		int A = Integer.parseInt(st.nextToken());
    		int B = Integer.parseInt(st.nextToken());
    		if(A != 0) System.out.println(prefix[B] - prefix[A - 1]);
    		else System.out.println(prefix[B]);
    	}
    }
}