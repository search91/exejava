package com.learn.leetcode.medium;

import org.junit.Assert;

/**
 * 55. 跳跃游戏
 * 
 * 给定一个非负整数数组 nums，你最初位于数组的第一个下标。 数组中的每个元素代表你在该位置可以跳跃的最大长度。 判断你是否能够到达最后一个下标。
 *
 *
 * 45. 跳跃游戏 II 输出最少跳跃次数
 * 
 * 给你一个非负整数数组 nums，你最初位于数组的第一个位置。 数组中的每个元素代表你在该位置可以跳跃的最大长度。 你的目标是使用最少的跳跃次数到达数组的最后一个位置。 假设你总是可以到达数组的最后一个位置。求最小跳跃次数
 *
 * @author hzliuzhujie
 * @date 2021-08-11 不会做 看了下解题大概懂了，贪心算法
 **/
public class JumpGame {

    /**
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *  如果我们「贪心」地进行正向查找，每次找到可到达的最远位置，就可以在线性时间内得到最少的跳跃次数。
     *
     * 例如，对于数组 `[2,3,1,2,4,2,3]`，初始位置是下标 0，从下标 0 出发，最远可到达下标 2。下标 0 可到达的位置中，下标 1 的值是 3，从下标 1 出发可以达到更远的位置，因此第一步到达下标 1。
     *
     * 从下标 1 出发，最远可到达下标 4。下标 1 可到达的位置中，下标 4 的值是 4 ，从下标 4 出发可以达到更远的位置，因此第二步到达下标 4。
     *
     * 在具体的实现中，我们维护当前能够到达的最大下标位置，记为边界。我们从左到右遍历数组，到达边界时，更新边界并将跳跃次数增加 1。
     *
     * 在遍历数组时，我们不访问最后一个元素，这是因为在访问最后一个元素之前，我们的边界一定大于等于最后一个位置，否则就无法跳到最后一个位置了。如果访问最后一个元素，在边界正好为最后一个位置的情况下，我们会增加一次「不必要的跳跃次数」，因此我们不必访问最后一个元素。
     * @param nums
     * @return
     */
    public int canJump2(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                // 每次走到当前最大范围，才更新步数
                end = maxPosition;
                System.out.println(maxPosition);
                System.out.println(i);
                steps++;
            }
        }
        return steps;
    }

    public static void main(String[] args) {
        // true 可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
        /*  Assert.assertTrue(new JumpGame().canJump(new int[] {2, 3, 1, 1, 4}));
        
        // false 论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
        Assert.assertFalse(new JumpGame().canJump(new int[] {3, 2, 1, 0, 4}));
        
        // true
        Assert.assertTrue(new JumpGame().canJump(new int[] {5, 1, 0, 0, 0}));
        
        // false
        Assert.assertFalse(new JumpGame().canJump(new int[] {3, 1, 0, 0, 0}));
        
        // true
        Assert.assertTrue(new JumpGame().canJump(new int[] {1, 1, 1, 0}));
        
        // false
        Assert.assertFalse(new JumpGame().canJump(new int[] {0, 1, 1, 0}));
        
        // true
        Assert.assertTrue(new JumpGame().canJump(new int[] {0}));
        
        // true
        Assert.assertTrue(new JumpGame().canJump(new int[] {2, 0, 0}));
        
        // false
        Assert.assertFalse(new JumpGame().canJump(new int[] {1, 1, 0, 1}));*/

        // true
      /*  Assert.assertTrue(new JumpGame().canJump(new int[] {3, 0, 8, 2, 0, 0, 1}));

        Assert.assertSame(4, new JumpGame().canJump2(new int[] {1, 1, 1, 1, 0}));

        Assert.assertSame(2, new JumpGame().canJump2(new int[] {2, 3, 1, 1, 4}));

        Assert.assertSame(2, new JumpGame().canJump2(new int[] {2, 4, 4, 1, 4}));*/

        Assert.assertSame(2, new JumpGame().canJump2(new int[] {7, 0, 9, 6, 9, 6, 1, 7, 9, 0, 1, 2, 9, 0, 3}));

    }

}
