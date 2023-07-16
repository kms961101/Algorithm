import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        int cnt = 0;
        boolean[] visited = new boolean[n];
        for(int i = 0; i < computers.length; i++){
            if(visited[i]) continue;
            answer++;
            cnt++;
            visited[i] = true;
            Queue<Integer> q = new LinkedList<>();
            for(int j = 0; j < computers.length; j++){
                if(j == i) continue;
                if(computers[i][j] == 1 && !visited[j]){
                    q.add(j);
                    visited[j] = true;
                    cnt++;
                }
            }
            
            while(!q.isEmpty()){
                Integer num = q.poll();
                for(int j = 0; j < computers.length; j++){
                    if(computers[num][j] == 1 && !visited[j]){
                        q.add(j);
                        visited[j] = true;
                        cnt++;
                    }
                }
            }
            if(cnt == n) return answer;
        }
        return answer;
    }
}