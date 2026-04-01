import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int start = 0;
        int end = Arrays.stream(arr).sum();
        int ans = 0;
        while(start <= end){
            int mid = (start + end) / 2;

            if(findNum(mid)){
                start = mid + 1;
                ans = Math.max(ans, mid);
            }
            else{
                end = mid - 1;
            }
        }

        System.out.println(ans);
    }

    static boolean findNum(int k){
        int sum = 0;
        for(int i = 0; i < n; i++){
            sum += arr[i] / k;
        }

        if(sum >= m) return true;
        return false;
    }
}