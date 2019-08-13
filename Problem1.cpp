//
// Created by shazm on 8/12/2019.
//

#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

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
class NestedIterator {
private:
    queue<int> que;
    void addToque(vector<NestedInteger> &nestedList){
        for(int x = 0; x<nestedList.size(); x++){
            if(nestedList[x].isInteger()){
                que.push(nestedList[x].getInteger());
            }else{
                addToque(nestedList[x].getList());
            }
        }
    }
public:
    NestedIterator(vector<NestedInteger> &nestedList) {
        addToque(nestedList);
    }

    int next() {
        int temp = que.front();
        que.pop();
        return temp;
    }

    bool hasNext() {
        if(que.empty()){return false;}
        return true;
    }
};

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i(nestedList);
 * while (i.hasNext()) cout << i.next();
 */