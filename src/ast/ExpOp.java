package ast;

import environment.Environment;

/**
 * A ExpOp object organizes and determines relop operations.
 * @author Jackelyn Shen
 * @version November 14, 2014
 */
public class ExpOp extends Expression
{
	private Expression lefthand;
	private String relop;
	private Expression righthand;
	
	/**
	 * Creates a ExpOp object and takes in two expressions and their determining relop.
	 * @param left  the leftmost Expression
	 * @param op  the relop operator
	 * @param right  the rightmost Expression
	 */
	public ExpOp(Expression left, String op, Expression right)
	{
		lefthand=left;
		relop=op;
		righthand=right;
	}
	
	/**
	 * Evaluates whether or not the condition is valid based on the relop between
	 * two expressions.
	 * Relops include =, <>, <, >, <=, >=.
	 * @param env  the environment in which the HashMap is stored.
	 * @return 1 if the condition is valid; otherwise 0
	 */
	public int eval(Environment env)
	{
		if(relop.equals("="))
		{
			if(lefthand.eval(env)==righthand.eval(env))
			{
				return 1;
			}
			return 0;
		}
		else if(relop.equals("<>"))
		{
			if(lefthand.eval(env)!=righthand.eval(env))
			{
				return 1;
			}
			return 0;
		}
		else if(relop.equals("<"))
		{
			if(lefthand.eval(env)<righthand.eval(env))
			{
				return 1;
			}
			return 0;
		}
		else if(relop.equals(">"))
		{
			if(lefthand.eval(env)>righthand.eval(env))
			{
				return 1;
			}
			return 0;
		}
		else if(relop.equals("<="))
		{
			if(lefthand.eval(env)<=righthand.eval(env))
			{
				return 1;
			}
			return 0;
		}
		else if(relop.equals(">="))
		{
			if(lefthand.eval(env)>=righthand.eval(env))
			{
				return 1;
			}
			return 0;
		}
		return 0;
	}
}
