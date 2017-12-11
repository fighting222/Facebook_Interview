/**题目：
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
*/

/**做题之前需要问的问题：
1.There is only one solution or many?
2. Can I use the same elements?
3. Has Duplicate numbers?
*/

//Method1: Sort + 2 pointers: !!!!!这个版本不包含duplicates处理，如果重复元素，可能返回任意的index，并不一定是第一次出现
//O(nlogn) time, O(1) space
// since we the given array nums is not sorted, so we need to build a sorted array to get which two numbers added together = target, if nums is sorted, then we can return its indices directly
class Solution {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        
        int[] original = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            original[i] = nums[i];
        }
        
        Arrays.sort(nums);
        
        int start = 0, end = nums.length - 1;
        int num1 = -1, num2 = -1;
        while (start != end) {
            int sum = nums[start] + nums[end];
            if (sum == target) {
                num1 = nums[start];
                num2 = nums[end];
                break;              //必须要break！！！！！
            } else if (sum < target) {
                ++start;
            } else {
                --end;
            }
        }
        //find the indices of num1, num2 in the nums
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;
        for (int i = 0; i < original.length; ++i) {
            if (original[i] == num1 || original[i] == num2) {
                if (result[0] == -1) {
        //这里和九章不一样，不用i + 1是因为， 九章要求index is NOT zero based，但这道题目没有特殊要求
                    result[0] = i;
                } else {
                    result[1] = i;
                    break;
                }
            }
        }
        return result;
    }
}


//Method2: HashMap: O(n) time, O(n) space
//HashMap: key store the current number, value store the index of the current number
//Hashmap获取元素的时间复杂度是O(1)，所以总的时间复杂度仍不超过O(n)。
//思路： check target - current number 的差值是否在map里
class Solution {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;
        }
        
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; ++i) {
            if (map.containsKey(target - nums[i])) {
                result[0] = map.get(target - nums[i]);
                result[1] = i;
            } else {
                map.put(nums[i], i);
            }
        }
        return result;
    }
}



//follow up: Method3：Sorting with Two Pointers, 处理duplicates，返回数值结果，不是index
//time = O(nlogn), space = O(n)
private ArrayList<List<Integer>> twoSum(int[] nums, int target){
    int left = 0, right = nums.length - 1;
    ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
    while(left < right){
        if(nums[left] + nums[right] == target){
            ArrayList<Integer> curr = new ArrayList<Integer>();
            curr.add(nums[left]);
            curr.add(nums[right]);
            res.add(curr);
            do {
                left++;
            }while(left < nums.length && nums[left] == nums[left - 1]); //处理duplicates
            do {
                right--;
            } while(right >= 0 && nums[right] == nums[right + 1]);  //处理duplicates
        } else if (nums[left] + nums[right] > target){
            right--;
        } else {
            left++;
        }
    }
    return res;
}



//follow up: Method4: HashMap: with duplicates, 所有满足条件的index结果
public ArrayList<int[]> findNumbersThatSumToTarget(int[] arr, int target) {
    Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();  //set store index of the same number记录重复数字的index
    List<int[]> res = new ArrayList<int[]>();
    for(int i=0; i<arr.length; i++){
        if(!map.containsKey(arr[i])){
            Set<Integer> set = new HashSet<Integer>();  //处理duplicates
            set.add(i);   
            map.put(arr[i], set);
        }else{
            map.get(arr[i]).add(i);
        }

        if(map.containsKey(target - arr[i])){
            for(Integer j : map.get(target - arr[i])){
                if(j != i){
                int[] item = new int[2];
                item.add(i);
                item.add(j);
                res.add(item);
                }
            }
        }
    }
}
