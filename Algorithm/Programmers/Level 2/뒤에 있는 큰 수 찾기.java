import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Stack<Integer> st = new Stack<>();
        
        outer:for(int i = numbers.length - 1; i >= 0; i--){
            if(st.size() != 0){
                while(st.size() > 0){
                    int now = st.peek();
                    if(now > numbers[i]){
                        answer[i] = now;
                        st.push(numbers[i]);
                        continue outer;
                    }
                    st.pop();
                }
            }
            
            answer[i] = -1;
            st.push(numbers[i]);
            
        }
        return answer;
    }
}