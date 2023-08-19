// Time Complexity : O(k) k = maximum depth or degree of nestedness or O(n) n = nestedList.size() (whichever is higher)
// Space Complexity :  O(k) k = maximum depth or degree of nestedness
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach


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


// approach: 1: (not recommended)
// we can think of using a queue and perform a recursion to process all integers into the queue and 
// using a pointer to get to our answer whenever a function is called. Even though this approach gets submitted
// on leetcode, it is not considered as a proper solution because, we are storing all integers initially
// and giving output whenever a function is called. the problem is : what if a value changes in the future,
// we will not be able to recognize it if we process all of the nestedList initially itself.
// so, using a queue and storing all values using recurssion is not a recommended solution


// approach: 2 : using controlled recursion 

// we use a stack and store the start and end iterator positions in the stack.
// whenever we come across a list, we push that lists start and end again in the stack.
// each time while processing , we check if the start and end positions are matching. if they are
// matching, that means the list is completely processes, so we pop the start and end positions of that 
// list from the stack.

class NestedIterator {
public:
    typedef vector<NestedInteger>::const_iterator NI;
    stack<pair<NI,NI>>st; // for start and end positions
    NestedIterator(vector<NestedInteger> &nestedList) {
        st.push({nestedList.begin(),nestedList.end()});
    }
    
    int next() {
        if(hasNext()){
            auto it = st.top().first; // take the start position
            
            st.top().first++; // increment the start position
            
            return it->getInteger();
        }
        return INT_MIN; 
    }
    
    bool hasNext() {
        while(!st.empty())
        {
            if(st.top().first == st.top().second){
                st.pop();
            }
            else if(st.top().first->isInteger()){
                return true;
            }
            else{
                auto &nextList = st.top().first->getList();
                
                st.top().first++; // incrementing because we are going to push first position list in stack
                
                st.push({nextList.begin(),nextList.end()});
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