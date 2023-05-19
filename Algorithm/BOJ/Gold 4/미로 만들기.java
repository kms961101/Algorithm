import java.util.*;
import java.io.*;

public class Main{
    public static class Miro{
        int x;
        int y;
        
        public Miro(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    static int N;
    static int[][] map;
    static int[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new int[N][N];

        for(int i = 0; i < N; i++){
            String[] s = br.readLine().split("");
            Arrays.fill(visited[i], Integer.MAX_VALUE);
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(s[j]);
            }
        }

        bfs();
        System.out.println(visited[N - 1][N - 1]);
    }
    
    static void bfs(){
        Queue<Miro> q = new LinkedList<>();
        q.add(new Miro(0, 0));
        visited[0][0] = 0;
        while(!q.isEmpty()){
            Miro miro = q.poll();
            for(int i = 0; i < 4; i++){
                int nx = miro.x + dx[i];
                int ny = miro.y + dy[i];
                if(isIn(nx, ny)) continue;
                // 흰방 && 거리가 더 짧을때
                if(map[nx][ny] == 1 && visited[nx][ny] > visited[miro.x][miro.y]){
                    visited[nx][ny] = visited[miro.x][miro.y];
                    q.add(new Miro(nx, ny));
                // 검은방 && 더 적게 방문할때
                }else if(map[nx][ny] == 0 && visited[nx][ny] > visited[miro.x][miro.y] + 1){
                    visited[nx][ny] = visited[miro.x][miro.y] + 1;
                    q.add(new Miro(nx, ny));
                }
            }
        }
    }
    
    static boolean isIn(int x, int y){
        if(x < 0 || y < 0 || x >= N || y >= N) return true;
        return false;
    }
}