class Solution {
    public int solution(int number, int limit, int power) {
        int answer = 1;
        int[] arr = new int[number + 1];
        
        for(int i = 2; i <= number; i++){ // 2번 기사부터 시작
            for(int j = 1; j <= Math.sqrt(i); j++){
                if(i % j != 0) continue;
                if(j * j == i) arr[i]++;
                else arr[i] += 2;
            }
        }
        
        for(int pw : arr) answer += pw > limit ? power : pw;
        return answer;
    }
}