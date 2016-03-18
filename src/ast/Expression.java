package ast;

import environment.Environment;

/**
 * An abstract Expression object creates the foundation for specific Expression
 * subclasses such as Number, Identifier, BinOp, and ExpOp.
 * @author Jackelyn Shen
 * @version November 14, 2014
 */
public abstract class Expression
{
	/**
	 * Creates an evaluation method for subclasses to modify.
	 * @param env  the environment in which the HashMaps are stored.
	 * @return 0, a default number
	 */
	public int eval(Environment env)
	{
		return 0;
	}
}
