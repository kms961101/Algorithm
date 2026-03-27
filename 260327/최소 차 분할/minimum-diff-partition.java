import java.io.*;
import java.util.*;

public class Main {
    static int n, total, min = 987654321;
	static int[] arr;
	static boolean[] selected;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        selected = new boolean[n + 1];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) arr[i] = Integer.parseInt(st.nextToken());
        total = Arrays.stream(arr).sum();
        powerSet(1);
        System.out.println(min);
    }
    
    static void powerSet(int idx) {
    	if(idx == n) {
    		int left = 0;
    		for(int i = 1; i <= n; i++) {
    			if(selected[i]) left += arr[i];
    		}
    		int right = total - left;
    		min = Math.min(min, Math.abs(right - left));
    		return;
    	}
    	
    	selected[idx] = false;
    	powerSet(idx + 1);
    	
    	selected[idx] = true;
    	powerSet(idx + 1);
    }
}