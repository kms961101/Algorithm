import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
		int idx, num;
		
		Node(int idx, int num){
			this.idx = idx;
			this.num = num;
		}
		
		@Override
		public int compareTo(Node n) {
			return this.num - n.num;
		}
	}
	
	static class Person{
		int idx, arrive, stay;
		
		Person(int idx, int arrive, int stay){
			this.idx = idx;
			this.arrive = arrive;
			this.stay = stay;
		}
	}
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	HashMap<Integer, Person> persons = new HashMap<>();
    	TreeSet<Node> arrives = new TreeSet<>();
    	TreeSet<Node> stays = new TreeSet<>();
    	
    	for(int i = 1; i <= N; i++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		int arrive = Integer.parseInt(st.nextToken());
    		int stay = Integer.parseInt(st.nextToken());
    		persons.put(i, new Person(i, arrive, stay));
    		arrives.add(new Node(i, arrive));
    		stays.add(new Node(i, arrive + stay));
    	}
    	
    	int time = 0;
    	int ans = 0;
    	for(int i = 0; i < N; i++) {
    		Person arrive = persons.get(arrives.first().idx);
    		Person stay = persons.get(stays.first().idx);
    		
    		Person remove = null;
    		
    		if(arrive.arrive > time && stay.arrive <= time) remove = stay;
    		else if(stay.arrive > time && arrive.arrive <= time) remove = arrive;
    		else if(arrive.idx == stay.idx) remove = arrive;
    		else if(arrive.arrive + arrive.stay == stay.arrive + stay.stay) {
    			if(arrive.idx < stay.idx) remove = arrive;
    			else remove = stay;
    		}
    		else if(arrive.arrive + arrive.stay < stay.arrive + stay.stay) remove = arrive;
			else remove = stay;
    		
    		int diff = time - remove.arrive;
			if(diff > 0) ans = Math.max(ans, diff);
			time = Math.max(time + remove.stay, remove.arrive + remove.stay);
			
			arrives.remove(new Node(remove.idx, remove.arrive));
			stays.remove(new Node(remove.idx, remove.arrive + remove.stay));
    	}
    	System.out.println(ans);
    }
}