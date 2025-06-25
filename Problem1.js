// # Design-3

// ## Problem 1: Flatten Nested List Iterator (https://leetcode.com/problems/flatten-nested-list-iterator/)

//! This code is expected, this is the optimal

var NestedIterator = function(nestedList) {
    this.stack = []
    if (nestedList) this.stack.push([nestedList, 0]);
};


/**
 * @this NestedIterator
 * @returns {boolean}
 */
NestedIterator.prototype.hasNext = function() {
    //** Time complexity : O(1)amortized */
    /**Every NestedInteger node (whether it’s an integer or a sub-list) is touched at most twice: once when you push it onto the stack, once when you pop it off.

Across the whole traversal of N integers (and a comparable number of sub-lists), there are O(N) total push/pop/peek operations.

Spread over the O(N) calls to hasNext() (interleaved with next()), that works out to O(1) extra work per call on average, even though occasionally you may spend up to O(d) in one hasNext() to skip empty lists or drill in. */
//** Auxiliary space = O(d), where d = maximum depth of the nested lists.*/
    //should return true or false
    while(this.stack.length){
        let [list,idx] = this.stack[this.stack.length-1]
        if(idx===list.length){
            this.stack.pop()
            continue
        }
        let x = list[idx]
        if(x.isInteger()){
            return true
        }
            //its a list
            this.stack[this.stack.length-1][1]++
            this.stack.push([x.getList(),0])
        
    }
    return false
};

/**
 * @this NestedIterator
 * @returns {integer}
 */
NestedIterator.prototype.next = function() {
    //** Time complexity :  O(1)*/
//** */
//this should print the value 
    let [list,idx] = this.stack[this.stack.length-1]
    let x = list[idx].getInteger()
    this.stack[this.stack.length-1][1]++
return x
};

/**
 * Your NestedIterator will be called like this:
 * var i = new NestedIterator(nestedList), a = [];
 * while (i.hasNext()) a.push(i.next());
*/


//! The below code works but isnt lazy loading we are precomputing all the elements in a construtor and only using res array as iterator
// var NestedIterator = function(nestedList) {
//   this.res = [];
//   this.i   = 0;

//   // Use an arrow fn so `this` is lexically inherited
//   const dfs = (list) => {
//     for (const ni of list) {
//       if (ni.isInteger()) {
//         this.res.push(ni.getInteger());
//       } else {
//         dfs(ni.getList());
//       }
//     }
//   };

//   dfs(nestedList);
// };

// NestedIterator.prototype.hasNext = function() {
//   return this.i < this.res.length;
// };

// NestedIterator.prototype.next = function() {
//   // post-increment so we return res[i] then bump i
//   return this.res[this.i++];
// };
