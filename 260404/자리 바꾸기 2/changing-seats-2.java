import java.io.*;
import java.util.*;

public class Main {
    static class Order{
		int a, b;
		
		Order(int a, int b){
			this.a = a;
			this.b = b;
		}
	}
	static int[] arr;
	static Order[] orders;
	static HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        orders = new Order[M];
        arr = new int[N + 1];
        for(int i = 1; i <= N; i++) {
        	HashSet<Integer> set = new HashSet<>();
        	set.add(i);
        	map.put(i, set);
        	arr[i] = i;
        }
        
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	orders[i] = new Order(a, b);
        }
        int count = 3;
        while(count-- > 0) {
        	for(int i = 0; i < M; i++) {
        		Order order = orders[i];
        		// 해당 번호가 움직이는 자리 적어주기
        		map.get(arr[order.a]).add(order.b);
        		map.get(arr[order.b]).add(order.a);
        		// 자리 이동
        		int temp = arr[order.a];
        		arr[order.a] = arr[order.b];
        		arr[order.b] = temp;
        	}
        }
        
        for(int i = 1; i <= N; i++) {
        	System.out.println(map.get(i).size());
        }
    }
}