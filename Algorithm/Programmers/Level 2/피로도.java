import java.util.*;

class Solution {
    static boolean[] visited;
    static int max = 0;
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[8];
        dfs(0, k, dungeons);
        return max;
    }
    
    public void dfs(int cnt, int k, int[][] dungeons){
        for(int i = 0; i < dungeons.length; i++){
            if(!visited[i] && dungeons[i][0] <= k){
                visited[i] = true;
                dfs(cnt + 1, k - dungeons[i][1], dungeons);
                visited[i] = false;
            }
        }
        
        max = Math.max(max, cnt);
    }
}