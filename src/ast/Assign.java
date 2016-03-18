package ast;

import environment.Environment;

/**
 * An Assign object organizes and evaluates assignments.
 * @author Jackelyn Shen
 * @version November 14, 2014
 */
public class Assign extends Statement
{
	private String var;
	private Expression exp;
	
	/**
	 * Creates an Assign object and takes in a String identifier and the Expression
	 * to be assigned.
	 * @param v  the String identifier
	 * @param e  the Expression to be assigned to the variable
	 */
	public Assign(String v, Expression e)
	{
		var=v;
		exp=e;
	}
	
	/**
	 * Adds the variable and its Expression to the HashMap.
	 * @param env  the environment in which the HashMap is stored
	 */
	public void exec(Environment env)
	{
		env.setVariable(var, exp.eval(env));
	}
}
