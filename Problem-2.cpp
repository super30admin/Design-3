// Time Complexity : O(1)

// Space Complexity : O(2N)

// Did this code successfully run on Leetcode : Yes

// Appoarch: Made use of 2 stacks st1 and st2 for checking it like an iterator. 

// 341. Flatten Nested List Iterator

  // This is the interface that allows for creating nested lists.
  // You should not implement it, or speculate about its implementation
  class NestedInteger {
    public:
      // Return true if this NestedInteger holds a single integer, rather than a nested list.
      bool isInteger() const;
 
      // Return the single integer that this NestedInteger holds, if it holds a single integer
      // The result is undefined if this NestedInteger holds a nested list
      int getInteger() const;
 
      // Return the nested list that this NestedInteger holds, if it holds a nested list
      // The result is undefined if this NestedInteger holds a single integer
      const vector<NestedInteger> &getList() const;
  };

#include <bits/stdc++.h>
using namespace std;

class NestedIterator {
public:
    stack<vector<NestedInteger>::iterator> st1,st2;
    NestedIterator(vector<NestedInteger> &nestedList) {
        st1.push(nestedList.begin());
        st2.push(nestedList.end());
    }
    
    int next() {
        hasNext();
        return (st1.top()++)->getInteger();
    }
    
    bool hasNext() {
        while(!st1.empty()){
            if(st1.top() == st2.top()){
                st1.pop();
                st2.pop();
            }
            else{
                auto el = st1.top();
                if(el->isInteger()){
                    return true;
                }
                st1.top()++;
                st1.push(el->getList().begin());
                st2.push(el->getList().end());
            }
        }
        return false;
    }
};