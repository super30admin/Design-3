
// TC = Avg case = O(1) worst case = O(N)
// SC = O(H) Depth of the list of iterator
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class NestedIterator {
    public:
        stack<vector<NestedInteger>::iterator> begins, ends;
    
        NestedIterator(vector<NestedInteger> &nestedList) {
            begins.push(nestedList.begin());
            ends.push(nestedList.end());
        }
    
        int next() { // O(1)
            hasNext(); // Ensure the top is an integer
            return (begins.top()++)->getInteger(); // Return and advance iterator
        }
    
        bool hasNext() { // Avg: O(1), Worst: O(N)
            while (!begins.empty()) {
                if (begins.top() == ends.top()) {
                    begins.pop();
                    ends.pop();
                } else {
                    auto it = begins.top();
                    if (it->isInteger()) return true;
    
                    // Go deeper into the nested list
                    begins.top()++;
                    begins.push(it->getList().begin());
                    ends.push(it->getList().end());
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