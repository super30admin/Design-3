#include<iostream>
#include<bits/stdc++.h>
#include<vector>
using namespace std;
class NestedInteger {
public:
     // Return true if this NestedInteger holds a single integer, rather than a nested list.
    bool isInteger() const;
 
      // Return the single integer that this NestedInteger holds, if it holds a single integer
     // The result is undefined if this NestedInteger holds a nested list
    int getInteger() const;

     // Return the nested list that this NestedInteger holds, if it holds a nested list
     // The result is undefined if this NestedInteger holds a single integer
    vector<NestedInteger> &getList() const;
};
/*
We maintain two stacks: one for lists and one for current indexes.
We iterate through nested lists by pushing inner lists when needed.
When we find an integer, we pause so next() can return it.
*/
class NestedIterator{
    stack<vector<NestedInteger>> st;
    stack<int> idxSt;
    NestedInteger nextEl;

public:
    NestedIterator(vector<NestedInteger> &nestedList) {
        st.push(nestedList);
        idxSt.push(0);
    }

    int next() {
        return nextEl.getInteger();
    }

    bool hasNext() {
        while (!st.empty()) {
            if (st.top().size() == idxSt.top()) {
                st.pop();
                idxSt.pop();
            } else {
                vector<NestedInteger> currentList = st.top();
                int currentIdx = idxSt.top();
                idxSt.pop();
                idxSt.push(currentIdx + 1);
                nextEl = currentList[currentIdx];
                if (nextEl.isInteger()) {
                    return true;
                } else {
                    st.push(nextEl.getList());
                    idxSt.push(0);
                }
            }
        }
        return false;
    }
};
