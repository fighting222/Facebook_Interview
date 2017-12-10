/**
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.
*/


//Dynamic Programming： Space = O(1), create int[] dp是constant length, 因为只有26个字母
//time = O(n)
/** I used a dp array of size n + 1 to save subproblem solutions. dp[0] means an empty string will have one way to decode, dp[1] means the way to decode a string of size 1. I then check one digit and two digit combination and save the results along the way. In the end, dp[n] will be the end result.
*
*但是还有一些其他的限制条件，比如说一位数时不能为0，两位数不能大于26，其十位上的数也不能为0，出去这些限制条件，根爬梯子基本没啥区别，也勉强算特殊的斐波那契数列，当然需要用动态规划Dynamci Programming来解。建立一位dp数组，长度比输入数组长度多2，全部初始化为1，因为斐波那契数列的前两项也为1，然后从第三个数开始更新，对应数组的第一个数。对每个数组首先判断其是否为0，若是将改为dp赋0，若不是，赋上一个dp值，此时相当如加上了dp[i - 1], 然后看数组前一位是否存在，如果存在且满足前一位不是0，且和当前为一起组成的两位数不大于26，则当前dp值加上dp[i - 2], 至此可以看出来跟斐波那契数组的递推式一样，http://www.cnblogs.com/grandyang/p/4313384.html
*/
class Solution {
    public int numDecodings(String s) {
    
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        //step1: state dp[i]表示从开始到i，有几种不同的解法。eg: dp[1] = 1表示从头开始1位数数，有一种不同的解法。dp[2]表示从头开始两位数，有几种不同的decode ways
        int[] dp = new int[s.length() + 1];   //因为从0 ~ n,所以是n + 1,
        
        //step2: initialize
//dp[0] is not the number of ways to decode an empty string, it is an initialization as strings with length 0 or 1 will not even enter the loop.
        //de[0]，一个都不包括，给一个初始值 = 1
        dp[0] = 1;
        //dp[1]，一个数字只有一个可能的解
        dp[1] = s.charAt(0) != '0' ? 1 : 0;  //先判断第一个数字是不是0，是的话就是无效的，返回0
        
        //step3: function：看小本本
        //总共解码方法：[i - 1] + [i - 2],最多到[i - 2]就是最多两个数字一个整体是因为字母，只有26个, 1 ~ 26
        //例如decode s = "12";
        // index i = 2从一个数的第二位开始进行计算，因为dp[0]和dp[1]都已经给了初始值
        for (int i = 2; i <= s.length(); ++i) {
            //处理conner case: 1204, index在4的位置，因为4的前一位是0，那么就是到0的位子：120|4 有[i - 1]中解法，然后单独看4
            if (s.charAt(i - 1) != '0') {  //if s.charAt(i) is 0, it can't be used as a single digit, so set c1 = 0  
                dp[i] = dp[i - 1];  //例如123，将3自己作为整体，有i - 1种方法（因为12是作为已经解好码的stirng）
            }
            
            //123，将23作为一个整体（有效数字10 ~ 26），有i-2种方法，
            //处理conner case:当前数位是0，例如1204，当前数位0不可以，他必须与前面的一个数“20”一起来看，1|20|4，20前面就是[i - 2]中解法
            int twoDigits = (s.charAt(i - 2) - '0') * 10 + s.charAt(i - 1) - '0';   //得到数字12
            if (twoDigits >= 10 && twoDigits <= 26) {   //有两种decode方法
                dp[i] += dp[i - 2];
            }
            //因为我们用两个变量dp[i] = dp[i - 1]和twoDigit，-》 space = O(1)
        }
        
        //step4: result，dp[n],所有数字都包括有几种不同的解法
        return dp[s.length()];  //返回s中n，所有数字都包括的情况下有多少种结果
    }
}


