import java.util.*;
import java.io.*;

public class Main {
    static class Player{
        int x, y, d, s, gun, id;

        public Player(int x, int y, int d, int s, int gun, int id){
            this.x = x;
            this.y = y;
            this.d = d;
            this.s = s;
            this.gun = gun;
            this.id =  id;
        }
    }
    static int n, m, k;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[] score;
    static int[][] isInPlayer;
    static PriorityQueue<Integer>[][] map;
    static ArrayList<Player> player = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new PriorityQueue[n][n];
        score = new int[m];
        isInPlayer = new int[n][n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                map[i][j] = new PriorityQueue<>(Collections.reverseOrder());
                map[i][j].add(Integer.parseInt(st.nextToken()));
            }
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            isInPlayer[x][y]++;
            player.add(new Player(x, y, d, s, 0, i));
        }

        while(k-- > 0){
            // 플레이어 순서대로 한명씩 진행
            for(int i = 0; i < m; i++){
                Player p = player.get(i);
                // 이동
                movePlayer(p);

                // 이동한 곳에 플레이어가 있으면 싸운다
                if(isInPlayer[p.x][p.y] > 1) fight(p);
                else changeGun(p);
            }
        }

        for(int i = 0; i < m - 1; i++){
            System.out.print(score[i] + " ");
        }
        System.out.print(score[m - 1]);
    }

    static void movePlayer(Player p){
        isInPlayer[p.x][p.y]--;
        int nx = p.x + dx[p.d];
        int ny = p.y + dy[p.d];
        // 범위 넘어가면 방향 바꿔서 이동
        if(isIn(nx, ny)){
            changeDir(p);
            nx = p.x + dx[p.d];
            ny = p.y + dy[p.d];
        }
        isInPlayer[nx][ny]++;
        p.x = nx;
        p.y = ny;

    }

    static void changeGun(Player p){
        int nowGun = map[p.x][p.y].peek();
        if(p.gun == 0 && nowGun == 0) return;
        if(p.gun < nowGun){
            map[p.x][p.y].add(p.gun);
            p.gun = nowGun;
            map[p.x][p.y].poll();
        }

    }

    static void fight(Player p){
        // 같은 위치 플레이어 찾기
        Player fightPlayer = null;
        for(int i = 0; i < m; i++){
            if(p.id == i) continue;
            Player next = player.get(i);
            if(p.x == next.x && p.y == next.y){
                fightPlayer = next;
                break;
            }
        }

        Player winPlayer = p;
        Player losePlayer = fightPlayer;


        // 능력치 + 총
        if(p.gun + p.s < fightPlayer.gun + fightPlayer.s){
            score[fightPlayer.id] += (fightPlayer.gun + fightPlayer.s) - (p.gun + p.s);
            winPlayer = fightPlayer;
            losePlayer = p;
        }
        else if(p.gun + p.s > fightPlayer.gun + fightPlayer.s) score[p.id] += (p.gun + p.s) - (fightPlayer.gun + fightPlayer.s);
        else{
            // 능력치 순으로
            if(p.s > fightPlayer.s) score[p.id] += (p.gun + p.s) - (fightPlayer.gun + fightPlayer.s);
            else {
                score[fightPlayer.id] += (fightPlayer.gun + fightPlayer.s) - (p.gun + p.s);
                winPlayer = fightPlayer;
                losePlayer = p;
            }
        }


        // 진사람
        map[losePlayer.x][losePlayer.y].add(losePlayer.gun);
        losePlayer.gun = 0;
        isInPlayer[losePlayer.x][losePlayer.y]--;
        int moveX = 0, moveY = 0;
        for(int i = 0; i < 4; i++){
            moveX = losePlayer.x + dx[losePlayer.d];
            moveY = losePlayer.y + dy[losePlayer.d];
            if(isIn(moveX, moveY) || isInPlayer[moveX][moveY] > 0) losePlayer.d = (losePlayer.d + 1) % 4;
            else break;
        }
        isInPlayer[moveX][moveY]++;
        losePlayer.x = moveX;
        losePlayer.y = moveY;
        changeGun(losePlayer);

        // 이긴사람

        changeGun(winPlayer);

    }

    static boolean isIn(int x, int y){
        if(x < 0 || y < 0 || x >= n || y >= n) return true;
        return false;
    }

    static void changeDir(Player p){
        if(p.d == 0) p.d = 2;
        else if(p.d == 1) p.d = 3;
        else if(p.d == 2) p.d = 0;
        else p.d = 1;
    }
}