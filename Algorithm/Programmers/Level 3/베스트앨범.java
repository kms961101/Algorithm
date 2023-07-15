import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        ArrayList<Integer> answer = new ArrayList<>();
        HashMap<String, Integer> numOfGenres = new HashMap<>();
        HashMap<String, HashMap<Integer, Integer>> music = new HashMap<>();
        
        for(int i = 0; i < genres.length; i++){
            if(!numOfGenres.containsKey(genres[i])){
                numOfGenres.put(genres[i], plays[i]);
                HashMap<Integer, Integer> map = new HashMap<>();
                map.put(i, plays[i]);
                music.put(genres[i], map);
            }else{
                numOfGenres.put(genres[i], numOfGenres.get(genres[i]) + plays[i]);
                music.get(genres[i]).put(i, plays[i]);
            }
        }
        
        ArrayList<String> nogOfKeys = new ArrayList(numOfGenres.keySet());
        Collections.sort(nogOfKeys, (s1, s2) -> numOfGenres.get(s2) - numOfGenres.get(s1));
        
        for(String key : nogOfKeys){
            HashMap<Integer, Integer> map = music.get(key);
            ArrayList<Integer> genreOfKey = new ArrayList(map.keySet());
            Collections.sort(genreOfKey, (s1, s2) -> map.get(s2) - map.get(s1));
            
            answer.add(genreOfKey.get(0));
            if(genreOfKey.size() > 1) answer.add(genreOfKey.get(1));
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}