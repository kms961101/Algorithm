class Solution {
    static int answer;
    public int solution(int[] numbers, int target) {
        dfs(numbers, 0, target, 0);
        return answer;
    }
    
    public void dfs(int[] numbers, int idx, int target, int num){
        if(idx == numbers.length){
            if(num == target) answer++;
            return;
        }
        dfs(numbers, idx + 1, target, num + numbers[idx]);
        dfs(numbers, idx + 1, target, num - numbers[idx]);
    }
}