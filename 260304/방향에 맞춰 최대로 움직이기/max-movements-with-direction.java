import java.util.*;
import java.io.*;

public class Main {
    static class Point{
		int x, y;
		
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	static int n;
	static int[][] number, dir;
	static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 0, 1, 1, 1, 0, -1, -1, -1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		number = new int[n][n];
		dir = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				number[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				dir[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken()) - 1;
		int y = Integer.parseInt(st.nextToken()) - 1;
		
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		int answer = 0;
		while(!q.isEmpty()) {
			boolean flag = false;
			int cnt =  q.size();
			while(cnt-- > 0) {
				Point now = q.poll();
				int d = dir[now.x][now.y];
				int nx = now.x + dx[d];
				int ny = now.y + dy[d];
				while(isIn(nx, ny)) {
					if(number[nx][ny] > number[now.x][now.y]) {
						q.add(new Point(nx, ny));
						flag = true;
					}
					nx += dx[d];
					ny += dy[d];
				}
			}
			if(flag) answer++;
		}
		
		System.out.println(answer);
	}
	
	static boolean isIn(int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < n;
	}
}