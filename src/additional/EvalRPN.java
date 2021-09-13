package additional;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 150. 逆波兰表达式求值
 * @author Murphy Xu
 * @create 2021-09-13 14:44
 */
public class EvalRPN {

    /**
     * 栈
     * 5ms, 92.96%; 38 MB,78.16%
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList<>();
        int n = tokens.length;
        for (int i = 0; i < n; i++) {
            String token = tokens[i];
            if (isNumber(token)) {
                stack.push(Integer.parseInt(token));
            }else {
                int num2 = stack.pop();
                int num1 = stack.pop();
                switch (token){
                    case "+":
                        stack.push(num1 + num2);
                        break;
                    case "-":
                        stack.push(num1 - num2);
                        break;
                    case "*":
                        stack.push(num1 * num2);
                        break;
                    case "/":
                        stack.push(num1 / num2);
                        break;
                }
            }
        }
        return stack.pop();
    }

    private boolean isNumber(String token) {
        return !("+".equals(token) || "-".equals(token)
        || "*".equals(token) || "/".equals(token));
    }

    @Test
    public void test(){
        String[] tokens = {"2","1","+","3","*"};
        System.out.println(evalRPN(tokens));
    }
}
