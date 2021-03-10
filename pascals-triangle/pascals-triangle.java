class Solution {
    public List<List<Integer>> generate(int numRows) {
       List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> val = null;
		for (int i = 0; i < numRows; i++) {

			if (i == 0) {
				val = new ArrayList<Integer>();
				val.add(1);
			} else if (i == 1) {
				val = new ArrayList<Integer>();
				val.add(1);
				val.add(1);
			} else {
				val = new ArrayList<Integer>();
				val.add(1);

				for (int j = 1; j < i; j++) {
					int result = res.get(i-1).get(j-1) + res.get(i-1).get(j);
					val.add(result);
				}

				val.add(1);

			}

			res.add(val);
		}
		return res;
        
        
    }
}