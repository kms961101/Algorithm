import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        int answer = 0;
        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);

        for (int i = 0; i < costs.length; i++) {
            int[] cost = costs[i];
            int root1 = find(parent, cost[0]);
            int root2 = find(parent, cost[1]);

            if (root1 != root2) {
                answer += cost[2];
                parent[root2] = root1;
            }
        }

        return answer;
    }

    public int find(int[] parent, int x) {
        if (parent[x] == -1) {
            return x;
        }

        return parent[x] = find(parent, parent[x]);
    }
}
