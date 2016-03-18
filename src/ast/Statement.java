package ast;

import environment.Environment;

/**
 * An abstract Statement object creates the foundation for specific Statement
 * subclasses such as Display, Assign, If, While, and Program.
 * @author Jackelyn Shen
 * @version November 14, 2014
 */
public abstract class Statement
{
	/**
	 * Creates an execution method for subclasses to modify.
	 * @param env  the environment in which the HashMap is stored.
	 */
	public void exec(Environment env)
	{
		
	}
}
