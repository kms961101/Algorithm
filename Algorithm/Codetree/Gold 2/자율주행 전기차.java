import java.util.*;
import java.io.*;

public class Main {
	static class Pair{
		int x, y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static class Move implements Comparable<Move>{
		int x, y, cnt, idx;
		
		public Move(int x, int y, int cnt, int idx) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.idx = idx;
		}
		
		@Override
		public int compareTo(Move m) {
			if(m.cnt != this.cnt) return this.cnt - m.cnt;
			if(m.x != this.x) return this.x - m.x;
			return this.y - m.y;
		}

		@Override
		public String toString() {
			return "Move [idx=" + idx + "]";
		}
		
		
	}
	
	static class Passenger{
		int xs, ys, xe, ye;
		
		public Passenger(int xs, int ys, int xe, int ye) {
			this.xs = xs;
			this.ys = ys;
			this.xe = xe;
			this.ye = ye;
		}
	}
	static Pair taxi;
	static int n, m, c, answer = -1;
	static boolean[] movedPassenger;
	static int[][] map, dist;
	static boolean[][] visited;
	static ArrayList<Passenger> passenger = new ArrayList<>();
	static int[] dx = {-1, 0, 0, 1};
	static int[] dy = {0, -1, 1, 0};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		movedPassenger = new boolean[m];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int taxiX = Integer.parseInt(st.nextToken()) - 1;
		int taxiY = Integer.parseInt(st.nextToken()) - 1;
		taxi = new Pair(taxiX, taxiY);
		
		int cnt = 0;
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int xs = Integer.parseInt(st.nextToken()) - 1;
			int ys = Integer.parseInt(st.nextToken()) - 1;
			int xe = Integer.parseInt(st.nextToken()) - 1;
			int ye = Integer.parseInt(st.nextToken()) - 1;
			passenger.add(new Passenger(xs, ys, xe, ye));
		}
		
		while(true) {
			if(find()) break;       // 태울 손님 찾고 이동
			if(isFinished()) break; // 끝났는지 체크
		}
		
		System.out.println(answer);
	}
	
	static boolean find() {
		// 태울 손님 찾기
		bfs(taxi.x, taxi.y);
		
		// 우선순위 높은 승객 찾기
		PriorityQueue<Move> pq = new PriorityQueue<>();
		for(int i = 0; i < m; i++) {
			Passenger p = passenger.get(i);
			if(visited[p.xs][p.ys] && !movedPassenger[i]) pq.add(new Move(p.xs, p.ys, dist[p.xs][p.ys], i));
		}
		
		Move m = pq.peek();
		// 갈 수 있는지 체크
		if(pq.size() == 0 || dist[m.x][m.y] > c) return true;
		taxi.x = m.x; taxi.y = m.y; c -= dist[m.x][m.y];
		
		// 목적지 이동
		bfs(taxi.x, taxi.y);
		Passenger p = passenger.get(m.idx);
		// 못가면 실패
		if(!visited[p.xe][p.ye] || dist[p.xe][p.ye] > c) return true;
		taxi = new Pair(p.xe, p.ye); c += dist[p.xe][p.ye]; movedPassenger[m.idx] = true;
 		return false;
		
	}
	
	static void bfs(int x, int y) {
		Queue<Pair> q = new LinkedList<>();
		dist = new int[n][n];
		visited = new boolean[n][n];
		
		q.add(new Pair(x, y));
		visited[x][y] = true;
		while(!q.isEmpty()) {
			Pair now = q.poll();
			for(int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if(isIn(nx, ny) && !visited[nx][ny] && map[nx][ny] == 0) {
					dist[nx][ny] = dist[now.x][now.y] + 1;
					visited[nx][ny] = true;
					q.add(new Pair(nx, ny));
					
				}
			}
		}
	}
	
	static boolean isFinished() {
		for(int i = 0; i < m; i++) {
			if(!movedPassenger[i]) return false;
		}
		answer = c;
		return true;
	}
	
	static boolean isIn(int x, int y) {
		return 0 <= x && x < n && 0<= y && y < n;
	}
}
