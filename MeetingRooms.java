// TC: O(1) for put and get
//Total TC: O(n log n)
// SC: O(capacity)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class Solution {
    public int minMeetingRooms(int[][] intervals) {
        // base
        if(intervals == null || intervals.length == 0)
            return 0;
        int room = 0, n = intervals.length, i = 0;
        int[] start = new int[n];
        int[] end = new int[n];
        for(int[] interval: intervals) {
            start[i] = interval[0];
            end[i] = interval[1];
            i++;
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int a = 0, b = 0, count = 0;
        for(int j = 0; j < n; j++) {
            if(start[a] < end[b]) {
                count++;
                a++;
            }
            else {
                count--;
                b++;
            }
            room = Math.max(count, room);
        }
        return room;
    }
}