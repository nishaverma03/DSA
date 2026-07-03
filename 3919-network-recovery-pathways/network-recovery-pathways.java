import java.util.*;

class Solution {
    static class Edge {
        int to, cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;

        List<Edge>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] indegree = new int[n];
        TreeSet<Integer> costs = new TreeSet<>();

        for (int[] e : edges) {
            graph[e[0]].add(new Edge(e[1], e[2]));
            indegree[e[1]]++;
            costs.add(e[2]);
        }

        int[] topo = new int[n];
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int index = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            topo[index++] = node;

            for (Edge edge : graph[node]) {
                if (--indegree[edge.to] == 0) {
                    queue.offer(edge.to);
                }
            }
        }

        if (!canReach(0, graph, online, topo, k)) {
            return -1;
        }

        List<Integer> values = new ArrayList<>(costs);

        int left = 0, right = values.size() - 1;
        int answer = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int score = values.get(mid);

            if (canReach(score, graph, online, topo, k)) {
                answer = score;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    private boolean canReach(int minScore, List<Edge>[] graph,
                             boolean[] online, int[] topo, long k) {
        int n = graph.length;
        long INF = Long.MAX_VALUE / 2;

        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[0] = 0;

        for (int node : topo) {
            if (!online[node] || dist[node] == INF) {
                continue;
            }

            for (Edge edge : graph[node]) {
                if (edge.cost < minScore || !online[edge.to]) {
                    continue;
                }

                long newCost = dist[node] + edge.cost;

                if (newCost < dist[edge.to] && newCost <= k) {
                    dist[edge.to] = newCost;
                }
            }
        }

        return dist[n - 1] <= k;
    }
}