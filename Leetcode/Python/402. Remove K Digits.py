class Solution(object):
    def removeKdigits(self, num, k):
        """
        :type num: str
        :type k: int
        :rtype: str
        """
        if num is None or num == "" or len(num) <= k:
            return "0"

        stack = []
        for i, c in enumerate(num):
            n = ord(c) - ord('0')
            while stack:
                if stack[-1] > n and len(num) - i + len(stack) > len(num) - k:
                    stack.pop()
                else:
                    break
            stack.append(n)

        result = ''.join(str(c) for c in stack)
        idx = 0
        for c in result:
            if c != '0':
                break
            idx += 1

        return "0" if idx == len(result) else result[idx:min(len(num), len(num) - k)]

# pythonic version
class Solution(object):
    def removeKdigits(self, num, k):
        """
        :type num: str
        :type k: int
        :rtype: str
        """
        if num is None or num == "" or len(num) <= k:
            return "0"

        stack = []
        for n in num:
            while stack and k and stack[-1] > n:
                stack.pop()
                k -= 1
            stack.append(n)
        print(-k)
        return ''.join(stack[:-k or None]).lstrip('0') or "0"
