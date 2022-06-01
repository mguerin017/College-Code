public class Tests {

    public int evaluate(int num1, int num2, char operator) {
        int x = 0;
        switch(operator) {
            case '+':
                x = num1 + num2;
                break;
            case '-':
                x = num1 - num2;
                break;
            case '*':
                x = num1 * num2;
                break;
            case '/':
                x = num1 / num2;
                break;
            default:
                System.out.println("Invalid parameters");
        }
        return x;
    }

    public void NameTest(String name) {
        charStack nameStack = new charStack();
        for(int i=0; i<name.length(); i++) {
            nameStack.push(name.charAt(i));
            System.out.println("push: " + nameStack.peek());
        }
        for(int i=0; i<name.length(); i++) {
            System.out.println("pop: " + nameStack.peek());
            nameStack.pop();
        }
    }

    public int EvaluationTest(String expression) {
        charStack operatorStack = new charStack();
        intStack numberStack = new intStack();
        for(int i=0; i<expression.length(); i++) {
            if(expression.charAt(i)==40 || expression.charAt(i)==91 || expression.charAt(i)==123) {}
            else if((48<=expression.charAt(i) && expression.charAt(i)<=57) && (48<=expression.charAt(i+1) && expression.charAt(i+1)<=57)) {
                char[] numberArray = {expression.charAt(i), expression.charAt(i+1)};
                String numberString = new String(numberArray);
                numberStack.push(Integer.parseInt(numberString));
                i++;
            }
            else if(48<=expression.charAt(i) && expression.charAt(i)<=57) {
                numberStack.push(Character.getNumericValue(expression.charAt(i)));
            }
            else if(expression.charAt(i)==43 || expression.charAt(i)==45 || expression.charAt(i)==42 || expression.charAt(i)==47) {
                operatorStack.push(expression.charAt(i));
            }
            else if(expression.charAt(i)==41 || expression.charAt(i)==93 || expression.charAt(i)==125) {
                char operator = operatorStack.peek();
                operatorStack.pop();
                int num2 = numberStack.peek();
                numberStack.pop();
                int num1 = numberStack.peek();
                numberStack.pop();
                numberStack.push(evaluate(num1, num2, operator));
            }
            else {
                System.out.println("No valid characters were detected.");
            }
        }
        return numberStack.peek();
    }
}
