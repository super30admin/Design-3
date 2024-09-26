class Solution {
    public int findJudge(int N, int[][] trust) {
        int[] indegree = new int[N];
​
        for (int[] tr : trust) {
            indegree[tr[0] - 1]--;
            indegree[tr[1] - 1]++;
        }
​
        for (int i = 0; i < N; i++) {
​
            if (indegree[i] == N - 1) {
                return i + 1;
            }
        }
​
        return -1;
    }
}
