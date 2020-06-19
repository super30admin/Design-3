// Simple solution

// Time Complexity : O(n) for creating queue where n is number of el in list; O(1) for both put and get  
// Space Complexity : O(n) 
// Did this code succesfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// 1. Maintain a queue and in the constructor call a flatten function
// 2. flatten is a recursive function which iterates thru input and if el is int then adds to queue else recursively calls flatten for el
// 3. next() just returns element in front and removes it; hasNext() returns true only when q is empty  

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
    queue<int> q;
    NestedIterator(vector<NestedInteger> &nestedList) {
        flatten(nestedList);
    }
    
    void flatten(vector<NestedInteger> &nestedList){
        // base
        // nothing
        
        // logic
        for(auto el: nestedList){
            if(el.isInteger())
                q.push(el.getInteger());
            else
                flatten(el.getList());
        }
    }
        
    int next() {
        int el = q.front();
        q.pop();
        return el;
    }
    
    bool hasNext() {
        return !q.empty();
    }
};

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i(nestedList);
 * while (i.hasNext()) cout << i.next();
 */