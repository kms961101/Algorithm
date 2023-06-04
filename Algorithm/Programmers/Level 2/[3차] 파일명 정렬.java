import java.util.*;

class Solution {
    public static class File implements Comparable<File>{
        String head, number, tail;
        int order;
        
        public File(String head, String number, String tail, int order){
            this.head = head;
            this.number = number;
            this.tail = tail;
            this.order = order;
        }

        @Override
        public int compareTo(File f){
            // 대소문자 상관 없이 사전순
            if(!this.head.toLowerCase().equals(f.head.toLowerCase())) return this.head.toLowerCase().compareTo(f.head.toLowerCase());
            // 숫자 크기 순
            if(Integer.parseInt(this.number) != Integer.parseInt(f.number)) return Integer.parseInt(this.number) - Integer.parseInt(f.number);
            // 나머지는 들어온 순
            return this.order - f.order;
        }
    }
    
    public String[] solution(String[] files) {
        PriorityQueue<File> pq = new PriorityQueue<>();
        int order = 0;
        for(String file : files){
            String head = "";
            String tail = "";
            String number = "";
            boolean isNum = true;
            for(int i = 0; i < file.length(); i++){
                char now = file.charAt(i);
                // 아직 숫자가 안나왔으면 head에 더하기
                if(isNum && !Character.isDigit(now)) head += now;
                // 숫자가 나왔으면 나왔다 체크
                if(isNum && Character.isDigit(now)) isNum = false;
                // 숫자가 시작됬으면 다른 문자 오기전까지 숫자에 더하기
                if(!isNum && Character.isDigit(now)) number += now;
                // 숫자가 시작됬는데 문자가 왔으면 tail만들고 끝내기
                if(!isNum && !Character.isDigit(now)) tail = file.substring(i);
                // tail 생성되면 종료
                if(!tail.equals("")) break;
            }
            pq.add(new File(head, number, tail, order++));
        }
        
        String[] answer = new String[pq.size()];
        int cnt = 0;
        while(!pq.isEmpty()){
            File f = pq.poll();
            answer[cnt++] = f.head + f.number + f.tail;
        }
        return answer;
    }
}