import java.util.*;
import java.io.*;

public class Main{
    static int N;
    static int[][] ladder, maxLadder, minLadder;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        ladder = new int[N][3];
        maxLadder = new int[N][3];
        minLadder = new int[N][3];
        int min = Integer.MAX_VALUE;
        int max = 0;

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) ladder[i][j] = Integer.parseInt(st.nextToken());
            Arrays.fill(minLadder[i], Integer.MAX_VALUE);
        }

        if(N == 1){
            for(int i = 0; i < 3; i++){
                min = Math.min(min, ladder[0][i]);
                max = Math.max(max, ladder[0][i]);
            }
            System.out.println(max + " " + min);
            System.exit(0);
        }

        for(int i = 0; i < N - 1; i++){
            for(int j = 0; j < 3; j++){
                if(i == 0){
                    maxLadder[i][j] = minLadder[i][j] = ladder[i][j];
                }
                makeLadder(i, j);
            }
        }

        for(int i = 0; i < 3; i++){
            min = Math.min(minLadder[N - 1][i], min);
            max = Math.max(maxLadder[N - 1][i], max);
        }

        System.out.println(max + " " + min);
    }

    static void makeLadder(int x, int y){
        if(y != 2){       // 0
            maxLadder[x + 1][0] = Math.max(maxLadder[x + 1][0], maxLadder[x][y] + ladder[x + 1][0]);
            minLadder[x + 1][0] = Math.min(minLadder[x + 1][0], minLadder[x][y] + ladder[x + 1][0]);
        }
        if(y != 0){       // 2
            maxLadder[x + 1][2] = Math.max(maxLadder[x + 1][2], maxLadder[x][y] + ladder[x + 1][2]);
            minLadder[x + 1][2] = Math.min(minLadder[x + 1][2], minLadder[x][y] + ladder[x + 1][2]);
        }                 // 1
        maxLadder[x + 1][1] = Math.max(maxLadder[x + 1][1], maxLadder[x][y] + ladder[x + 1][1]);
        minLadder[x + 1][1] = Math.min(minLadder[x + 1][1], minLadder[x][y] + ladder[x + 1][1]);
    }
}