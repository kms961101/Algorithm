import java.io.*;
import java.util.*;

public class Main {
    static class Node{
		int x, y, cnt;
		
		Node(int x, int y, int cnt){
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	static int N, K;
	static int[][] map, ans;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static boolean[][][] visited;
	static Node start, end;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new boolean[K + 1][N][N];
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 0; j < N; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        st = new StringTokenizer(br.readLine());
        start = new Node(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0);
        st = new StringTokenizer(br.readLine());
        end = new Node(Integer.parseInt(st.nextToken()) - 1,Integer.parseInt(st.nextToken()) - 1, 0);
        
        Queue<Node> q = new LinkedList<>();
        q.add(start);
        int cnt = 1;
        while(!q.isEmpty()) {
        	int size = q.size();
        	while(size-- > 0) {
        		Node now = q.poll();
        		visited[now.cnt][now.x][now.y] = true;
        		for(int i = 0; i < 4; i++) {
        			int nx = now.x + dx[i];
        			int ny = now.y + dy[i];
        			// 범위 벗어나면 패스
        			if(!isIn(nx, ny)) continue;
        			if(nx == end.x && ny == end.y) {
        				System.out.println(cnt);
        				System.exit(0);
        			}
        			// 벽이 아니고 방문한적 없으면 이동
        			if(map[nx][ny] == 0 && !visited[now.cnt][nx][ny]) {
        				q.add(new Node(nx, ny, now.cnt));
        			}
        			// 벽이지만 벽을 부셔서 방문한적 없으면 이동
        			else if(map[nx][ny] == 1 && now.cnt + 1 <= K && !visited[now.cnt + 1][nx][ny]) {
        				q.add(new Node(nx, ny, now.cnt + 1));
        			}
        		}
        	}
        	cnt++;
        }
        
        System.out.println(-1);
	}
	
	static boolean isIn(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}
}