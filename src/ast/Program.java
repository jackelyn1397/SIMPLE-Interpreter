package ast;

import environment.Environment;

/**
 * Organizes programs and program statements and becomes the root of the abstract syntax tree of
 * the SIMPLE Interpreter.
 * @author Jackelyn Shen
 * @version November 1, 2014
 */
public class Program extends Statement
{
	private Statement stmt;
	private Statement program;
	
	/**
	 * Creates a Program object which takes in a Statement to be executed and another Program,
	 * setting both to instance variables.
	 * @param s  the Statement to be set to an instance variable
	 * @param p  the Program to be set to an instance variable
	 */
	public Program(Statement s, Statement p)
	{
		stmt=s;
		program=p;
	}
	
	/**
	 * Executes the Statement and, if the Program is not null, executes the Program.
	 * @param env  the environment in which the HashMap is stored
	 */
	public void exec(Environment env)
	{
		stmt.exec(env);
		if(program!=null)
		{
			program.exec(env);
		}
	}
}
