package ast;

import environment.Environment;

/**
 * A Number object contains integer values.
 * @author Jackelyn Shen
 * @version November 14, 2014
 */
public class Number extends Expression
{
	private int number;
	
	/**
	 * Creates a Number object and sets the integer value to an instance variable.
	 * @param num  the integer value to be set
	 */
	public Number(int num)
	{
		number=num;
	}
	
	/**
	 * Returns the integer value.
	 * @param env  the environment in which the HashMap is stored
	 * @return the instance variable number
	 */
	public int eval(Environment env)
	{
		return number;
	}
}
