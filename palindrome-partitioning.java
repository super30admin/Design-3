class Solution {
    List<List<String>> result;
​
    public List<List<String>> partition(String s) {
​
        result = new ArrayList<List<String>>();
​
        helper(s, new ArrayList<String>(), 0);
​
        return result;
    }
​
    private void helper(String s, List<String> temp, int index) {
        // TODO Auto-generated method stub
​
        // base
        if (index == s.length()) {
            result.add(new ArrayList<String>(temp));
        }
​
        // logic
        for (int i = index; i < s.length(); i++) {
            if (isPalindrome(s, index, i)) {
​
                // action
                temp.add(s.substring(index, i + 1));
​
                // recurse
                helper(s, temp, i + 1);
​
                // backtrack
                temp.remove(temp.size() - 1);
​
            }
​
        }
​
    }
​
    private boolean isPalindrome(String s, int l, int r) {
        if (l == r)
            return true;
​
        while (l < r) {
            if (s.charAt(l) != s.charAt(r))
                return false;
            l++;
            r--;
        }
        return true;
    }
}
