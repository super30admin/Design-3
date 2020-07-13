//Time Complexity : O(n)
// Space Complexity :O(n)
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :No
class NestedIterator {
public:
    // controlled recrusion not followed
    void flatten_list(vector<NestedInteger> &nestedList){
        for(NestedInteger nums:nestedList){
            if(nums.isInteger()){
                q.push(nums);
            }
            else{
                flatten_list(nums.getList());
            }
        }
    }
    
    queue<NestedInteger> q;
    NestedIterator(vector<NestedInteger> &nestedList) {
        flatten_list(nestedList);
    }
    
    int next() {
        NestedInteger n = q.front();
        int num = n.getInteger();
        q.pop();
        return num;
    }
    
    bool hasNext() {
        if(q.empty()==false)
            return true;
        
        return false;
    }
};

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i(nestedList);
 * while (i.hasNext()) cout << i.next();
 */