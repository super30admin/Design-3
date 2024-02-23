// TC = Avg case = O(1) worst case = O(N)
// SC = O(H) Depth of the list of iterator
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

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

// class NestedIterator {
// public:
//     queue<int> q;
//     NestedIterator(vector<NestedInteger> &nestedList) {
//         dfs(nestedList);
//     }
    
//     int next() {
//         int n = q.front();
//         q.pop();
//         return n;
//     }
    
//     bool hasNext() {
//         return !q.empty();
//     }

//     void dfs(vector<NestedInteger> &nestedList) {
//         // base
//         // logic
//         for(NestedInteger el: nestedList) {
//             if(el.isInteger()) q.push(el.getInteger());
//             else dfs(el.getList());
//         }
//     }
// };

// or (correct way)


class NestedIterator {
public:
    stack<vector<NestedInteger>::iterator> begins, ends;
    
    NestedIterator(vector<NestedInteger> &nestedList) {
        begins.push(nestedList.begin());
        ends.push(nestedList.end());
    }
    
    int next() {    // O(1)
        hasNext();
        return (begins.top()++)->getInteger(); // moving iterator to next
    }
    
    bool hasNext() {    // Avg case = O(1) worst case = O(N)
        while(begins.size()) {
            if(begins.top() == ends.top()) { // as we'r moving the iterator, so compare both iterators
                begins.pop();
                ends.pop();
            }
            else {
                auto nextEl = begins.top();
                if(nextEl->isInteger()) return true;
                begins.top()++; // moving iterator to next
                begins.push(nextEl->getList().begin());
                ends.push(nextEl->getList().end());
            }
        }
        return false;
    }

};
/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i(nestedList);
 * while (i.hasNext()) cout << i.next();
 */