class Solution {
    public String customSortString(String S, String T) {
        String res = "";
        
        if (S == null || S.length() == 0)
            return null;
​
        HashMap<Character, Integer> map = new HashMap<>();
​
        for (int i = 0; i < T.length(); i++) {
            map.put(T.charAt(i), map.getOrDefault(T.charAt(i), 0) + 1);
​
        }
        int count = 0;
        for (int i = 0; i < S.length(); i++) {
            count = 0;
            if (map.containsKey(S.charAt(i))) {
​
                count = map.get(S.charAt(i));
                for (int j = 0; j < count; j++) {
                    res += S.charAt(i);
​
                }
​
            } 
            map.remove(S.charAt(i));
​
        }
        count =0;
        for(char c : map.keySet()){
            count = map.get(c);
            for (int j = 0; j < count; j++) {
                    res += c;
​
                }
        }
​
        return res;
​
    }
}
