import java.util.*;

class Solution {
    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int cnt = 0;
        
        for(int num : score){
            pq.add(num);
            if(pq.size() > k) pq.poll();
            answer[cnt++] = pq.peek();
        }
        return answer;
    }
}