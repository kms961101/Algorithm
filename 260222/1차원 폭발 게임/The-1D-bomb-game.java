import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Integer> list = new ArrayList<>();
        
        for(int i = 0; i < N; i++){
            list.add(Integer.parseInt(br.readLine()));
        }

        boolean flag = true;
        if(M == 1) {
        	System.out.println(0);
        	System.exit(0);
        }
        while(flag){
            ArrayList<Integer> copy = new ArrayList<>();
            int cnt = 1;
            int num = list.get(0);
            flag = false;
            for(int i = 1; i < list.size(); i++){
                int now = list.get(i);
                if(num == now) cnt++; // 같은 번호면 카운트 증가
                else if(cnt < M){ // 이전 번호랑 다르고, 수량이 M보다 작으면 
                    for(int j = 0; j < cnt; j++) copy.add(num); // 카운트 만큼 다시 리스트에 저장
                    num = now; // 이전 번호 현재로 갱신
                    cnt = 1;
                }
                else {
                	flag = true; // 한번이라도 터지면 다시 확인 필요
                	num = now;
                    cnt = 1;
                }
            }
            if(list.size() >= 2 && list.get(list.size() - 2) != num) copy.add(num);
            if(list.size() < M) flag = false;
            else list = copy;
        }

        System.out.println(list.size());
        for(int i = 0; i < list.size(); i++) {
        	System.out.println(list.get(i));
        }
    }
}