import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
	static int N, M, D, tempD, totalCnt, max = Integer.MIN_VALUE;
	static int tempN;
	static int tempM;
	static int[][] map;
	static boolean[] visited;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		D = sc.nextInt();
		
		tempN = N;
		tempM = M;
		tempD = D;
		map = new int[N][M];
		visited = new boolean[M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		dfs(0);
		System.out.println(max);
	}
	
	private static void dfs(int cnt) {
		if(cnt == 3) {
			ArrayList<Point> list = new ArrayList<>();
			for (int i = 0; i < M; i++) {
				if(visited[i]) list.add(new Point(N, i));
			}
			int[][] temp = ArrayCopy(map);
			fight(list, temp);
			max = Math.max(max, totalCnt);
			totalCnt = 0;
			tempD = D;
			tempN = N;
			return;
		}
		
		for(int i = 0; i < M; i++) {
			if(!visited[i]) {
				visited[i] = true;
				dfs(cnt + 1);
				visited[i] = false;
			}
		}
		
	}
	
	private static int[][] ArrayCopy(int[][] map) {
		int[][] temp = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				temp[i][j] = map[i][j];
			}
		}
		
		return temp;
		
	}

	private static void fight(ArrayList<Point> list, int[][] temp) {
		if(tempN == 0) return;
		
		ArrayList<enemy> enemy1 = new ArrayList<>();
		ArrayList<enemy> enemy2 = new ArrayList<>();
		ArrayList<enemy> enemy3 = new ArrayList<>();
		HashSet<Integer> set = new HashSet<>();
		for (int n = 0; n < 3; n++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(temp[i][j] != 0 && n == 0 && Math.abs(i - list.get(0).x) + Math.abs(j - list.get(0).y) <= tempD) {
						enemy1.add(new enemy(i, j, Math.abs(i - list.get(0).x) + Math.abs(j - list.get(0).y)));
					}else if(temp[i][j] != 0 && n == 1 && Math.abs(i - list.get(1).x) + Math.abs(j - list.get(1).y) <= tempD) {
						enemy2.add(new enemy(i, j, Math.abs(i - list.get(1).x) + Math.abs(j - list.get(1).y)));
					}else if(temp[i][j] != 0 && n == 2 && Math.abs(i - list.get(2).x) + Math.abs(j - list.get(2).y) <= tempD) {
						enemy3.add(new enemy(i, j, Math.abs(i - list.get(2).x) + Math.abs(j - list.get(2).y)));
					}
				}
			}
		}
		Collections.sort(enemy1);
		Collections.sort(enemy2);
		Collections.sort(enemy3);
		

		if(!enemy1.isEmpty()) {
			set.add(enemy1.get(0).y);
			temp[enemy1.get(0).x][enemy1.get(0).y] = 0;
		}
		if(!enemy2.isEmpty()) {
			set.add(enemy2.get(0).y);
			temp[enemy2.get(0).x][enemy2.get(0).y] = 0;			
		}
		if(!enemy3.isEmpty()) {
			set.add(enemy3.get(0).y);
			temp[enemy3.get(0).x][enemy3.get(0).y] = 0;			
		}
		
		for (int i = 0; i < M; i++) {
			temp[tempN - 1][i] = 0;
		}
		
		totalCnt += set.size();
		tempN--;
		tempD++;
		if(check(temp)) fight(list, temp);
	}
	

	
	private static boolean check(int[][] temp) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(temp[i][j] != 0) return true;
			}
		}
		return false;
	}

	static class enemy implements Comparable<enemy>{
		int x, y, distance;
		
		enemy(int x, int y, int distance){
			this.x = x;
			this.y = y;
			this.distance = distance;
		}

		@Override
		public int compareTo(enemy o) {
			if(this.distance == o.distance) return this.y - o.y;
			else return this.distance - o.distance;
		}
		
	}

	static class Point{
		int x, y;
		
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
}