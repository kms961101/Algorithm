import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        
        HashSet<Integer> lostSet = new HashSet<>();
        HashSet<Integer> reserveSet = new HashSet<>();
        
        for(int num : reserve){
            reserveSet.add(num);
        }
        for(int num : lost){
            if(reserveSet.contains(num)) reserveSet.remove(num);
            else lostSet.add(num);
        }
        
        for(Integer num : lostSet){
            if(reserveSet.contains(num - 1)){
                reserveSet.remove(num - 1);
                answer++;
            }else if(reserveSet.contains(num + 1)){
                reserveSet.remove(num + 1);
                answer++;
            }
        }
        
        return n - lostSet.size() + answer;
    }
}