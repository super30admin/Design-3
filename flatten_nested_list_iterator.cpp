// Time Complexity : O(1)
// Space Complexity :  O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this

class NestedIterator {
private:
    vector<int> vect;
    int curr_iter;
    
    void iterate_rec(vector<NestedInteger> &curr_list)
    {
        int curr_iter = 0;
        int len = curr_list.size();
        
        while(curr_iter < len)
        {
            // eiter an integer
            if(curr_list[curr_iter].isInteger())
            {
                vect.push_back(curr_list[curr_iter].getInteger());
            }
            // or a list
            else
            {
                vector<NestedInteger> temp_list = curr_list[curr_iter].getList();
                
                iterate_rec(temp_list);
            }
            
            curr_iter++;
        }
    }
    
public:
    NestedIterator(vector<NestedInteger> &nestedList) {
        
        curr_iter = 0;
        
        iterate_rec(nestedList);
    }
    
    int next() {
        
        int ret_val = vect[curr_iter];
        
        curr_iter++;
        
        return ret_val;
        
    }
    
    bool hasNext() {
        
        if(curr_iter < vect.size())
            return true;
        else
            return false;
    }
};
