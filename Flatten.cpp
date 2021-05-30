/*
Intuition: In this program, we can apply DFS but if we use the recursive method, we will have to store the numbers in an array
which voilates the 'iterator' property of the iterator.

To avoid this, we do contorlled recursion using our own custom stack.

#####################################################################
next Method: 
Time Complexity : O(1)
#####################################################################
hasNext Method: 
Time Complexity : O(1)
#####################################################################
Total Space: O(D), where D is the depth.
*/
class NestedIterator {
public:
    stack<vector<NestedInteger>::iterator> Start, End;
    NestedIterator(vector<NestedInteger> &nestedList) {
        Start.push(begin(nestedList));
        End.push(end(nestedList));    
    }
    int next() {
        return Start.top()++ -> getInteger();
    }

	bool hasNext() {        
        while(size(Start)){			
            auto top = Start.top();            
            if(Start.top() == End.top()){
                Start.pop(), End.pop();
                continue;
            }                
            if(top -> isInteger()) break;
            Start.top()++;
            Start.push(begin(top -> getList()));  
            End.push(end(top -> getList()));
        }      
        return size(Start); 
    }    
};