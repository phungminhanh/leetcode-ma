/**
 * Given an integer n, generate all structurally unique BST's (binary search
 * trees) that store values 1...n.
 *
 * For example,
 * Given n = 3, your program should return all 5 unique BST's shown below.
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class UniqueBinarySearchTreesII95 {
    public List<TreeNode> generateTrees(int n) {
        return generateTrees(n, 1, n);
    }

    public List<TreeNode> generateTrees(int n, int l, int r) {
        List<TreeNode> res = new ArrayList<>();
        if (l > r) return res;
        for (int i=l; i<=r; i++) {
            List<TreeNode> lefts = generateTrees(n, l, i-1);
            if (lefts.isEmpty()) lefts.add(null);
            List<TreeNode> rights = generateTrees(n, i+1, r);
            if (rights.isEmpty()) rights.add(null);
            for (TreeNode ll: lefts) {
                for (TreeNode rr: rights) {
                    TreeNode t = new TreeNode(i);
                    t.left = ll;
                    t.right = rr;
                    res.add(t);
                }
            }
        }

        return res;
    }


    // DP
    public List<TreeNode> generateTrees2(int n) {
        return generateTrees(n, 1, n, new List[n][n]);
    }

    public List<TreeNode> generateTrees(int n, int l, int r, List[][] dp) {
        List<TreeNode> res = new ArrayList<>();
        if (l > r) return res;
        if (dp[l-1][r-1] != null) return dp[l-1][r-1];
        for (int i=l; i<=r; i++) {
            List<TreeNode> lefts = generateTrees(n, l, i-1, dp);
            if (lefts.isEmpty()) lefts.add(null);
            List<TreeNode> rights = generateTrees(n, i+1, r, dp);
            if (rights.isEmpty()) rights.add(null);
            for (TreeNode ll: lefts) {
                for (TreeNode rr: rights) {
                    TreeNode t = new TreeNode(i);
                    t.left = ll;
                    t.right = rr;
                    res.add(t);
                }
            }
        }

        dp[l-1][r-1] = res;
        return res;
    }

}