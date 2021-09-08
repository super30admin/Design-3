/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * class NestedInteger {
 *   public:
 *     // Return true if this NestedInteger holds a single integer, rather than a nested list.
 *     bool isInteger() const;
 *
 *     // Return the single integer that this NestedInteger holds, if it holds a single integer
 *     // The result is undefined if this NestedInteger holds a nested list
 *     int getInteger() const;
 *
 *     // Return the nested list that this NestedInteger holds, if it holds a nested list
 *     // The result is undefined if this NestedInteger holds a single integer
 *     const vector<NestedInteger> &getList() const;
 * };
 next() function
 Time Complexity = O(1)
 Space Complexity = O(n + h)
 where n is the size of the queue and h is the height of the tree.
 
 hasnext() function
 Time Complexity = O(1)
 Space Complexity = O(n + h)
 where n is the size of the queue and h is the height of the tree.
 */

class NestedIterator {
public:
    queue<int> q;
    NestedIterator(vector<NestedInteger> &nestedList) {
        dfs(nestedList);
    }
    
    int next() {
        int x=q.front();
        q.pop();
        return x;
    }
    
    bool hasNext() {
        return !q.empty();
    }
    void dfs(vector<NestedInteger> nestedList)
    {
        for(auto x : nestedList){
            if(x.isInteger()){
                q.push(x.getInteger());
            }
            else
                dfs(x.getList());
        }
    }
};

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i(nestedList);
 * while (i.hasNext()) cout << i.next();
 */
