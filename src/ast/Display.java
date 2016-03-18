package ast;

import environment.Environment;

/**
 * A Display object contains and executes SIMPLE display statements.
 * @author Jackelyn Shen
 * @version October 17, 2014
 */
public class Display extends Statement
{
	private Expression exp;
	private Statement read;
	
	/**
	 * Creates a Display object and sets the expression to be printed out and the
	 * read identifier to instance variables.
	 * @param e  the expression to be displayed
	 * @param r  the identifier to be set
	 */
	public Display(Expression e, Statement r)
	{
		exp=e;
		read=r;
	}
	
	/**
	 * Prints out the display Expression and executes the read assignment.
	 * @param env  the environment in which the HashMap is stored
	 */
	public void exec(Environment env)
	{
		System.out.println(exp.eval(env));
		if(read!=null)
		{
			read.exec(env);
		}
	}
}