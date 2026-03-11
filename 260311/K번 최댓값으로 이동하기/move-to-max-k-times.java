import java.util.*;
import java.io.*;

public class Main {
    static class Node implements Comparable<Node>{
		int x, y, num;
		
		Node(int x, int y, int num){
			this.x = x;
			this.y = y;
			this.num = num;
		}
		
		@Override
		public int compareTo(Node n) {
			if(this.num != n.num) return n.num - this.num;
			if(this.x != n.x) return this.x - n.x;
			return this.y - n.y;
		}
		
	}
	static int N, K;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        
        
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 0; j < N; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken()) - 1;
        int y = Integer.parseInt(st.nextToken()) - 1;
        
        
        for(int k = 0; k < K; k++) {
        	PriorityQueue<Node> pq = new PriorityQueue<>();
        	Queue<Node> q = new LinkedList<>();
        	q.add(new Node(x, y, map[x][y]));
        	visited = new boolean[N][N];
        	visited[x][y] = true;
        	int num = map[x][y];
        	while(!q.isEmpty()) {
        		Node now = q.poll();
        		for(int i = 0; i < 4; i++) {
        			int nx = now.x + dx[i];
        			int ny = now.y + dy[i];
        			if(isIn(nx, ny) && map[nx][ny] < num && !visited[nx][ny]) {
        				pq.add(new Node(nx, ny, map[nx][ny]));
        				q.add(new Node(nx, ny, map[nx][ny]));
        				visited[nx][ny] = true;
        			}
        		}
        	}
        	
        	if(pq.size() == 0) break;
        	Node next = pq.poll();
        	x = next.x;
        	y = next.y;
        }
        
        System.out.println(++x + " " + ++y);
    }
	
	static boolean isIn(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}
}