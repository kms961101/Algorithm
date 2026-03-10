import java.util.*;
import java.io.*;

public class Main {
    static int N, M, Q, r1, c1, r2, c2;
	static int[][] map, copy;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        copy = new int[N][M];
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 0; j < M; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        		copy[i][j] = map[i][j];
        	}
        }
        
        
        
        while(Q-- > 0) {
        	st = new StringTokenizer(br.readLine());
            r1 = Integer.parseInt(st.nextToken()) - 1;
            c1 = Integer.parseInt(st.nextToken()) - 1;
            r2 = Integer.parseInt(st.nextToken()) - 1;
            c2 = Integer.parseInt(st.nextToken()) - 1;
        	
        	wind();
        	copyMap();
        	average();
        	copyMap();
        }
        
        for(int i = 0; i < N; i++) {
        	for(int j = 0; j < M - 1; j++) {
        		System.out.print(map[i][j] + " ");
        	}
        	System.out.print(map[i][M - 1]);
        	System.out.println();
        }
    }
	
	static void wind() {
		for(int i = c2; i > c1; i--) copy[r1][i] = map[r1][i - 1];
		for(int i = r2; i > r1; i--) copy[i][c2] = map[i - 1][c2];
		for(int i = c1; i < c2; i++) copy[r2][i] = map[r2][i + 1];
		for(int i = r1; i < r2; i++) copy[i][c1] = map[i +1][c1];
	}
	
	static void average() {
		for(int i = r1; i <= r2; i++) {
			for(int j = c1; j <= c2; j++) {
				int num = map[i][j];
				int cnt = 1;
				for(int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k]; 
					if(!isIn(nx, ny)) continue;
					num += map[nx][ny];
					cnt++;
				}
				copy[i][j] = num / cnt;
			}
		}
	}
	
	static boolean isIn(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
	}
	
	static void copyMap() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = copy[i][j];
			}
		}
	}
}