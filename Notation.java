/**
 * Author: Edward Gregg
 * Class: CMSC 204 FAL2022
 * 
 * 
 * 
 * 
 * 
 */


public class Notation {

	private static MyQueue<String> theQueue;
	  private static MyStack<String> theStack;
	  private final static String operators = "+-*/";
	  
	  /**
	   * 
	   * @return top of the stack
	   */
	  private static String topStack() {
		    try {
		      return theStack.top();
		    } catch (StackUnderflowException e) {
		      e.getMessage();
		    }
		    return null;
		  }
	  
	  /**
	   * 
	   * @return top of the stack
	   */ 
	  private static String popStack() {
		    try {
		      return theStack.pop();
		    } catch (StackUnderflowException e) {
		      e.getMessage();
		    }
		    return null;
		  }
	  
	  /**
	   * pushes to the stack
	   * @param c
	   * @return
	   */
	  private static boolean stackPush(String c) {
		    try {
		      return theStack.push(c);
		    } catch (StackOverflowException e) {
		      e.getMessage();
		    }
		    return false;
		  }
	  
	  /**
	   * adds to the queue with enqueue
	   * @param c
	   * @return
	   */
	  private static boolean enToTheQueue(String c) {
		    try {
		      return theQueue.enqueue(c);
		    } catch (QueueOverflowException e) {
		      e.getMessage();
		    }
		    return false;
		  }
	  
	  /**
	   * removes from the queue with dequeue
	   * @return
	   */
	  private static String dequeue() {
		    try {
		      return theQueue.dequeue();
		    } catch (QueueUnderflowException e) {
		      e.getMessage();
		    }
		    return null;
		  }
	  
	  /**
	   * calculates the precedence of the character with * and / taking priority
	   * @param c
	   * @return
	   */
	  private static int calculatePrec(char c) {
		    if (c == '*' || c == '/') {
		      return 1;
		    } else if (c == '+' || c == '-') {
		      return 0;
		    }
		    return -1;
		  }
	 
	  /**
	   * attempts to fix the order of the chracters and then returns the string
	   * @param first
	   * @param second
	   * @param operator
	   * @return
	   * @throws InvalidNotationFormatException
	   */
	  private static String applyOperator(String first, String second, char operator) throws InvalidNotationFormatException {
		    double a = Double.parseDouble(first);
		    double b = Double.parseDouble(second);
		    switch (operator) {
		      case '+':
		        return Double.toString(a + b);
		      case '-':
		        return Double.toString(a - b);
		      case '*':
		        return Double.toString(a * b);
		      case '/':
		        if (b == 0)
		          throw new InvalidNotationFormatException();
		        return Double.toString(a / b);
		    }
		    return null;
		  }
	  
	  
	  /**
	   * In the infixToPostfix method, you MUST use a queue for the internal structure that holds the postfix solution. Then use the toString method of the Queue to return the solution as a string.
	   * @param complexInfix
	   * @return
	   * @throws InvalidNotationFormatException
	   */
	  public static String convertInfixToPostfix(String complexInfix) throws InvalidNotationFormatException {
		    theQueue = new MyQueue<String>();
		    theStack = new MyStack<String>();

		    for (int i = 0; i < complexInfix.length(); i++) {
		      char charCurrent = complexInfix.charAt(i);
		      if (charCurrent == ' ') {
		        continue;
		      } else if (Character.isDigit(charCurrent)) {
		        enToTheQueue(Character.toString(charCurrent));
		      } else if (charCurrent == '(') {
		        stackPush(Character.toString(charCurrent));
		      } else if (operators.indexOf(charCurrent) >= 0) {
		        while (!theStack.isEmpty() && calculatePrec(topStack().charAt(0)) >= calculatePrec(charCurrent)) {
		          enToTheQueue(popStack());
		        }
		        stackPush(Character.toString(charCurrent));
		      } else if (charCurrent == ')') {
		        char top = popStack().charAt(0);
		        while (top != '(') {
		          enToTheQueue(Character.toString(top));
		          if (theStack.isEmpty()) {
		            throw new InvalidNotationFormatException();
		          } else {
		            top = popStack().charAt(0);
		          }
		        }
		      }
		    }
		    while (!theStack.isEmpty()) {
		      enToTheQueue(popStack());
		    }
		    return theQueue.toString();
		  }
	  /**
	   * 
	   * @param complexPostfix
	   * @return
	   * @throws InvalidNotationFormatException
	   */
	  public static String convertPostfixToInfix(String complexPostfix)
		      throws InvalidNotationFormatException {
		    theStack = new MyStack<String>();
		    for (int i = 0; i < complexPostfix.length(); i++) {
		      char charCurrent = complexPostfix.charAt(i);
		      if (charCurrent == ' ') {
		        continue;
		      } else if (Character.isDigit(charCurrent)) {
		        stackPush(Character.toString(charCurrent));
		      } else if (operators.indexOf(charCurrent) >= 0) {
		        String a = popStack().toString(), b, tmp;
		        if (theStack.isEmpty()) {
		          throw new InvalidNotationFormatException();
		        } else {
		          b = popStack().toString();
		          tmp = '(' + b + charCurrent + a + ')';
		          stackPush(tmp);
		        }
		      }
		    }
		    if (theStack.size() != 1) {
		      throw new InvalidNotationFormatException();
		    }
		    return popStack();
		  }
	  
	  /**
	   * this is the main algorithm for notation, from the notes if it finds a space then ignores it, if it finds a left parentheses, it pushes it to the stack
	   * @param complexPostfix
	   * @return
	   * @throws InvalidNotationFormatException
	   */
	  public static double evaluatePostfixExpression(String complexPostfix) throws InvalidNotationFormatException {
		    theStack = new MyStack<String>();
		    for (int i = 0; i < complexPostfix.length(); i++) {
		      char charCurrent = complexPostfix.charAt(i);
		      if (charCurrent == ' ') {
		        continue;
		      } else if (Character.isDigit(charCurrent) || charCurrent == '(') {
		        stackPush(Character.toString(charCurrent));
		      } else if (operators.indexOf(charCurrent) >= 0) {
		        String a = popStack().toString(), b;
		        String result;
		        if (theStack.isEmpty()) {
		          throw new InvalidNotationFormatException();
		        } else {
		          b = popStack().toString();
		          result = applyOperator(b, a, charCurrent);
		          stackPush(result);
		        }
		      }
		    }
		    if (theStack.size() != 1) {
		      throw new InvalidNotationFormatException();
		    }
		    return Double.parseDouble(popStack());
		  }

		  /**
		   * Evaluates an infix expression from a string to a double
		   * 
		   * @param infixExpr - the infix expression in String format
		   * @return the evaluation of the infix expression as a double
		   */
		  public static double evaluateInfixExpression(String infixExpr) {
		    String postfixExpression = convertInfixToPostfix(infixExpr);
		    return evaluatePostfixExpression(postfixExpression);
		  }
	  
	  
}
