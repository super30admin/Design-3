class NestedIterator {

public:

int listIdx;
NestedIterator* child;
vector<NestedInteger> &nestedList1;

NestedIterator(vector<NestedInteger> &nestedList): nestedList1(nestedList){
    listIdx = 0;
    child = NULL;
}

int next() {
    hasNext();
    if(!nestedList1[listIdx].isInteger()) {
        return child->next();
    }
    return nestedList1[listIdx++].getInteger();
}

bool hasNext() {
    while(listIdx < nestedList1.size()) {
        if(nestedList1[listIdx].isInteger()) {
            return true;
        }
        if(child==NULL) {
            child = new NestedIterator(nestedList1[listIdx].getList());
        }
        if(child->hasNext()) {
            return true;
        }
        child = NULL;
        listIdx++;
    }
    return false;
}
};