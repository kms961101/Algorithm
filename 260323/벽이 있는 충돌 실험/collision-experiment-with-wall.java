import java.io.*;
import java.util.*;

public class Main {
    static class Node{
        int x, y, dir;

        Node(int x, int y, int dir){
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    static int T, N, M;
    static ArrayList<Node> list, copy;
    static int[][] visited;
    // 왼 위 오 아
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            list = new ArrayList<>();
            visited = new int[N][N];

            for(int i = 0; i < M; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()) - 1;
                int y = Integer.parseInt(st.nextToken()) - 1;
                char dir = st.nextToken().charAt(0);
                list.add(new Node(x, y, findDir(dir)));
            }
            
            int cnt = 0;
            while(cnt++ < N * 2 + 2) {
            	visited = new int[N][N];
            	copy = new ArrayList<>();
            	for(int i = 0; i < list.size(); i++) {
            		Node now = list.get(i);
            		int nx = now.x + dx[now.dir];
            		int ny = now.y + dy[now.dir];
            		if(!isIn(nx, ny)) {
            			copy.add(new Node(now.x, now.y, changeDir(now.dir)));
            			visited[now.x][now.y]++;
            		}
            		else {
            			copy.add(new Node(nx, ny, now.dir));
            			visited[nx][ny]++;
            		}
            	}
            	isCrushed();
            	list = copy;
            }
            
            System.out.println(list.size());
            

        }
    }
    
    static void isCrushed() {
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < N; j++) {
    			if(visited[i][j] < 2) continue;
    			for(int k = 0; k < copy.size(); k++) {
    				Node now = copy.get(k);
    				if(now.x == i && now.y == j) {
    					copy.remove(k);
    					k--;
    				}
    			}
    		}
    	}
    }
    
    static int findDir(char dir) {
    	if(dir == 'L') return 0;
    	if(dir == 'U') return 1;
    	if(dir == 'R') return 2;
    	return 3;
    }
    
    static boolean isIn(int x, int y) {
    	return 0 <= x && x < N && 0 <= y && y < N;
    }
    
    static int changeDir(int dir) {
    	return (dir + 2) % 4;
    }
}