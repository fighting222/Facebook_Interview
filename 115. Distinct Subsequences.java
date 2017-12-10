/**题目：
Given a string S and a string T, count the number of distinct subsequences of S which equals T.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Here is an example:
S = "rabbbit", T = "rabbit"

Return 3.


*/

//DP, time = O(mn), space = O(mn)
/**思路：
1. 状态：i, j分别表示和S中长度为j的prefix：S[0:i-1], T中长度为i的prefix：T[0:j-1]
DP[i][j]：means that S[0..i-1] contains T[0..j-1] that many times as distinct subsequences。显然如果i<j，DP[i][j] = 0。

2. 递推公式：
(a) S[i-1]!=T[j-1]:
T = r a b
S = r c a c b c

DP[i][j] = DP[i - 1][j]

The first case is easy to catch. When the new character in s, s[i-1] is not equal with the head char in t, t[j-1], we can no longer increment the number of distinct subsequences, it is the same as the situation before incrementing the t, so dp[i][j] = dp[i-1][j]

(b) S[i-1] = T[j-1]: when the new incrementing character in s, s[i-1] is equal with t[j-1], which contains two case

T = r a b b
S = r a b b b  - DP[i - 1][j] = 1 (1)We don't match those two characters, which means that it still has original number of distinct subsequences,
S = r a b b b  - DP[i - 1][j - 1] = 2  (2)We match those two characters, in this way
S = r a b b b  /

DP[i][j] = DP[i - 1][j] + DP[i - 1][j - 1]

3. 计算方向和起始状态：
DP[i][j]
DP[i+1][j]   DP[i+1][j+1]

所以从上向下，从左到右的顺序可以计算。

根据计算顺序，需要先设置第0行、0列的值。
第0列：DP[i][0] = 0，i>0。因为T的长度大于S的长度，不可能成为S的subsequence。
第0行：DP[0][j] = 1，j>=0。这是为了保证第1行的计算正确：
*/
class Solution {
    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();
    
        int[][] dp = new int[n+1][m+1];
    
        for (int i = 0; i < n+1; i++) {
            dp[i][0] = 1;
        }
    
        for (int j = 1; j < m+1; j++) {
            dp[0][j] = 0;
        }
    
        for (int j = 1; j < m+1; j++) {
            for (int i = 1; i < n+1; i++) {
                dp[i][j] = dp[i-1][j];
                if (s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] += dp[i-1][j-1];
                }   
            }
        }
    
    return dp[n][m];
    }
}
