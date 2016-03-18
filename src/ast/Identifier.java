package ast;

import environment.Environment;

/**
 * An Identifer object contains information for variables.
 * @author Jackelyn Shen
 * @version November 14, 2014
 */
public class Identifier extends Expression
{
	private String name;
	
	/**
	 * Creates an Identifier object and sets the String variable to an instance variable.
	 * @param n  the name of the variable
	 */
	public Identifier(String n)
	{
		name=n;
	}
	
	/**
	 * Returns the integer value of the variable in the HashMap.
	 * @param env  the environment in which the HashMap is stored
	 * @return the integer value of the variable
	 */
	public int eval(Environment env)
	{
		return env.getVariable(name);
	}
}
