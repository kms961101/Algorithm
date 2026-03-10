import java.util.*;
import java.io.*;

public class Main {
    static int N, min = Integer.MAX_VALUE;;
	static int[] arr;
	static boolean[] visited;
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N * 2];
        visited = new boolean[N * 2];
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for(int i = 0; i < N * 2; i++) arr[i] = Integer.parseInt(st.nextToken());
        
        comb(0, N);
        System.out.println(min);
    }
	
	static void comb(int start, int cnt) {
		if(cnt == 0) {
			int left = 0;
			int right = 0;
			for(int i = 0; i < N * 2; i++) {
				if(visited[i]) left += arr[i];
				else right += arr[i];
			}
			min = Math.min(min, Math.abs(left - right));
			return;
		}
		
		for(int i = start; i < N * 2; i++) {
			visited[i] = true;
			comb(i + 1, cnt - 1);
			visited[i] = false;
		}
	}
}