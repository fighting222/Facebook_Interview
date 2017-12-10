/**题目：
*/
You are given coins of different denominations and a total amount of money amount. 
Write a function to compute the fewest number of coins that you need to make up that amount. 
If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
coins = [1, 2, 5], amount = 11
return 3 (11 = 5 + 5 + 1)

Example 2:
coins = [2], amount = 3
return -1.
//Method1: DP, time = O(n * amount), space = O(n)
/**思路：bottom-up
store the solutions of the already calculated subproblems in dp[]
dp[i] means the fewest number of coins when amount = i
dp[i] = min(dp[i], dp[i - coinsp[j]] + 1), 其中coins[j]为第j个硬币，那么凑齐钱数 amount 最少硬币数为：固定钱数为 coins[j] 一枚硬币，另外的钱数为 amount - coins[j] 它的数量为dp[amount - coins[j]] + 1（coin[j]）和当前dp数组中的值做比较，取较小的那个更新dp数组 -> coints[j] means the jth coin -> i - coins[j] means the rest amount after amount - coins[j], and we find the rest amount result in dp, then add 1
compare 
*/
class Solution {
    public int coinChange(int[] coins, int amount) {
        //base case
        if (coins == null || coins.length == 0) {
            return -1;
        }
        
        int max = amount + 1;
// dp[i] will be storing the number of solutions for value i. We need amount+1 rows as the dp is consturcted in bottom up manner using the base case (amount = 0)
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max); //fill all elements in dp[] to be max
        dp[0] = 0;
        
        for (int i = 1; i <= amount; ++i) {
            for (int j = 0; j < coins.length; ++j) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
