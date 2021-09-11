class NestedIterator {
public:
    
    stack<vector<NestedInteger>::iterator> begins, ends;
    NestedIterator(vector<NestedInteger> &nestedList) {
        begins.push(nestedList.begin());
        ends.push(nestedList.end());
    }
    
    int next() {
        hasNext();
        return ((begins.top()++)->getInteger());
    }
    
    bool hasNext() {
        while(begins.size()){
            if(begins.top() == ends.top()){
                begins.pop();
                ends.pop();
            }
            else{
                auto x = begins.top();
                if(x->isInteger())
                    return true;
                begins.top()++;
                begins.push(x->getList().begin());
                ends.push(x->getList().end());
            }
        }
        return false;
    }
};


class NestedIterator {
public:
    queue<int> q;
    NestedIterator(vector<NestedInteger> &nestedList) {
        dfs(nestedList);
    }
    int next() {
        int val = q.front();
        q.pop();
        return val;
    }
    
    bool hasNext() {
        return !q.empty();
    }
    
    void dfs(vector<NestedInteger> &nestedList){
        for(NestedInteger el : nestedList){
            if(el.isInteger())
                q.push(el.getInteger());
            else
                dfs(el.getList());
        }
    }
};
