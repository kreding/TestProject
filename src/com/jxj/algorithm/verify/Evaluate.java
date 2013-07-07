package com.jxj.algorithm.verify;

import com.jxj.algorithm.datastructure.Stack;
import com.jxj.algorithm.stdtools.StdIn;
import com.jxj.algorithm.stdtools.StdOut;

/**
 * Dijkstra的双栈算数表达式求值算法
 * @author kreding
 *
 */
public class Evaluate {

	public static void main(String[] args) {
		Stack<String> ops = new Stack<String>();
		Stack<Double> vals = new Stack<Double>();
		
		while( !StdIn.isEmpty() ) {
			String s = StdIn.readString();
			System.out.println("s:"+s);
			if(s.equals("(")){}
			else if(s.equals("+")) { ops.push(s); }
			else if(s.equals("-")) { ops.push(s); }
			else if(s.equals("*")) { ops.push(s); }
			else if(s.equals("/")) { ops.push(s); }
			else if(s.equals("sqrt")) { ops.push(s); }
			else if(s.equals(")")) {
				String op = ops.pop();
				Double v = vals.pop();
				
				if(op.equals("+")) { v = vals.pop() + v; }
				else if(op.equals("-")) { v= vals.pop() - v; }
				else if(op.equals("*")) { v= vals.pop() * v; }
				else if(op.equals("/")) { v= vals.pop() / v; }
				else if(op.equals("sqrt")) { v= Math.sqrt(v); }
				
				vals.push(v);
			}
			else {
				vals.push(Double.parseDouble(s));
			}
			
		}
		StdOut.println(vals.pop());
	}

}
