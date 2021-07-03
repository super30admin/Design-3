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

//I just put everything in a stack and returned it. Vector is lifo, so is stack. Rather than using vector again, I just used stack as lazy way out since it has all the things I need
// tc and sc are both O(n), where n is no of elems in nested list
class NestedIterator {
public:
    NestedIterator(vector<NestedInteger> &nestedList) {
        for(int i=nestedList.size()-1;i>=0;i--)
            st.push(nestedList[i]);
    }

    int next() {
        NestedInteger t = st.top(); st.pop();
        return t.getInteger();
    }

    bool hasNext() {
        while(!st.empty()) {
            NestedInteger t = st.top();
            if(t.isInteger()) return 1;
            st.pop();
            for(int i=t.getList().size()-1; i>=0;i--) st.push(t.getList()[i]);
        }
       return 0;
    }
    
private:
    stack<NestedInteger> st;
};
/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i(nestedList);
 * while (i.hasNext()) cout << i.next();
 */
