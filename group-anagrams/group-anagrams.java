class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<List<String>>();
​
        if (strs == null || strs.length == 0) {
            return result;
​
        }
​
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
​
        // sort and put in hashmap
        for (String s : strs) {
            char tempArray[] = s.toCharArray();
            // sort tempArray
            Arrays.sort(tempArray);
            String var = String.valueOf(tempArray);
​
            if (!map.containsKey(var)) {
                map.put(var, new ArrayList<String>());
            }
            map.get(var).add(s);
​
        }
​
        for (List str : map.values()) {
            result.add(str);
        }
​
        return result;
​
    }
}
