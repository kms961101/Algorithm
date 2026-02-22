import java.util.*;
import java.io.*;

public class Main {
    static int MAX_NUM = 100;
	static int[] arr = new int[MAX_NUM];
	static int[] temp = new int[MAX_NUM];
	static int N, M;
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        for(int i = 0; i < N; i++) {
        	arr[i] = Integer.parseInt(br.readLine());
        }
        
        boolean canMove = true;
        while(canMove){
        	canMove = false;
        	for(int startIndex = 0; startIndex < N; startIndex++) {
        		if(arr[startIndex] == 0) continue;
        		// 같은 번호 있는지 찾기
        		int endIndex = findEndIndex(startIndex);
        		// M만큼 같은 수가 있으면 해당 범위 없애기
        		if(endIndex - startIndex + 1 >= M) {
        			makeZero(startIndex, endIndex);
        			canMove = true;
        		}
        	}
        	copyToTemp();
        }
        
        printAnswer();
    }
	
	static int findEndIndex(int startIndex) {
		int endIndex = startIndex + 1;
		for(int i = startIndex; i < N; i++) {
			if(arr[startIndex] == arr[endIndex]) endIndex++;
			else break;;
		}
		return endIndex - 1;
	}
	
	static void makeZero(int startIndex, int endIndex) {
		for(int i = startIndex; i <= endIndex; i++) {
			arr[i] = 0;
		}
	}
	
	static void copyToTemp() {
		int nowIndex = 0;
		for(int i = 0; i < N; i++) {
			if(arr[i] == 0) continue;
			temp[nowIndex++] = arr[i];
		}
		
		arr = temp;
		temp = new int[MAX_NUM];
	}
	
	static void printAnswer() {
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			if(arr[i] == 0) break;
			cnt++;
		}
		
		System.out.println(cnt);
		
		for(int i = 0; i < cnt; i++) {
			System.out.println(arr[i]);
		}
	}
}