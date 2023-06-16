import java.util.*;
import java.io.*;


public class Main
{
    static int M, N, K;
    static String[] key;
    static boolean anwer = false;
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        key = new String[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            key[i] = st.nextToken();
        }
        String[] butten = br.readLine().split(" ");
        find(butten);
        System.out.println(anwer ? "secret" : "normal");
    }

    static void find(String[] butten){
        int idx = 0;
        for(int i = 0; i < N; i++){
            if(butten[i].equals(key[idx])){
                boolean flag = false;
                while(idx != M - 1){
                    if(!butten[++i].equals(key[++idx])) flag = true;
                    if(flag) break;
                }
                // key랑 똑같다면 멈추기
                if(!flag){
                    anwer = true;
                    return;
                }else{
                    i--;
                    idx = 0;
                }
            }
        }
    }
}