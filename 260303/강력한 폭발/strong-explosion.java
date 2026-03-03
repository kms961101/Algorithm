import java.util.*;
import java.io.*;
public class Main {
    static class Point{
		int x, y;
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int[][] bombMap, bombType;
	static boolean[][] bombed;
	static int n, max;
	static ArrayList<Point> bombPos = new ArrayList<>();
	static Point[][] bombShapes = {
			{},
            {new Point(-2, 0), new Point(-1, 0), new Point(0, 0), new Point(1, 0), new Point(2, 0)},
            {new Point(-1, 0), new Point(1, 0), new Point(0, 0), new Point(0, -1), new Point(0, 1)},
            {new Point(-1, -1), new Point(-1, 1), new Point(0, 0), new Point(1, -1), new Point(1, 1)}
        };
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		bombMap = new int[n][n];
		bombType = new int[n][n];
		
		for(int i = 0; i < n; i ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				bombMap[i][j] = Integer.parseInt(st.nextToken());
				if(bombMap[i][j] == 1) bombPos.add(new Point(i, j));
			}
		}
		
		findMaxArea(0);
		System.out.println(max);
		
	}
	
	static int calc() {
		// 1. 폭탄 터지는 지역 초기화
		bombed = new boolean[n][n];
		
		// 2. 폭탄에 모양 대로 터지기
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(bombType[i][j] > 0) bomb(i, j, bombType[i][j]);
			}
		}
		
		// 3. 터진 크기 계산
		int cnt = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(bombed[i][j]) cnt++;
			}
		}
		
		return cnt;
	}
	
	static void bomb(int x, int y, int bType) {
		for(int i = 0; i < 5; i++) {
			int nx = x + bombShapes[bType][i].x;
			int ny = y + bombShapes[bType][i].y;
			if(isIn(nx, ny)) bombed[nx][ny] = true;
		}
	}
	
	static void findMaxArea(int idx) {
		if(idx == bombPos.size()) {
			max = Math.max(max, calc());
			return;
		}
		
		for(int i = 1; i <= 3; i++) {
			int x = bombPos.get(idx).x;
			int y = bombPos.get(idx).y;
			bombType[x][y] = i;
			findMaxArea(idx + 1);
			bombType[x][y] = 0;
		}
	}
	
	static boolean isIn(int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < n;
	}
}