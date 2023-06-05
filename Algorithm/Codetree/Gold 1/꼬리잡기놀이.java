import java.util.*;
import java.io.*;

public class Main {
    static class Team{
        int team, num;
        public Team(int team, int num) {
            this.team = team;
            this.num = num;
        }
    }

    static class Pair{
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, m, k, score, teamNum, moveX, moveY, d;
    static boolean isTail;
    static boolean[][] visited;
    static Team[][] map;
    static Pair[] tail, head;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new Team[n][n];
        visited = new boolean[n][n];
        head = new Pair[m];
        tail = new Pair[m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) map[i][j] = new Team(0, Integer.parseInt(st.nextToken()));
        }

        // 각각 팀 번호 넣어주기
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(map[i][j].num == 1) {
                    findTeam(i, j);
                    head[teamNum] = new Pair(i, j);
                    teamNum++;
                }
            }
        }
        int round = -1;
        while(k-- > 0) {
            visited = new boolean[n][n];
            // 모든 팀 이동
            for(int i = 0; i < m; i++){
                isTail = false;
                moveTeam(head[i].x, head[i].y, map[head[i].x][head[i].y].team);
                // 시작이 머리인데 바로 옆이 꼬리일때 옮겨주기
                if(isTail){
                    for(int j = 0; j < 4; j++){
                        int nx = tail[i].x + dx[j];
                        int ny = tail[i].y + dy[j];
                        if(isIn(nx, ny)) continue;
                        if(map[nx][ny].num == 4){
                            map[nx][ny].num = 3;
                            map[nx][ny].team = i;
                            tail[i].x = nx;
                            tail[i].y = ny;
                            break;
                        }
                    }
                }
            }
            // 공 던지기
            Pair catchBall = throwBoll();
            if(catchBall.x == -1 && catchBall.y == -1) continue;
            // 해당 좌표 기준 머리 값이랑 비교 후 점수 증가
            int teamNumber = map[catchBall.x][catchBall.y].team;
            Pair team = head[teamNumber];
            visited = new boolean[n][n];
            // 머리부터 현재 좌표까지 순서 구하기
            count(catchBall.x, catchBall.y, teamNumber);
            // 점수 획득한 팀 머리 꼬리 위치 바꾸기
            head[teamNumber] = tail[teamNumber];
            tail[teamNumber] = team;
            map[head[teamNumber].x][head[teamNumber].y].num = 1;
            map[tail[teamNumber].x][tail[teamNumber].y].num = 3;
        }

        System.out.println(score);

    }

    static void count(int x, int y, int teamNumber){
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x, y));

        int cnt = 1;
        while(!q.isEmpty()){
            int size = q.size();
            while(size-- > 0){
                Pair now = q.poll();
                if(head[teamNumber].x == now.x && head[teamNumber].y == now.y){
                    score += cnt * cnt;
                    return;
                }
                visited[now.x][now.y] = true;
                for(int i = 0; i < 4; i++){
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];
                    // 꼬리인데 바로 옆이 머리이면 안됨
                    if(isIn(nx, ny) || visited[nx][ny] || (map[now.x][now.y].num == 3 && map[nx][ny].num == 1)) continue;
                    if(map[nx][ny].num != 0 && map[nx][ny].num != 4) q.add(new Pair(nx, ny));
                }
            }
            cnt++;
        }
    }

    static void moveTeam(int x, int y, int num) {
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(isIn(nx, ny)) continue;

            // 한칸 이동
            if(map[nx][ny].num == 4 || (map[x][y].num == 1 && map[nx][ny].num == 3)) {
                if(map[nx][ny].num == 3) isTail = true;
                // 헤드 좌표 갱신
                if(x == head[num].x && y == head[num].y) head[num] = new Pair(nx, ny);
                map[nx][ny] = new Team(map[x][y].team, map[x][y].num);
                map[x][y].num = 4;
                map[x][y].team = 0;
                visited[x][y] = true;
                break;
            }
        }
        // 현재 기준 이전 좌표 가져오기
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(isIn(nx, ny) || visited[nx][ny]) continue;
            // 한칸 이동
            if(map[nx][ny].num == 2) moveTeam(nx, ny, num);
            else if(map[nx][ny].num == 3) {
                // 꼬리는 현재칸으로 땡기고
                map[x][y] = new Team(num, 3);
                // 꼬리자리는 경로로 바꾸기
                map[nx][ny] = new Team(0, 4);
                visited[nx][ny] = true;
                tail[num] = new Pair(x, y);
                return;
            }
        }
    }

    static Pair throwBoll() {
        if(d == 0) {
            for(int i = 0; i < n; i++) {
                if(map[moveX][moveY + i].num != 0 && map[moveX][moveY + i].num != 4) {
                    if(++moveX == n) {
                        d = 1;
                    }
                    return new Pair(moveX == n ? --moveX : moveX - 1, moveY + i);
                }
            }
            if(++moveX == n) {
                moveX--;
                d = 1;
            }
        }
        else if(d == 1) {
            for(int i = 0; i < n; i++) {
                if(map[moveX - i][moveY].num != 0 && map[moveX - i][moveY].num != 4) {
                    if(++moveY == n) {
                        d = 2;
                    }
                    return new Pair(moveX - i, moveY == n ? --moveY : moveY - 1);
                }
            }
            if(++moveY == n) {
                moveY--;
                d = 2;
            }
        }
        else if(d == 2) {
            for(int i = 0; i < n; i++) {
                if(map[moveX][moveY - i].num != 0 && map[moveX][moveY - i].num != 4) {
                    if(--moveX == -1) {
                        d = 3;
                    }
                    return new Pair(moveX == -1 ? ++moveX : moveX + 1, moveY - i);
                }
            }
            if(--moveX == -1) {
                moveX++;
                d = 3;
            }
        }
        else {
            for(int i = 0; i < n; i++) {
                if(map[moveX + i][moveY].num != 0 && map[moveX + i][moveY].num != 4) {
                    if(--moveY == -1) {
                        d = 0;
                    }
                    return new Pair(moveX + i, moveY == -1 ? ++moveY : moveY + 1);
                }
            }
            if(--moveY == -1) {
                moveY++;
                d = 0;
            }
        }
        return new Pair(-1, -1);
    }

    static void findTeam(int x, int y) {
        visited[x][y] = true;
        map[x][y].team = teamNum;

        if(map[x][y].num == 3) {
            tail[teamNum] = new Pair(x, y);
            return;
        }

        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(isIn(nx, ny) || visited[nx][ny]) continue;
            if(map[nx][ny].num == 2 || map[nx][ny].num == 3) {
                findTeam(nx, ny);
            }
        }
    }

    static boolean isIn(int x, int y) {
        if(x < 0 || y < 0 || x >= n || y >= n) return true;
        return false;
    }
}