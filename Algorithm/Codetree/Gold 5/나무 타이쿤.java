import java.util.*;
import java.io.*;

public class Main {
    static class Tree{
        int x;
        int y;

        public Tree(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int n, m, d, p, answer;
    static int[][] map, move;
    static boolean[][] visited;
    //  → ↗ ↑ ↖ ← ↙ ↓ ↘
    static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {0, 1, 1, 0, -1, -1, -1, 0, 1};
    static Queue<Tree> tree = new LinkedList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++)map[i][j] = Integer.parseInt(st.nextToken());
        }
        move = new int[m][2];

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            move[i][0] = Integer.parseInt(st.nextToken());
            move[i][1] = Integer.parseInt(st.nextToken());

        }

        // 처음 영양제 위치
        tree.add(new Tree(n - 2, 0));
        tree.add(new Tree(n - 2, 1));
        tree.add(new Tree(n - 1, 0));
        tree.add(new Tree(n - 1, 1));
        int year = 0;
        while(year++ < m){
            d = move[year - 1][0];
            p = move[year - 1][1];
            visited = new boolean[n][n];
            // 이동 후 나무 1증가
            move();
            // 대각선 4방향 0보다 크면 더해주기
            add(new LinkedList<>(tree));
            // 2보다 큰 나무 자르고 영양제 위치로 넣어주기
            cutAndChange();
        }

        score();
        System.out.println(answer);
    }

    static void score(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(map[i][j] != 0) answer += map[i][j];
            }
        }
    }

    static void cutAndChange(){
        tree = new LinkedList<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(visited[i][j] || map[i][j] < 2) continue;
                map[i][j] -= 2;
                tree.add(new Tree(i, j));
            }
        }
    }

    static void add(Queue<Tree> temp){
        while(!temp.isEmpty()){
            Tree t = temp.poll();
            visited[t.x][t.y] = true;
            for(int i = 2; i <= 8; i += 2){
                int x = t.x + dx[i];
                int y = t.y + dy[i];
                if(isIn(x, y) || map[x][y] == 0) continue;
                map[t.x][t.y]++;
            }
        }
    }

    static void move(){
        int size = tree.size();
        while(size-- > 0){
            Tree t = tree.poll();
            // n크기를 넘어가면 다시 처음으로 가기 때문에 음수 방지를 위해 n 더하고 n 으로 나눈 나머지가 현재 위치
            t.x = (t.x + dx[d] * p + n) % n;
            t.y = (t.y + dy[d] * p + n) % n;
            tree.add(t);
            map[t.x][t.y]++;
        }
    }

    static boolean isIn(int x, int y){
        if(x < 0 || y < 0 || x >= n || y >= n) return true;
        return false;
    }
}