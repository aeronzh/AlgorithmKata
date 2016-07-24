public class Solution {
    /**
     * @param stack an integer stack
     * @return void
     */
    public void stackSorting(Stack<Integer> stack) {
        // Write your code here
        if (stack == null || stack.size() == 0) {
            return;
        }

        // decreasing stack
        Stack<Integer> deStack = new Stack<Integer>();

        while (!stack.isEmpty()) {
            if (deStack.isEmpty()) {
                deStack.push(stack.pop());
            } else {
                if (stack.peek() > deStack.peek()) {
                    int temp = stack.pop();
                    while (!deStack.isEmpty() && deStack.peek() <= temp) {
                        stack.push(deStack.pop());
                    }
                    stack.push(temp);
                    while (!deStack.isEmpty()) {
                        stack.push(deStack.pop());
                    }
                } else {
                    deStack.push(stack.pop());
                }
            }
        }

        while (!deStack.isEmpty()) {
            stack.push(deStack.pop());
        }
    }
}
