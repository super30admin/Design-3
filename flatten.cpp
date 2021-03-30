//TC: O(n) where n is number of elements
//SC: O(n) 

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
 */

class NestedIterator {

//use queue since this would make implementing next and hasNext easy. 
queue<int> q;
    
void dfs(vector<NestedInteger> &list){
    
        //get element from the nested list. Check if this is a list or an integer using getList() or getInteger()
        for(auto nst:list)
        {
          //if it is integer
          if(nst.isInteger())
             q.push(nst.getInteger());
          else
              dfs(nst.getList());    
       }
    
}
    
public:
    NestedIterator(vector<NestedInteger> &nestedList) {
        //calls dfs function to add to queue.
        dfs(nestedList);
    }
    
    int next() {
        //returns the next value from the queue, and removes it from the queue
        int nextVal = q.front();
        q.pop();
        return nextVal;
    }
    
    bool hasNext() {
        //checks if queue has a next element or not
        return !q.empty();
    }
};

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i(nestedList);
 * while (i.hasNext()) cout << i.next();
 */