import java.util.*;

class Solution {
    static class Node{
        int x, y;
        
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    static int[] dx = {0, 0, 1, 1};
    static int[] dy = {0, 1, 0, 1};
    static char[][] map;
    static ArrayList<Node> block;
    static int answer;
    public int solution(int m, int n, String[] board) {
        answer = 0;
        map = new char[m][n];
        
        for(int i = 0; i < m; i++){
            String s = board[i];
            for(int j = 0; j < n; j++) map[i][j] = s.charAt(j);
        }
        
        while(true){
            block = new ArrayList<>();
            
            for(int i = 0; i < m - 1; i++){
                for(int j = 0; j < n - 1; j++){
                    if(map[i][j] == '*') continue;                  // 빈 블럭 패스
                    if(isBlock(i, j)) block.add(new Node(i, j));    // 4개 블럭인지 체크
                }
            }
            
            if(block.size() == 0) break; // 블럭이 없다면 끝내기
            getScore();                  // 찾은 블럭 점수 얻기
            down(n, m);                  // 빈 블럭 내리기
        }
        
        return answer;
    }
    
    static boolean isBlock(int x, int y){ // 4개 블럭인지 체크
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(map[x][y] != map[nx][ny]) return false;
        }
        return true;
    }
    
    static void getScore(){
        for(Node n : block){
            for(int i = 0; i < 4; i++){
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];
                if(map[nx][ny] == '*') continue;
                map[nx][ny] = '*';
                answer++;
            }
        }
    }
    
    static void down(int n, int m){
        for(int i = 0; i < n; i++){
            char[] copy = new char[m];
            Arrays.fill(copy, '*');
            int idx = 0;
            
            for(int j = m - 1; j >= 0; j--) if(map[j][i] != '*') copy[idx++] = map[j][i];
            for(int j = m - 1; j >= 0; j--) map[j][i] = copy[m - j - 1];

        }
    }
}