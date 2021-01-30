class Solution {
    
    class TrieNode {
​
        String word;
        TrieNode children[];
​
        public TrieNode() {
            children = new TrieNode[26];
​
        }
    }
​
    TrieNode root;
​
    public String longestWord(String[] words) {
​
        root = new TrieNode();
        Queue<TrieNode> q = new LinkedList<TrieNode>();
        for (String w : words) {
            insert(w);
​
        }
        q.add(root);
        TrieNode curr = null;
        while (!q.isEmpty()) {
            curr = q.poll();
            for (int j = 25; j >= 0; j--) {
                if (curr.children[j] != null && curr.children[j].word != null) {
                    q.add(curr.children[j]);
                }
​
            }
​
        }
        return curr.word;
​
    }
​
    private void insert(String word) {
​
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
​
            char ch = word.charAt(i);
​
            if (curr.children[ch - 'a'] == null)
                curr.children[ch - 'a'] = new TrieNode();
​
            curr = curr.children[ch - 'a'];
​
        }
        curr.word = word;
