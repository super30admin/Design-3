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
vector<int> res;
int k = 0;
vector<NestedInteger> nL ;

NestedIterator(vector<NestedInteger> &nestedList) {
    nL = nestedList ;
    stack<NestedInteger>st ;
    int i;
    for(i = nL.size()-1 ; i>=0 ; i--) st.push(nL[i]) ;
    while(!st.empty()){
        NestedInteger nl = st.top(); st.pop() ;
        if(!nl.isInteger()){
            vector<NestedInteger> nl2 = nl.getList() ;
             for(i = nl2.size()-1 ; i>=0 ; i--) st.push(nl2[i]) ;
            
        }
        else{
            res.push_back(nl.getInteger()) ;
        }
    }
}

int next() {
    int rtn = res[k++] ;
    return rtn;
}

bool hasNext() {
    return ( k < res.size() );
}
};

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i(nestedList);
 * while (i.hasNext()) cout << i.next();
 */