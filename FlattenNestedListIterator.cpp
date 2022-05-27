// Time Complexity : O(h)
// Space Complexity : O(h)
//    where h : maximum level of nesting.
// Did this code successfully run on Leetcode : yes

// Three line explanation of solution in plain english
/* Controlled stack approach. Create a stack to store native iterator of vector.
 * Initialize this stack with the iterator of i/p vector.
 * Perform the following operation when hasNext() is called.
 *    1. If iterator present at the top of stack is reached to end of vector then pop the top element of stack.
 *    2. If element pointed by iterator present at the top of stack is integer then assign that value to nextEle member variable and return true.
 *    3. If element pointed by iterator present at the top of stack is vector then push the iterator of that vector on the stack.
 * Keep repeating above step until the stack is not empty. If stack is empty then return true. 
 */

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
    stack<pair<vector<NestedInteger>::iterator, vector<NestedInteger>::iterator>> stk;
    NestedInteger nextEle;
    
    NestedIterator(vector<NestedInteger> &nestedList) {
        stk.push({nestedList.begin(), nestedList.end()});    
    }
    
    int next() {
        return nextEle.getInteger();
    }
    
    bool hasNext() {
        while (!stk.empty())
        {
            auto& itr = stk.top().first;
            
            if(itr == stk.top().second)
            {
                stk.pop();
            }
            else if (itr->isInteger())
            {
                nextEle = itr->getInteger();
                itr++;
                return true;
            }
            else
            {
                stk.push({itr->getList().begin(), itr->getList().end()});
                itr++;
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