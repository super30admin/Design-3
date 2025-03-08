// https://leetcode.com/problems/flatten-nested-list-iterator/
// Time Complexity : 
// next  - O(1)
// hasNext - O(n) where n is the number of nested elements
// Space Complexity : O(n)  where n is the number of nested elements
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach


 class NestedIterator {
    public:
        stack<NestedInteger> st;
        NestedIterator(vector<NestedInteger> &nestedList) {
            for(int i = nestedList.size()-1; i>=0; i--){
                st.push(nestedList[i]);
            }
        }
        
        int next() {
            int ans = st.top().getInteger();
            st.pop();
            return ans;
        }
        
        bool hasNext() {
            while(!st.empty()){
                NestedInteger curr = st.top();
                if(curr.isInteger()){
                    return true;
                }
                st.pop();
                vector<NestedInteger> res = curr.getList();
                for(int i = res.size()-1; i>=0; i--){
                    st.push(res[i]);
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