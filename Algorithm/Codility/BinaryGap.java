import java.util.*;

class Solution {
    public int solution(int N) {
        String binaryToString = Integer.toBinaryString(N);
        int answer = 0;
        int cnt = 0;

        for(int i = 0; i < binaryToString.length(); i++){
            if(binaryToString.charAt(i) == '0') cnt++;
            else{
                answer = Math.max(answer, cnt);
                cnt = 0;
            }
        }

        return answer;
    }
}