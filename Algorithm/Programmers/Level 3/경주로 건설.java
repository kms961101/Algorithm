import java.util.*;

class Solution {
    static class Node{
        int x, y, dir, cost;
        
        Node(int x, int y, int dir, int cost){
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cost = cost;
        }
        
    }
    static boolean[][][] visited;
    static int N, answer = Integer.MAX_VALUE;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public int solution(int[][] board) {
        N = board.length;
        visited = new boolean[N][N][4];
        bfs(board);
        return answer;
    }
    
    static void bfs(int[][] board){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, -1, 0));
        
        while(!q.isEmpty()){
            Node now = q.poll();
            if(now.x == N - 1 && now.y == N - 1){
                answer = Math.min(answer, now.cost);
            }
            for(int i = 0; i < 4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                // 벽이면 패스
                if(isin(nx, ny) || board[nx][ny] == 1) continue;
                // 새로운 메모리 할당 안해주면 같은 값이 변경됨
                int nextCost = now.cost;
                // 처음 움직이거나 같은 방향은 100 증가
                if(now.dir == -1 || now.dir == i) nextCost += 100;
                else nextCost += 600;
                // 가는곳에 현재 방향으로 처음가고, 비용이 내가 더 작으면 갱신
                if(!visited[nx][ny][i] || board[nx][ny] >= nextCost){
                    visited[nx][ny][i] = true;
                    board[nx][ny] = nextCost;
                    q.add(new Node(nx, ny, i, nextCost));
                }
            }
        }
    }
    
    static boolean isin(int x, int y){
        if(x < 0 || y < 0 || x >= N || y >= N) return true;
        return false;
    }
}