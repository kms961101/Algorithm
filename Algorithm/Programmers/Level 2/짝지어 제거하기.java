import java.util.*;

class Solution
{
    public int solution(String s)
    {
        String[] string = s.split("");
        Stack<String> st = new Stack<>();
        
        for(int i = 0; i < string.length; i++){
            if(st.isEmpty()) st.push(string[i]);
            else if(st.peek().equals(string[i])) st.pop();
            else st.push(string[i]);        
        }
        
        return st.isEmpty() ? 1 : 0;
    }
}