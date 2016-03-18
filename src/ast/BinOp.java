package ast;

import environment.Environment;

/**
 * A BinOp object organizes and evaluates operations.
 * @author Jackelyn Shen
 * @version November 14, 2014
 */
public class BinOp extends Expression
{
	private Expression lefthand;
	private String operator;
	private Expression righthand;
	
	/**
	 * Creates a BinOp object and takes in two expressions and their determining operator.
	 * @param left  the leftmost Expression
	 * @param op  the operator
	 * @param right  the rightmost Expression
	 */
	public BinOp(Expression left, String op, Expression right)
	{
		lefthand=left;
		operator=op;
		righthand=right;
	}
	
	/**
	 * Evaluates the operation between the two expressions.
	 * Operators include +, -, *, /.
	 * @param env  the environment in which the HashMap is stored
	 * @return  an integer value representing the evaluated operation
	 */
	public int eval(Environment env)
	{
		if(operator.equals("+"))
		{
			return lefthand.eval(env)+righthand.eval(env);
		}
		else if(operator.equals("-"))
		{
			return lefthand.eval(env)-righthand.eval(env);
		}
		else if(operator.equals("*"))
		{
			return lefthand.eval(env)*righthand.eval(env);
		}
		else
		{
			return lefthand.eval(env)/righthand.eval(env);
		}
	}
}
