// 341. Flatten Nested List Iterator
// Time Complexity : O(n)
// Space Complexity :O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : No


class NestedIterator {
public:
    vector<int> a;
    NestedIterator(vector<NestedInteger> &nestedList) {
        flattenList(nestedList);
    }
    void flattenList(vector<NestedInteger> &nestedList) // no of integers is the time complexicity
    {
        for(int i = 0; i < nestedList.size(); i++){
            if(nestedList[i].isInteger()){
                a.push_back(nestedList[i].getInteger());
            }
            else{
                 flattenList(nestedList[i].getList()); 
            }
        }
    }
    int i = -1;
    int next() {
        i++;
        return a[i];
    }
    bool hasNext() {
        if(i+1 < a.size()) return true;
        return false;
    }
};

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i(nestedList);
 * while (i.hasNext()) cout << i.next();
 */
