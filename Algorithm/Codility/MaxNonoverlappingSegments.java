class Solution {
    public int solution(int[] A, int[] B) {
        int start = 0;
        int cnt = 1;

        if(A.length <= 1) return A.length;

        for(int i = 0; i < A.length; i++){
            if(B[start] < A[i]){
                cnt++;
                start = i;
            }
        }

        return cnt;
    }
}