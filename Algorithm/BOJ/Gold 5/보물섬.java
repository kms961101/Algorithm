import java.util.*;
import java.io.*;

public class Main{
    static class Pair{
        int x, y;

        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int R, C, max;
    static char[][] map;
    static int[][] dist;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for(int i = 0; i < R; i++){
            String line = br.readLine();
            for(int j = 0; j < C; j++) map[i][j] = line.charAt(j);
        }

        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(map[i][j] == 'W') continue;
                bfs(new Pair(i, j));
                check(i, j);
            }
        }

        System.out.println(max);
    }

    static void bfs(Pair p){
        Queue<Pair> q = new LinkedList<>();
        q.add(p);
        dist = new int[R][C];
        for(int i = 0; i < R; i++) Arrays.fill(dist[i], -1);
        dist[p.x][p.y] = 0;

        while(!q.isEmpty()){
            Pair now = q.poll();
            for(int i = 0; i < 4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if(!isIn(nx, ny) || map[nx][ny] == 'W' || dist[nx][ny] != -1) continue;
                dist[nx][ny] = dist[now.x][now.y] + 1;
                q.add(new Pair(nx, ny));
            }
        }
    }

    static void check(int x, int y){
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                max = Math.max(max, dist[i][j]);
            }
        }
    }

    static boolean isIn(int x, int y){
        return 0 <= x && x < R && 0 <= y && y < C;
    }
}