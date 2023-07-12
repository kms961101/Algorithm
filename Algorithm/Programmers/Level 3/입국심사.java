import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        quickSort(times, 0, times.length - 1);
        // Arrays.sort(times);
        long start = 1;
        long end = (long)times[times.length - 1] * n;
        
        while(start <= end){
            long sum = 0;
            long mid = (start + end) / 2;
            
            for(int time : times){
                sum += mid / time;
            }
            if(sum >= n){
                end = mid - 1;
                answer = mid;
            }else start = mid + 1;
        }
        return answer;
    }
    
    public static void swap(int[] arr, int pl, int pr){
        int temp = arr[pl];
        arr[pl] = arr[pr];
        arr[pr] = temp;
    } 
    
    public static void quickSort(int[] arr, int left, int right){
        int pl = left;
        int pr = right;
        int pivot = arr[(pl + pr) / 2];
        
        do{
            while(arr[pl] < pivot) pl++;
            while(arr[pr] > pivot) pr--;
            if(pl <= pr) swap(arr, pl++, pr--);
        } while(pl <= pr);
        
        if(left < pr) quickSort(arr, left, pr);
        if(pl < right) quickSort(arr, pl, right);
    }
}