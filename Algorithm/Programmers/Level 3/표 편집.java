import java.util.*;

class Solution {
    public static String solution(int n, int k, String[] cmd) {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        int tableSize = n;
        
        for(int i = 0; i < cmd.length; i++){
            String[] split = cmd[i].split(" ");
            if(split[0].equals("U")) k -= Integer.parseInt(split[1]);
            else if(split[0].equals("D")) k += Integer.parseInt(split[1]);
            // 복구는 테이블 크기 증가, 현재위치보다 이전이면 현재위치 증가
            else if(split[0].equals("Z")){
            	int remove = stack.pop();
                tableSize++;
                if(remove <= k) k++;
            // 삭제는 테이블 크기 감소, 현재위치가 마지막이면 하나 줄이기
            }else{
            	stack.add(k);
                tableSize--;
                if(tableSize == k) k--;
            }
        }
        // 테이블 크기만큼 "O" 넣어주기
        for(int i = 0; i < tableSize; i++) sb.append("O");
        // 삭제된 위치에 "X" 넣어주기
        while(!stack.isEmpty()) sb.insert(stack.pop(), "X");
        return sb.toString();
    }
}