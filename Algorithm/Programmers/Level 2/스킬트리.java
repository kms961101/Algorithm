import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        // 스킬 순서 map에 넣기
        for(int i = 1; i <= skill.length(); i++) map.put(skill.charAt(i - 1), i);
        outer:for(String sk : skill_trees){
            int idx = 1;
            // 순서 있는 스킬이고 순서가 맞는지 체크
            for(int i = 0; i < sk.length(); i++){
                int order = map.getOrDefault(sk.charAt(i), -1);
                if(order != -1 && order != idx) continue outer;
                else if(order != -1 && order == idx) idx++;
            }
            answer++;
        }
        return answer;
    }
}