import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static int N, H;
    static int[] up, down;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        up = new int[H + 1];
        down = new int[H + 1];
        for(int i = 1; i <= N; i++){
            int num = Integer.parseInt(br.readLine());
            if(i % 2 == 1) up[num]++;
            else down[num]++;
        }

        for(int i = H - 1; i > 0; i--){
            down[i] += down[i + 1];
            up[i] += up[i + 1];

        }

        int minD = Integer.MAX_VALUE; int cnt = 0;
        for(int i = 1; i < H + 1; i++){
            int brake = up[i] + down[H - i + 1];
            if(brake < minD){
                minD = brake;
                cnt = 1;
            }else if(brake == minD) cnt++;
        }
        System.out.println(minD + " " + cnt);
    }
}