import java.io.*;
import java.util.*;

public class Main {
    static class Node{
		int x, y;
		
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	static int N, H, M;
	static int[][] map, ans;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static Node[] peoples;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        ans = new int[N][N];
        peoples = new Node[H];
        int idx = 0;
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 0; j < N; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        		if(map[i][j] == 2) {
        			peoples[idx++] = new Node(i, j);
        			ans[i][j] = -1;
        		}
        	}
        }
        
        for(int i = 0; i < H; i++) {
        	boolean[][] visited = new boolean[N][N];
        	Queue<Node> q = new LinkedList<>();
        	q.add(peoples[i]);
        	int cnt = 1;
        	while(!q.isEmpty()) {
        		int size = q.size();
        		while(size-- > 0) {
        			Node people = q.poll();
        			visited[people.x][people.y] = true;
        			for(int j = 0; j < 4; j++) {
        				int nx = people.x + dx[j];
        				int ny = people.y + dy[j];
        				// 범위 밖, 방문한 곳, 벽이면 패스
        				if(!isIn(nx, ny) || visited[nx][ny] || map[nx][ny] == 1) continue;
        				if(map[nx][ny] == 3) {
        					ans[peoples[i].x][peoples[i].y] = cnt;
        					q.clear();
        					size = 0;
        					break;
        				}
        				q.add(new Node(nx, ny));
        			}
        		}
        		cnt++;
        	}
        }
        
        for(int i = 0; i < N; i++) {
        	for(int j = 0; j < N; j++) {
        		System.out.print(ans[i][j] + " ");
        	}
        	System.out.println();
        }
	}
	
	static boolean isIn(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}
}