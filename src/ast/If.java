package ast;

import environment.Environment;

/**
 * A If object contains and executes SIMPLE If statements.
 * @author Jackelyn Shen
 * @version November 14, 2014
 */
public class If extends Statement
{
	private Expression ifstatement;
	private Statement thenstatement;
	private Statement elsestatement;
	
	/**
	 * Creates a If object which takes in a Expression representing the if
	 * condition, a Program representing the then execution, and
	 * another Program representing the else execution.
	 * @param ifs  the if Expression
	 * @param thens  the then Statement
	 * @param elses  the else Statement
	 */
	public If(Expression ifs, Statement thens, Statement elses)
	{
		ifstatement=ifs;
		thenstatement=thens;
		elsestatement=elses;
	}
	
	/**
	 * Executes the then Program if the If condition is valid, and if not, executes
	 * the else Program.
	 * @param env  the environment in which the HashMap is stored.
	 */
	public void exec(Environment env)
	{
		if(ifstatement.eval(env)==1)
		{
			if(thenstatement!=null)
			{
				thenstatement.exec(env);
			}
		}
		else
		{
			if(elsestatement!=null)
			{
				elsestatement.exec(env);
			}
		}
	}
}