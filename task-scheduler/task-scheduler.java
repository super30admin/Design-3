class Solution {
    public int leastInterval(char[] tasks, int n) {
        
      int maxFreq = 0;
        int maxCount = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (char ch : tasks) {
            int count = map.getOrDefault(ch, 0);
            map.put(ch, count + 1);
            maxFreq = Math.max(maxFreq, count + 1);
​
        }
        for (Integer i : map.values()) {
            if (i == maxFreq) {
                maxCount++;
            }
        }
​
        int paritions = maxFreq - 1;
        int empty = paritions * (n - maxCount + 1);
        int pending = tasks.length - (maxFreq * maxCount);
        int idle = Math.max(0, empty - pending);
        return tasks.length + idle;
        
        
        
    }
}
