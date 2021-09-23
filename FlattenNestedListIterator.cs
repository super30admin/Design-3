using System;
using System.Collections.Generic;
using System.Text;

namespace Design
{
    class FlattenNestedIntegerIterator
    {
        //C# .NET do not have built in NestedIterator implemented
        //TC for Next and HasNest is O(1)
        //For Iterator it is O(n)
        //SC is O(n)
        public class NestedIterator 
        {
            Queue<int> q;
            public NestedIterator(IList<NestedInteger> nestedList)
            {
                q = new Queue<int>();
                dfs(nestedList);
            }
            public int Next()
            {
                return q.Dequeue();
            }
            public bool HasNext()
            {
                return q.Count != 0;
            }
            private void dfs(IList<NestedInteger> nestedList)
            {
                foreach (NestedInteger el in nestedList)
                {
                    if (el.IsInteger())
                    {
                        q.Enqueue(el.GetInteger());
                    }
                    else
                    {
                        dfs(el.GetList());
                    }
                }
            }
        }
    }

    public class NestedInteger
    {
        internal int GetInteger()
        {
            throw new NotImplementedException();
        }

        internal IList<NestedInteger> GetList()
        {
            throw new NotImplementedException();
        }

        internal bool IsInteger()
        {
            throw new NotImplementedException();
        }
    }
}
