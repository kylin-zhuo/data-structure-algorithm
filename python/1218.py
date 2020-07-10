"""
longest arithmetic subsequence of given difference

arr = [1,5,7,8,5,3,4,2,1], diff = -2
ans = 4

"""

def longest_arith_subseq(arr, diff):
    
    n = len(arr)
    if n == 1:
        return 1
    
    dp = [0] * n
    map = {arr[0]: 1}

    dp[0] = 1
    
    res = 1
    
    for i in range(1, n):
        target = arr[i] - diff
        if target not in map:
            dp[i] = 1
            map[arr[i]] = 1
        else:
            dp[i] = map[target] + 1
            map[arr[i]] = dp[i]
        res = max(res, dp[i])



    return res


if __name__ == '__main__':

    arr = [1,5,7,8,5,3,4,2,1]
    diff = -2

    print(longest_arith_subseq(arr, diff))


