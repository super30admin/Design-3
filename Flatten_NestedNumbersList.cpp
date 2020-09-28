// Time Complexity : O(n)
// Space Complexity : O(n) where n is number of numbers in my given list
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : no issues as of now.. Learning



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
public:
    
    queue<int> myqueue;
    NestedIterator(vector<NestedInteger> &nestedList) {
        recursion(nestedList);
    }
    
    int next() {
         int temp = myqueue.front();
        myqueue.pop();
        return temp;
    }
    
    bool hasNext() {
        return !myqueue.empty();
    }
    
    void recursion(vector<NestedInteger> &nestedList){
        
     
        for ( NestedInteger data:nestedList){
            
            if(data.isInteger()){
                myqueue.push(data.getInteger());
            }else{
                recursion(data.getList());
            }
        }
    }
};

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i(nestedList);
 * while (i.hasNext()) cout << i.next();
 */