// Time Complexity : O(n) where n is the number of elements  
// Space Complexity : O(levels of nesting or the depth)
// Did this code successfully run on Leetcode : Yes 



// Approach 1: Flattening the input itself - NOT a good solution

class NestedIterator {
public:
    queue<int> input;
    NestedIterator(vector<NestedInteger> &nestedList) {
        recursion(nestedList);
    }

    void recursion(vector<NestedInteger> &nestedList) {
        int size = nestedList.size();
        for(auto i:nestedList){
            if(i.isInteger())
                input.push(i.getInteger());
            else {
                recursion(i.getList());
            }
        }
    }
    
    int next() {
        int result = input.front();
        input.pop();
        return result;
    }
    
    bool hasNext() {
        return (!input.empty());
    }
};


// Approach 2: Writing an iterator thats going to flatten it for us

class NestedIterator {
public:
    //c++ iterator doesn't have the next and hasNext methods. To check if the iterator has reached the end of the vector, we need to compare it to vector.end()
    //we use endTracker to keep a track of the this end for every vector I push into the stack 
    stack<vector<NestedInteger>::iterator> stack, endTracker;
    int nextElement;

    //push the initial vector and the vector end in the stacks
    NestedIterator(vector<NestedInteger> &nestedList) {
        stack.push(nestedList.begin());
        endTracker.push(nestedList.end());
    }
    
    int next() {
        return nextElement;
    }
    
    // this moved the iterator to the next element 
    // thus we capture the element and move the iterator to the next
    bool hasNext() {
        while(!stack.empty()){
            // Three possibilitis 
            // 1. next() points to null
            // 2. The next element is an integer 
            // 3. the next element is a list

            //if the current top has reached its end - i.e., we have successfully processed that nested (or not) vector 
            //pop from both stacks
            if(stack.top() == endTracker.top()){
                stack.pop();
                endTracker.pop();
            }
            //the iterator at the top is pointing to an integer
            //assign it to nextElement to return from next(), increment iterator and return true
            else if(stack.top()->isInteger()){
                nextElement = stack.top()->getInteger();
                stack.top()++;
                return true;
            }

            //the iterator is pointing to another vector 
            //this means we should push this to the stack and increment the current iterator 
            //using a temp to keep the old value and push the vector and vector end 
            else {
                auto stackTop = stack.top();
                stack.top()++;
                stack.push(stackTop->getList().begin());
                endTracker.push(stackTop->getList().end());
            }
        }
        // the while loop keeps running till the stack is empty 
        // the function also stops running when it finds an Integer 
        // essentially, it keeps processing the nested vectors till it finds the integer 
        return false;
    }
};

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i(nestedList);
 * while (i.hasNext()) cout << i.next();
 */