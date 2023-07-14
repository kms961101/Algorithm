import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {0, 0};
        PriorityQueue<Integer> descQueue = new PriorityQueue<>();
        PriorityQueue<Integer> ascQueue = new PriorityQueue<>(Collections.reverseOrder());
        
        for(String operation : operations){
            String cmd = operation.split(" ")[0];
            String data = operation.split(" ")[1];

            if(cmd.equals("I")){
                descQueue.add(Integer.parseInt(data));
                ascQueue.add(Integer.parseInt(data));
            }else {
                if(data.equals("-1") && descQueue.size() >= 1){
                    Integer poll = descQueue.poll();
                    ascQueue.remove(poll);
                }else if(data.equals("1") && ascQueue.size() >= 1){
                    Integer poll = ascQueue.poll();
                    descQueue.remove((poll));
                }
            }
        }
        return ascQueue.size() == 0 ? answer : new int[] {ascQueue.poll(), descQueue.poll()};
    }
}