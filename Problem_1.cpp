
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
     stack<pair<vector<NestedInteger>,int>>st;
     NestedIterator(vector<NestedInteger> &nestedList) {
         st.push(make_pair(nestedList, -1));    
     }
    
    int next() {
        return st.top().first[st.top().second].getInteger();
    }
    
    bool hasNext() {
        while(st.size()){
            while(st.size() && ++st.top().second == st.top().first.size()){ 
                st.pop();
            }
            if(st.empty() || st.top().first[st.top().second].isInteger()) {
                break;
            }
            st.push(make_pair(st.top().first[st.top().second].getList(), -1));
        }
        return st.size();
    }
};

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i(nestedList);
 * while (i.hasNext()) cout << i.next();
 */