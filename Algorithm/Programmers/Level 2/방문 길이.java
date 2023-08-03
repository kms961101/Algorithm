class Solution {
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    boolean[][] map;
    int x;
    int y;
    int answer;
    public int solution(String dirs) {
        answer = 0;
        
        map = new boolean[11 * 2 - 1][11 * 2 - 1];
        x = 10;
        y = 10;
        
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                if(i % 2 == 0 && j % 2 == 0) map[i][j] = true;
            }
        }
        
        for(int i = 0; i < dirs.length(); i++) {
            move(dirs.charAt(i));
        }
        
        return answer;
    }
    
    public void move(char dir) {
        char[] d = {'U', 'R', 'D', 'L'};
        for(int i = 0; i < 4; i++) {
            if(d[i] == dir) {
                for(int j = 0; j < 2; j++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if(nx >= 0 && nx < map.length && ny >= 0 && ny < map.length) {
                        if(!map[nx][ny]) answer++;
                        map[nx][ny] = true;
                        x = nx;
                        y = ny;
                    }
                }
                break;
            }
        }
    }
}