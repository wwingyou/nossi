import java.util.*;

public class Solution {
    public int solve(int n, int[] cars, int[][] links) {
        int[] carsSum = new int[n+1];
        for (int i = 0; i < n; i++) {
            carsSum[i+1] = cars[i];
        }
        //✅ 주어진 간선을 통해 그래프를 생성한다.
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] link : links) {
            graph.putIfAbsent(link[0], new ArrayList<>());
            graph.putIfAbsent(link[1], new ArrayList<>());
            graph.get(link[0]).add(link[1]);
            graph.get(link[1]).add(link[0]);
        }

        //✅ 스택에 루트 노드를 넣고 dfs를 진행한다.
        boolean[] visited = new boolean[n+1];
        visited[1] = true;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        while (!stack.isEmpty()) {
            //✅ 스택에 추가하려는 노드가 부모 노드인 경우 제거한다.
            if (!graph.get(stack.peek()).isEmpty() && visited[graph.get(stack.peek()).get(0)]) {
                graph.get(stack.peek()).remove(0);
            }
            //✅ 자식 노드가 있는 경우 스택에 다음 노드를 추가한다.
            if (!graph.get(stack.peek()).isEmpty()) {
                int temp = graph.get(stack.peek()).remove(0);
                visited[temp] = true;
                stack.push(temp);
            //✅ 자식 노드가 없는 경우 해당 노드의 노드값을 부모 노드에 추가한다.
            } else {
                int temp = stack.pop();
                if (!stack.isEmpty()) {
                    carsSum[stack.peek()] += carsSum[temp];
                }
            }
        }
        //✅ 저장된 노드 값들을 통해 정답을 찾는다.
        double target = carsSum[1] / 2.0;
        double minDiff = target;
        for (int carsPerNode : carsSum) {
            minDiff = Math.min(minDiff, Math.abs(target - carsPerNode));
        }
        return (int)(minDiff * 2);
    }
}
