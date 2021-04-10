// Time Complexity : O(N+L) 
// Space Complexity : O(N+D)
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

class NestedIterator {
public:
    queue<int> q;
    NestedIterator(vector<NestedInteger> &nestedList) {
        flatten(nestedList);
    }
    
    int next() {
        int res = q.front();
        q.pop();
        return res;
    }
    
    bool hasNext() {
        return !q.empty();
    }
    void flatten(vector<NestedInteger> &nestedList){
        for(NestedInteger elements:nestedList){
            if(elements.isInteger()){
                q.push(elements.getInteger());
            }
            else{
                flatten(elements.getList());
            }
        }
    }
};

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i(nestedList);
 * while (i.hasNext()) cout << i.next();
 */
 
 
 // BETTER APPROACH
 
 // Time Complexity : O(1)
// Space Complexity : O(D) deepest list
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

class NestedIterator {
    NestedInteger *p;
    int index, size;
    stack<NestedInteger*> pStack;
    stack<int> sStack;
    stack<int> iStack;
public:
    NestedIterator(vector<NestedInteger> &nestedList) {
        index = 0;
        size = nestedList.size();
        if(size!=0){
            p = &nestedList[0];
        }
    }
    
    int next() {
        int result = p->getInteger();
        p++;
        index++;
        return result;
    }
    
    bool hasNext() {
        while(index>=size || p->isInteger()==false){
            if(index>=size){
                if(pStack.size()==0){
                    return false;
                }
                p = pStack.top()+1;
                index = iStack.top()+1;
                size = sStack.top();
                pStack.pop();
                iStack.pop();
                sStack.pop();
            }
            else if(p->getList().size()==0){
                p++;
                index++;
            }
            else{
                pStack.push(p);
                iStack.push(index);
                sStack.push(size);
                size = p->getList().size();
                p = &(p->getList()[0]);
                index = 0;
            }
        }
        return true;
    }
};

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i(nestedList);
 * while (i.hasNext()) cout << i.next();
 */
