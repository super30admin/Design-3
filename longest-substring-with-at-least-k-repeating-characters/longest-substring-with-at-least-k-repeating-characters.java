class Solution {
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() < k)
            return 0;
        if (k == 0)
            return s.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        boolean recursion = false;
        for (char c : map.keySet()) {
            if (map.get(c) < k) {
                recursion = true;
                break;
            }
        }
        if (!recursion)
            return s.length();
        int fast = 0, slow = 0, res = 0;
        while (fast < s.length()) {
            if (map.get(s.charAt(fast)) < k) {
                if (fast > slow)
                    res = Math.max(res, longestSubstring(s.substring(slow, fast), k));
                slow = ++fast;
            } else
                fast++;
        }
        return Math.max(res, longestSubstring(s.substring(slow), k));
    }
}
