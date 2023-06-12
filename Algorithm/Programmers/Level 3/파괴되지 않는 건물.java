import java.util.*;

class Solution {
    static int N, M;
    static int[][] prifixSum;
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        // 가로, 세로 길이
        N = board.length;
        M = board[0].length;
        // 누적합 담아 놓을 배열
        prifixSum = new int[N + 1][M + 1];
        
        for(int i = 0; i < skill.length; i++){
            attack(skill[i][0] == 1 ? true : false, skill[i][1], skill[i][2], skill[i][3], skill[i][4], skill[i][5]);
        }
        
        // 가로 누적합
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                prifixSum[i][j + 1] += prifixSum[i][j];
            }
        }
        
        // 세로 누적합
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                prifixSum[j + 1][i] += prifixSum[j][i];
            }
        }
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                int num = board[i][j] + prifixSum[i][j];
                if(num > 0) answer++;
            }
        }
        return answer;
    }
    
    static void attack(boolean isAttack, int r1, int c1, int r2, int c2, int degree){
        int num = isAttack == true ? -degree : degree;
        prifixSum[r1][c1] += num;
        prifixSum[r1][c2 + 1] += -num;
        prifixSum[r2 + 1][c1] += -num;
        prifixSum[r2 + 1][c2 + 1] += num;
    }
}