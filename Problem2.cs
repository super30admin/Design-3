// Time Complexity : O(1) for both Get and Put
// Space Complexity : O(n) to store the nodeMap
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

/*
1. All nodes are stored in a node map so that they can be accessed by their key in O(1) time
2. All insertion are done at the start, deletion is done at the end. When node capacity reaches max, node at end is deleted.
3. If a node exits and Get or Put is called on it, the node is removed and inserted at the start.
*/

public class CacheNode
{
    public int key;
    public int value;
    public CacheNode next;
    public CacheNode prev;

    public CacheNode(int key, int value)
    {
        this.key = key;
        this.value = value;
        this.next = null;
        this.prev = null;
    }
}

public class LRUCache {
    Dictionary<int, CacheNode> nodeMap;
    int capacity;
    CacheNode head;
    CacheNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.nodeMap = new();
        head = new CacheNode(-1, -1);
        tail = new CacheNode(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public CacheNode RemoveNode(CacheNode temp)
    {
        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
        temp.next = null;
        temp.prev = null;
        return temp;
    }

    public void InsertAtStart(CacheNode temp)
    {
        temp.next = head.next;
        head.next.prev = temp;
        head.next = temp;
        temp.prev = head;
    }

    public CacheNode DeleteAtEnd()
    {
        CacheNode temp = tail.prev;
        temp.prev.next = tail;
        tail.prev = temp.prev;
        temp.next = null;
        temp.prev = null;
        return temp;
    }
    
    public int Get(int key) {
        if(!nodeMap.ContainsKey(key))
        {
            return -1;
        }

        int value = nodeMap[key].value;
        CacheNode temp = RemoveNode(nodeMap[key]);
        InsertAtStart(temp);

        return value;
    }
    
    public void Put(int key, int value) {
        if(!nodeMap.ContainsKey(key))
        {
            if(nodeMap.Count == capacity)
            {
                CacheNode nodeDeleted = DeleteAtEnd();
                nodeMap.Remove(nodeDeleted.key);
            }

            CacheNode temp = new CacheNode(key, value);
            nodeMap[key] = temp;
            InsertAtStart(temp);
        }

        else
        {
            nodeMap[key].value = value;
            CacheNode modifiedNode = RemoveNode(nodeMap[key]);
            InsertAtStart(modifiedNode);
        }       
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.Get(key);
 * obj.Put(key,value);
 */