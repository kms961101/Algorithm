import java.util.*;

class Solution {
    public static boolean[] visited;
    public static ArrayList<String> list;
    
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        visited = new boolean[tickets.length];
        list = new ArrayList<>();
        dfs("ICN", "ICN", tickets, 0);
        
        Collections.sort(list);
        return list.get(0).split(" ");
    }
    
    public static void dfs(String start, String way, String[][] tickets, int cnt){
        if(cnt == tickets.length){
            list.add(way);
            return;
        }
        
        for(int i = 0; i < tickets.length; i++){
            if(visited[i] || !tickets[i][0].equals(start)) continue;
            visited[i] = true;
            dfs(tickets[i][1], way + " " + tickets[i][1], tickets, cnt + 1);
            visited[i] = false;
        }
        return;
    }
}