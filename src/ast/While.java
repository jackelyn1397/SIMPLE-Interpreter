package ast;

import environment.Environment;

/**
 * A While object contains and executes SIMPLE While statements.
 * @author Jackelyn Shen
 * @version October 17, 2014
 */
public class While extends Statement
{
	private Expression whilestatement;
	private Statement dostatement;
	
	/**
	 * Creates a While object which takes in a expression representing the while
	 * condition and a Program representing the do execution.
	 * @param whiles  the while Expression
	 * @param dos  the do Statement
	 */
	public While(Expression whiles, Statement dos)
	{
		whilestatement=whiles;
		dostatement=dos;
	}
	
	/**
	 * Executes the do Program while the while condition is valid.
	 * @param env  the environment in which the HashMap is stored.
	 */
	public void exec(Environment env)
	{
		while(whilestatement.eval(env)==1)
		{
			if(dostatement!=null)
			{
				dostatement.exec(env);
			}
		}
	}
}
