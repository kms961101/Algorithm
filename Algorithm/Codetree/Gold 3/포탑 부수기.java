import java.util.*;
import java.io.*;

public class Main {
    static class Pair{
        int x, y;

        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int N, M, K;
    static int[][] map, lastAtack, backX, backY;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1, -1, 1, -1, 1};
    static int[] dy = {1, 0, -1, 0, -1, -1, 1, 1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        lastAtack = new int[N][M];
        backX = new int[N][M];
        backY = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        int time = 1;
        while(K-- > 0){
            // 포탑이 1개만 남으면 종료
            if(isFinished()) break;
            // 공격자 정하기
            Pair attacker = selectAttacker();
            // 타겟 정하기
            Pair target = selectTarget();
            // 공격자 핸디캡 적용
            map[attacker.x][attacker.y] += N + M;
            // 최근 포탑으로 갱신
            lastAtack[attacker.x][attacker.y] = time++;
            if(tryRaser(attacker, target)) bomb(attacker, target);
            visited[attacker.x][attacker.y] = true;
            // 포탑 정비
            fix();
        }
        int max = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++) max = Math.max(max, map[i][j]);
        }
        System.out.println(max);
    }

    static boolean isFinished(){
        int cnt = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++) if(map[i][j] != 0) cnt++;
        }
        return cnt == 1 ? true : false;
    }

    static Pair selectAttacker(){
        int minP = Integer.MAX_VALUE, minX = -1, minY = -1, maxT = -1;
        for(int sum = M + N - 2; sum >= 0; sum--){
            for(int j = M - 1; j >= 0; j--){
                int i = sum - j;
                if(i < 0 || i >= N || map[i][j] == 0) continue;
                if(minP > map[i][j]){
                    minP = map[i][j];
                    minX = i;
                    minY = j;
                    maxT = lastAtack[i][j];
                }else if(minP == map[i][j] && maxT < lastAtack[i][j]){
                    minP = map[i][j];
                    minX = i;
                    minY = j;
                    maxT = lastAtack[i][j];
                }
            }
        }
        return new Pair(minX, minY);
    }

    static Pair selectTarget(){
        int maxP = 0, maxX = -1, maxY = -1, minT = Integer.MAX_VALUE;
        for(int sum = 0; sum <= M + N - 2; sum++){
            for(int j = 0; j < M; j++){
                int i = sum - j;
                if(i < 0 || i >= N || map[i][j] == 0) continue;
                if(maxP < map[i][j]){
                    maxP = map[i][j];
                    maxX = i;
                    maxY = j;
                    minT = lastAtack[i][j];
                }else if(maxP == map[i][j] && minT > lastAtack[i][j]){
                    maxP = map[i][j];
                    maxX = i;
                    maxY = j;
                    minT = lastAtack[i][j];
                }
            }
        }
        return new Pair(maxX, maxY);
    }

    static boolean tryRaser(Pair attacker, Pair target){
        Queue<Pair> q = new LinkedList<>();
        visited = new boolean[N][M];
        q.add(attacker);
        visited[attacker.x][attacker.y] = true;
        boolean flag = true;
        // 이전 값 저장해 놓고 찾기
        while(!q.isEmpty()){
            Pair now = q.poll();
            for(int i = 0; i < 4; i++){
                int nx = (now.x + dx[i] + N) % N;
                int ny = (now.y + dy[i] + M) % M;
                if(map[nx][ny] == 0 || visited[nx][ny]) continue;
                backX[nx][ny] = now.x;
                backY[nx][ny] = now.y;
                visited[nx][ny] = true;
                if(nx == target.x && ny == target.y) flag = false;
                q.add(new Pair(nx, ny));
            }
            if(!flag) break;
        }
        // 타겟까지 못가면 bomb 실행
        if(flag) return flag;

        // 갈 수 있다면
        visited = new boolean[N][M];


        int goX = target.x;
        int goY = target.y;

        while(goX != attacker.x || goY != attacker.y){
            int power = map[attacker.x][attacker.y] / 2;
            if(goX == target.x && goY == target.y) power = map[attacker.x][attacker.y];
            attack(goX, goY, power);
            int tempX = goX;
            goX = backX[goX][goY];
            goY = backY[tempX][goY];
        }

        return false;
    }

    static void bomb(Pair attacker, Pair target){
        visited = new boolean[N][M];
        for(int i = 0; i < 8; i++){
            int nx = (target.x + dx[i] + N) % N;
            int ny = (target.y + dy[i] + M) % M;
            if(map[nx][ny] == 0) continue;
            if(nx == attacker.x && ny == attacker.y) continue;
            attack(nx, ny, map[attacker.x][attacker.y] / 2);
        }
        attack(target.x, target.y, map[attacker.x][attacker.y]);
    }

    static void fix(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(visited[i][j] || map[i][j] == 0) continue;
                map[i][j]++;
            }
        }
    }

    static void attack(int x, int y, int power){
        visited[x][y] = true;
        map[x][y] = Math.max(0, map[x][y] - power);
    }
}