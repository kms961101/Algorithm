import java.util.*;

class Solution {
    public int[] solution(String[] names, int[] yearnings, String[][] photos) {
        ArrayList<Integer> answer = new ArrayList<>();
        HashMap<String, Integer> yearning = new HashMap<>();
        
        for(int i = 0; i < names.length; i++) yearning.put(names[i], yearnings[i]); // 이름별 점수
        for(String[] photo : photos){
            int score = 0;
            for(String name : photo) score += yearning.getOrDefault(name, 0); // 이름 없으면 0점
            answer.add(score);
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
}