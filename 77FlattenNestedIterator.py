"""
// Time Complexity :O(n) length of list.
// Space Complexity :O(nd) len of list, d = depth of nested list
// Did this code successfully run on Leetcode : BF solution.
// Any problem you faced while coding this : NA

//Explanation:
if element is not list, append to result.
Else recursively call the function 
"""
class NestedIterator:
  def __init__(self):
    self.result = []

  def solution(self,st):
    for element in st:
      if type(element) == list:
        self.solution(element)
      else:
        self.result.append(element)
    return self.result



if __name__ == "__main__":
 st = [[1,1],2,[1,1]]
 n = NestedIterator()
 print("result = ",n.solution(st))
