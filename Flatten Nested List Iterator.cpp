//Time Complexity- O(1)
//Space Complexity- O(D)

class NestedIterator {
public:
    stack<vector<NestedInteger>::iterator> Start, End; // stores iterators to start & end of nested lists
    NestedIterator(vector<NestedInteger> &nestedList) {
        Start.push(begin(nestedList)); // only storing the iterator to start of nestedList
        End.push(end(nestedList));     // required to detect if we reached end of nestedList
    }
    // will only return the next integer and increment the iterator
    int next() {
        return Start.top()++ -> getInteger();
    }
    /*  before next() is called, each time hasNext will make sure there exists a integer to return
		If there exists, it will make sure that top of stack points to it.
		If stack size becomes empty, all of nestedList is traversed
	*/
	    bool hasNext() {        
            while(!Start.empty()){			
                auto top = Start.top();            
                // If it is end of one of nested list, pop that list, so we can iterate over the next one
                if(Start.top() == End.top()){
                    Start.pop(), End.pop();
                    continue;
                }                
                if(top -> isInteger()) break;
                // if top is not integer, then it is definately a list
                // First increment top of stack, so we know that all integers uptill(including top list) now have been traversed
                // and then push the start iterator of top into stack, so that we traverse that one next
                Start.top()++;
                Start.push(begin(top -> getList()));  
                End.push(end(top -> getList()));
            }
            // true will only be returned when stack top holds iterator of next integer
            return size(Start); 
    }    
};

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i(nestedList);
 * while (i.hasNext()) cout << i.next();
 */