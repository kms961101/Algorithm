import java.io.*;
import java.util.*;

public class Main{
public static final int MAX_N = 100000;
    
    // 변수 선언
    public static int n;
    
    public static long[] arr = new long[MAX_N];
    public static long[] L = new long[MAX_N];
    public static long[] R = new long[MAX_N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력: 
        n = sc.nextInt();
        for(int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        // 총 합을 계산합니다.
        long totalSum = 0;
        for(int i = 0; i < n; i++)
            totalSum += arr[i];
        
        // 총 합이 4로 나누어 떨어지지 않으면 답은 0입니다.
        if(totalSum % 4 != 0) {
            System.out.print(0);
            System.exit(0);
        }

        // 각 구간 내 합이 되어야 할 targetSum
        // 값을 설정합니다.
        long targetSum = totalSum / 4;

        // L 배열을 채워줍니다.
        // L[i] = 0번부터 i번까지 합이 targetSum인 구간을 
        //        정확히 2개 만들 수 있는 서로 다른 가지수
        L[0] = 0;
        long sum = arr[0]; // 지금까지의 합
        long cnt = (sum == targetSum) ? 1 : 0; // 합이 정확히 targetSum이 되었던 횟수
        for(int i = 1; i < n; i++) {
            sum += arr[i];
            // 합이 2 * targetSum이 되면
            // 정확히 2개의 구간을 만들 수 있는 가능성이 있으므로
            // cnt값을 기록해줍니다.
            if(sum == 2 * targetSum)
                L[i] = cnt;
            
            // 합이 targetSum인 경우
            // cnt 값을 갱신해줍니다.
            if(sum == targetSum)
                cnt++;
        }
        
        // R 배열을 채워줍니다.
        // R[i] = i번부터 n - 1번까지 합이 targetSum인 구간을 
        //        정확히 2개 만들 수 있는 서로 다른 가지수
        R[n - 1] = 0;
        sum = arr[n - 1]; // 지금까지의 합
        cnt = (sum == targetSum) ? 1 : 0; // 합이 정확히 targetSum이 되었던 횟수
        for(int i = n - 2; i >= 0; i--) {
            sum += arr[i];
            // 합이 2 * targetSum이 되면
            // 정확히 2개의 구간을 만들 수 있는 가능성이 있으므로
            // cnt값을 기록해줍니다.
            if(sum == 2 * targetSum)
                R[i] = cnt;
            
            // 합이 targetSum인 경우
            // cnt 값을 갱신해줍니다.
            if(sum == targetSum)
                cnt++;
        }
        
        // 각 위치 i에 대해
        // [0, i] 까지는 합이 targetSum인 구간을 정확히 2개를 만들고
        // [i + 1, n] 까지도 합이 targetSum인 구간을 정확히 2개를 만든다고 했을 때 
        // 가능한 가지수를 세줍니다.
        long ans = 0;
        for(int i = 1; i < n - 1; i++)
            ans += (long) L[i] * R[i + 1];
        System.out.print(ans);
    }
}