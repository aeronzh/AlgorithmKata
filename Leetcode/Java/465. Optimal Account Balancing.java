// A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for $10. Then later Chris gave Alice $5 for a taxi ride. We can model each transaction as a tuple (x, y, z) which means person x gave person y $z. Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), the transactions can be represented as [[0, 1, 10], [2, 0, 5]].
//
// Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.
//
// Note:
//
// A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
// Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.
// Example 1:
//
// Input:
// [[0,1,10], [2,0,5]]
//
// Output:
// 2
//
// Explanation:
// Person #0 gave person #1 $10.
// Person #2 gave person #0 $5.
//
// Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
// Example 2:
//
// Input:
// [[0,1,10], [1,0,1], [1,2,5], [2,0,5]]
//
// Output:
// 1
//
// Explanation:
// Person #0 gave person #1 $10.
// Person #1 gave person #0 $1.
// Person #1 gave person #2 $5.
// Person #2 gave person #0 $5.
//
// Therefore, person #1 only need to give person #0 $4, and all debt is settled.

// TODO
// This problem can be converted to calculate the minimum connections between the rich and the poor
public class Solution {
    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] t : transactions) {
            if (!map.containsKey(t[0])) {
                map.put(t[0]);
            }
            map.put(t[0], map.get(t[0]) - t[3]);

            if (!map.containsKey(t[1])) {
                map.put(t[1]);
            }
            map.put(t[1], map.get(t[1]) + t[3]);
        }

        List<Integer> rich = new ArrayList<>();
        List<Integer> poor = new ArrayList<>();
        for (int debt : map.values()) {
            if (debt > 0) {
                rich.add(debt);
            } else if (debt < 0) {
                poor.add(debt);
            }
        }

        return helper(rich, poor);
    }

    private int helper(List<Integer> rich, List<Integer> poor) {
        if (rich.size() <= 1 || poor.size() <= 1) {
            return Math.max(rich.size(), poor.size());
        }

        int result = 0;
        for (int i = 0; i < rich.size(); i++) {
            for (int j = 0; j < poor.size(); j++) {
                List<Integer> richClone = rich.clone();
                List<Integer> poorClone = poor.clone();
                if (rich[i] == poor[j]) {
                    richClone.remove(i);
                    poorClone.remove(j);
                } else if (rich[i] < poor[j]) {
                    poorClone.set(j, richClone.remove(i) + poor[j]);
                } else {
                    richClone.set(i, poorClone.remove(j) + rich[i]);
                }
                result = Math.min(result, helper(richClone, poorClone) + 1);
            }
        }

        return result;
    }
}
