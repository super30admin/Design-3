/* 
    Time Complexity                              :  next -> O(1)
                                                    hasNext -> Ammortized O(1)
    Space Complexity                             :  O(maximum nesting depth), in worst case O(N).
    Did this code successfully run on Leetcode   :  Yes
    Any problem you faced while coding this      :  No
*/

#include <bits/stdc++.h> 
using namespace std;  

// https://leetcode.com/problems/flatten-nested-list-iterator/submissions/


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
    typedef vector<NestedInteger>::iterator iter;
    vector<int> ans;
    stack<iter> begins, ends;
    
    // void flattenMethod(NestedInteger nestedList) {
    //     for(auto ni : nestedList) {
    //         if(ni.isIntger()) {
    //             q.push(ni);
    //         } else {
    //             flattenMethod(ni);
    //         }
    //     }
    // }
public:
    // NestedInteger nel;
    // stack<NestedInteger> st;
    
    NestedIterator(vector<NestedInteger> &nestedList) {
        begins.push(nestedList.begin());
        ends.push(nestedList.end());
    }
    
    int next() {
        iter top = begins.top()++;
        int val = top->getInteger();
        return val;
    }
    
    bool hasNext() {
        while(!begins.empty()) {
            iter top = begins.top();
            if(top == ends.top()) {
                begins.pop();
                ends.pop();
                if(!begins.empty()) {
                    begins.top()++;
                }
            } else if(top->isInteger()) {
                return true;
            } else {
                vector<NestedInteger>& nl = top->getList();
                begins.push(nl.begin());
                ends.push(nl.end());
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