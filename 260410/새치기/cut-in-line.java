import java.io.*;
import java.util.*;

public class Main {
    static class People{
		int num;
		People prev, next;
		
		People(int num){
			this.num = num;
			this.prev = this.next = null;
		}
	}
	static int N, M, Q;
	static People[] peoples;
	static HashMap<Integer, TreeSet<Integer>> lines = new HashMap<>();
	static HashMap<Integer, Integer> numLine = new HashMap<>();
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	Q = Integer.parseInt(st.nextToken());
    	
    	peoples = new People[N + 1];
    	
    	for(int i = 1; i <= N; i++) peoples[i] = new People(i);
    	
    	for(int i = 1; i <= M; i++) {
    		lines.put(i, new TreeSet<>());
    		String[] arr = br.readLine().split(" ");
    		int cnt = Integer.parseInt(arr[0]);
    		if(cnt == -1) continue;
    		
    		for(int j = 1; j < cnt; j++) {
    			int a = Integer.parseInt(arr[j]);
    			int b = Integer.parseInt(arr[j + 1]);
    			connect(peoples[a], peoples[b]);
    			lines.get(i).add(a);
    			numLine.put(a, i);
    		}
    		lines.get(i).add(Integer.parseInt(arr[cnt]));
    		numLine.put(Integer.parseInt(arr[cnt]), i);
    	}
    	
    	for(int i = 0; i < Q; i++) {
    		st = new StringTokenizer(br.readLine());
    		int type = Integer.parseInt(st.nextToken());
    		if(type == 1) {
    			// a가 b앞으로 가기
    			int a = Integer.parseInt(st.nextToken());
    			int b = Integer.parseInt(st.nextToken());
    			
    			People peopleA = peoples[a];
    			People peopleB = peoples[b];
    			
    			// a번 줄 번호를 b번 줄 번호로 변경
    			int aLine = numLine.getOrDefault(a, 0);
    			if(aLine == 0) continue;
    			int bLine = numLine.getOrDefault(b, 0);
    			
    			numLine.put(a, bLine);
    			lines.get(aLine).remove(a);
    			lines.get(bLine).add(a);
    			
    			connect(peopleA.prev, peopleA.next);
    			connect(peopleB.prev, peopleA);
    			connect(peopleA, peopleB);
    			
    		}
    		else if(type == 2) {
    			int a = Integer.parseInt(st.nextToken());
    			int aLine = numLine.get(a);
    			numLine.put(a, 0);
    			lines.get(aLine).remove(a);
    			
    			if(peoples[a].prev != null) peoples[a].prev.next = peoples[a].next;
    			if(peoples[a].next != null) peoples[a].next.prev = peoples[a].prev;
    			peoples[a] = null;
    		}
    		else if(type == 3) {
    			int a = Integer.parseInt(st.nextToken());
    			int b = Integer.parseInt(st.nextToken());
    			int c = Integer.parseInt(st.nextToken());
    			
    			People peopleA = peoples[a];
    			People peopleB = peoples[b]; 
    			People peopleC = peoples[c];
    			//if(peopleA == null || peopleB == null || peopleC == null) continue;
    			int aLine = numLine.get(a);
    			int cLine = numLine.get(c);
    			// 라인 맞춰주기
    			People p = peoples[a];
    			if(aLine != cLine) {
    				while(p != null) {
    					if(p.num == c) break;
    					numLine.put(p.num, cLine);
    					p = p.next;
    				}
    			}
    			
    			connect(peopleA.prev, peopleB.next);
    			connect(peopleC.prev, peopleA);
    			connect(peopleB, peopleC);
    		}
    	}
    	
    	for(int i = 1; i <= M; i++) {
    		if(lines.get(i).isEmpty()){
    			System.out.println(-1);
    			continue;
    		}
    		
    		People people = peoples[lines.get(i).first()];
    		
    		while(people.prev != null) {
    			people = people.prev;
    		}
    		
    		while(people != null) {
    			System.out.print(people.num + " ");
    			people = people.next;
    		}
    		System.out.println();
    	}
    	
    }
    
    
    static void connect(People a, People b) {
    	if(a != null) a.next = b;
    	if(b != null) b.prev = a;
    }
}