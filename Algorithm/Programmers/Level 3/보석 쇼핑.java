import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Set<String> set = new HashSet<>();
        // set사용 중복제거
        for(String gem : gems) set.add(gem);
        int start = 1;
        int len = gems.length;
        int x = 1;
        HashMap<String, Integer> map = new HashMap<>();
        Queue<String> q = new LinkedList<>();
        for(int i = 0; i < gems.length; i++){
            // 갯수 넣어주기
            map.put(gems[i], map.getOrDefault(gems[i], 0) + 1);
            q.add(gems[i]);
            
            while(true){
                String temp = q.peek();
                // 중복된거 있으면 시작점 옮겨주기
                if(map.get(temp) > 1){
                    x++;
                    q.poll();
                    map.put(temp, map.get(temp) - 1);
                }else break;
            }
            
            if(map.size() == set.size()){
                // 가장 작은거 찾기위해 길이 줄이면서 갱신
                if(len > q.size()){
                    len = q.size();
                    start = x;
                }
            }
        }
        return new int[] {start, start + len - 1};
    }
}