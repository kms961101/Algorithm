class Solution {
    public int solution(int n) {
        int answer = 0;
        int cnt = 1;
        int[] prefix = new int[n + 1];
        for(int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + cnt++;
        
        outer:for(int i = 1; i <= n; i++){
            for(int j = i; j <= n; j++){
                int sum = prefix[j] - prefix[i - 1];
                if(sum > n) continue outer;
                if(sum == n) answer++;
            }
        }
        
        return answer;
    }
}