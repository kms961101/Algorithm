import java.util.*;

class Solution {
    static public int solution(int k, int n, int[][] reqs) {
        int[][] delayTables = new int[k][];
        for(int i = 0; i < k; i++){
            delayTables[i] = getTypeDelays(i + 1, n - k + 1, reqs);
        }
        
        int answer = minDelay(delayTables, 0, n);
        return answer;
    }
    
    static int[] getTypeDelays(int type, int max, int[][] reqs){ // 상담 유형, 최대 상담사 수
        int[] delays = new int[max];
        for(int i = 0; i < max; i++){
            int count = i + 1;
            int delay = 0;
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            
            for(int[] req : reqs){
                if(req[2] != type) continue;
                int time = req[0];
                if(pq.size() == count){
                    int endTime = pq.poll();
                    time = Math.max(time, endTime);
                    delay += time - req[0];
                }
                pq.add(time + req[1]);
            }
            delays[i] = delay;
        }
        
        return delays;
    }
    
    static int minDelay(int[][] delayTables, int typeIdx, int remain){
        if(delayTables.length == typeIdx) return 0;
        
        int delay = Integer.MAX_VALUE;
        int maxCount = remain - (delayTables.length - typeIdx) + 1;
        for(int i = 0; i < maxCount; i++){
            delay = Math.min(delay, delayTables[typeIdx][i] + 
                            minDelay(delayTables, typeIdx + 1, remain - i - 1)); // 각 유형별 최소 기다린 시간
        }
        
        return delay;
    }
}