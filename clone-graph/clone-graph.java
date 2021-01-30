/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/
​
class Solution {
    public Node cloneGraph(Node node) {
        if(node == null)
            return null;
        
        
        HashMap<Node,Node> map = new HashMap<>();
        Queue<Node> q = new LinkedList<>();
​
        Node copy = new Node(node.val);
        q.add(node);
        map.put(node,copy);
        while(!q.isEmpty()){
            
            Node curr = q.poll();
            for(Node n :curr.neighbors){
                if(!map.containsKey(n)){
                    Node newNode = new Node(n.val);
                    map.put(n,newNode);
                    q.add(n);
                }
                
                map.get(curr).neighbors.add(map.get(n));
                
            }
            
            
        }
        
        return map.get(node); // or copy
        
    }
}
