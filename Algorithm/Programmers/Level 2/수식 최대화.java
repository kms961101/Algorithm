import java.util.*;

class Solution {
    static int[] pq;
    static String[] operator;
    static boolean[] visited;
    static ArrayList<String> list;
    static Long answer;
    public long solution(String expression) {
        operator = new String[] {"*", "+", "-"};
        list = new ArrayList<>();
        answer = 0L;
        int start = 0;
        // 리스트에 숫자, 연산자 나눠서 넣기
        for(int i = 0; i < expression.length(); i++){
            if(expression.charAt(i) == '*' || expression.charAt(i) == '+' || expression.charAt(i) == '-'){
                list.add(expression.substring(start, i));
                list.add(expression.charAt(i) + "");
                start = i + 1;
            }
        }
        // 마지막 넣기
        list.add(expression.substring(start));
        pq = new int[3];
        visited = new boolean[3];
        // 순열로 경우의수 구하기
        dfs(expression, 0);
        return answer;
    }
    
    static void dfs(String s, int cnt){
        if(cnt == 3){
            calculate();
            return;
        }
        
        for(int i = 0; i < 3; i++){
            if(visited[i]) continue;
            visited[i] = true;
            pq[cnt] = i;
            dfs(s, cnt +1);
            visited[i] = false;
        }
    }
    
    static void calculate(){
        ArrayList<String> copy = new ArrayList<>(list);
        // 우선순위에 따른 계산진행
        for(int j = 0; j < 3; j++) {
        	for(int i = 0; i < copy.size(); i++){
                if(copy.get(i).equals(operator[pq[j]])){
                    // 앞뒤 숫자와 연산자를 계산해서 연산자 앞에값 바꾸고 뒤에 두개 지우기
                    copy.set(i - 1, calc(copy.get(i - 1), copy.get(i), copy.get(i + 1)));
                    copy.remove(i);
                    copy.remove(i);
                    i--;
                }
            }
        }
        
        answer = Math.max(answer, Math.abs(Long.parseLong(copy.get(0))));
    }
    
    static String calc(String num1, String op, String num2){
        long n1 = Long.parseLong(num1);
        long n2 = Long.parseLong(num2);
        
        if(op.equals("+")) return n1 + n2 + "";
        else if(op.equals("-")) return n1 - n2 + "";
        else return n1 * n2 + "";
        
    }
}