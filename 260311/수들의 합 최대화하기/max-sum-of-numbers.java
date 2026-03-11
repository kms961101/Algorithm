import java.io.*;
import java.util.*;

public class Main {
    static int n, max;
	static int[][] map;
	static int[] num;
	static boolean[] visited;
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        num = new int[n];
        visited = new boolean[n];
        for(int i = 0; i < n; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	for(int j = 0; j < n; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        find(0, 0);
        System.out.println(max);
    }
	
	static void find(int cnt, int depth) {
		if(cnt == n) {
			int temp = 0;
			for(int i = 0; i < n; i++) temp += map[i][num[i]];
			max = Math.max(max, temp);
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				num[depth] = i;
				find(cnt + 1, depth + 1);
				visited[i] = false;
			}
		}
	}
}