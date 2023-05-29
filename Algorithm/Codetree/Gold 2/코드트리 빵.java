import java.util.*;
import java.io.*;

public class Main {
    static class Node{
        int x, y, goalX, goalY;

        public Node(int x, int y, int goalX, int goalY){
            this.x = x;
            this.y = y;
            this.goalX = goalX;
            this.goalY = goalY;
        }
    }

    static int n, m;
    static int[][] map;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static int[][] dist;
    static ArrayList<Node> cvsList= new ArrayList<>();
    static ArrayList<Node> person = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) cvsList.add(new Node(i, j, -1, -1));
            }
        }
        // 처음 값 버리기
        person.add(new Node(-1, -1, -1, -1));

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            person.add(new Node(-1, -1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1));
        }

        int T = 0;
        while(!isEnd()){
            T++;
            // step 1
            for(int i = 1; i < T && i <= m; i++){
                movePerson(person.get(i));
            }
            // step 2
            for(int i = 1; i < T && i <= m; i++){
                checkArrived(person.get(i));
            }
            // step 3
            if(T <= m){
                init(person.get(T));
            }
        }

        System.out.println(T);

    }

    static boolean isEnd(){
        for(int i = 1; i <= m; i++){
            if(!isArrived(person.get(i))) return false;
        }
        return true;
    }

    static void movePerson(Node node){
        if(isArrived(node)) return;
        // 최단 거리 찾기
        bfs(node.goalX, node.goalY);
        // 4방향 중에 가장 작은 곳으로 가면됨
        int minDist = 100000, minDir = -1;
        for(int i = 0; i < 4; i++){
            int nx = node.x + dx[i];
            int ny = node.y + dy[i];
            if(isIn(nx, ny)) continue;
            if(dist[nx][ny] < minDist){
                minDist = dist[nx][ny];
                minDir = i;
            }
        }

        node.x += dx[minDir];
        node.y += dy[minDir];

    }

    static boolean isArrived(Node node){
        return node.x == node.goalX && node.y == node.goalY;
    }

    static void checkArrived(Node node){
        if(isArrived(node)) map[node.x][node.y] = -1;
    }

    static void init(Node node){
        bfs(node.goalX, node.goalY);

        int minDist = 100000, minX = 0, minY = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(map[i][j] != 1) continue;
                if(dist[i][j] < minDist){
                    minDist = dist[i][j];
                    minX = i;
                    minY = j;
                }
            }
        }

        node.x = minX;
        node.y = minY;
        map[minX][minY] = -1;
    }

    static void bfs(int x, int y){
        // 도착하고 싶은 편의점 기준으로 모든 거리를 측정해준다.
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y, 0, 0));
        dist = new int[n][n];
        for(int i = 0; i < n; i++) Arrays.fill(dist[i], 100000);
        dist[x][y] = 0;

        int minDist = 100000;
        int minDir = -1;
        while(!q.isEmpty()){
            Node now = q.poll();
            for(int i = 0; i < 4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if(isIn(nx, ny) || dist[nx][ny] != 100000 || map[nx][ny] == -1) continue;
                dist[nx][ny] = dist[now.x][now.y] + 1;
                q.add(new Node(nx, ny, now.goalX, now.goalY));
            }
        }

    }

    static boolean isIn(int x, int y){
        if(x < 0 || y < 0 || x >= n || y >= n) return true;
        return false;
    }
}