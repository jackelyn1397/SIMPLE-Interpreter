package interpreter;

import ast.Program;
import ast.Expression;
import ast.ExpOp;
import ast.BinOp;
import ast.If;
import ast.While;
import ast.Statement;
import ast.Assign;
import ast.Display;
import ast.Identifier;
import ast.Number;
import scanner.ScanErrorException;
import scanner.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Creates an Interpreter object to parse SIMPLE programs according to
 * the following overall grammar:
 * Program -> Statement P
 * P -> Program | e
 * Statement -> display Expression St1 | assign id = Expression |
 *              while Expression do Program end | if Expression then Program St2
 * St1 -> read id | e
 * St2 -> end | else Program end
 * Expression -> AddExpr whileExp
 * whileExp -> relop AddExpr | e
 * AddExpr -> MultExpr whileAdd
 * whileAdd -> + MultExpr whileAdd | - MultExpr whileAdd | e
 * MultExpr -> NegExpr whileMult
 * whileMult -> * NegExpr whileMult | / NegExpr whileMult | e
 * NegExpr -> -Value | Value
 * Value -> id | number | (Expression)
 * @author Jackelyn Shen
 * @version November 17, 2014
 *
 */
public class Interpreter
{
	Scanner scan;
    String currentToken;

    /**
     * Constructor for an Interpreter object.
     * Takes in a Scanner and stores it in an instance variable.
     * Call's the Scanner's next method and stores the returned String in another 
     * instance variable that represents the current token.
     * Catches a ScanErrorExpection if the next token cannot be scanned, then aborts
     * the program.
     * @param s  the Scanner to be set in an instance variable
     */
    public Interpreter(Scanner s)
    {
        scan=s;
        try
        {
            currentToken=scan.nextToken();
        }
        catch(ScanErrorException e)
        {
            System.out.println("Syntax error: Could not scan token.");
            System.exit(-1);
        }
    }
    
    /**
     * Gets the current token of the Scanner.
     * @return a String representing the current token
     */
    public String getToken()
    {
        return currentToken;
    }

    /**
     * Takes in a String representing the current token.
     * If the expected token matches the current token, asks Scanner for the next token
     * to store as the current token.
     * Catches a ScanErrorExpection if the next token cannot be scanned, then aborts
     * the program.
     * @throws IllegalArgumentException if expected token does not match current token
     * @param expected  the expected String for currentToken
     */
    public void eat(String expected)
    {
        if(expected.equals(currentToken))
        {
            try
            {
                currentToken=scan.nextToken();
            }
            catch(ScanErrorException e)
            {
                System.out.println(e);
                System.exit(-1);
            }
        }
        else
        {
            throw new IllegalArgumentException(
                "Illegal character - expected "+expected+" and found "+currentToken);
        }
    }

    /**
     * Handles display, assign, if, and while statements.
     * Statement -> display Expression St1 | assign id = Expression |
              while Expression do Program end | if Expression then Program St2
 	 * St1 -> read id | e
 	 * St2 -> end | else Program end
     * postcondition: all tokens associated with the statement have been eaten
     */
    public Statement parseStatement()
    {
        if(currentToken.equals("display"))
        {
            eat("display");
            Expression displayExp=parseExp();
            Statement read=null;
            if(currentToken.equals("read"))
        	{
        		eat("read");
        		String id=currentToken;
        		eat(currentToken);
        		System.out.println("Set "+id+" to: ");
        		BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
        		int input=0;
        		try
        		{
        			input=Integer.parseInt(b.readLine());
        		}
        		catch(IOException e)
        		{
        			System.out.println("User input not valid.");
        			System.exit(-1);
        		}
        		Number num=new Number(input);
        		read=new Assign(id,num);
        	}
            return new Display(displayExp, read);
        }
        else if(currentToken.equals("assign"))
        {
            eat("assign");
            String id=currentToken;
            eat(currentToken);
            eat("=");
            Expression assignExp=parseExp();
            return new Assign(id, assignExp);
        }
        else if(currentToken.equals("if"))
        {
            eat("if");
            Expression ifExp=parseExp();
            eat("then");
            Statement thenProgram=parseProgram();
            Statement elseProgram=null;
            if(currentToken.equals("else"))
            {
            	eat("else");
            	elseProgram=parseProgram();
            }
            eat("end");
            return new If(ifExp,thenProgram,elseProgram);

        }
        else if(currentToken.equals("while"))
        {
            eat("while");
            Expression whileExp=parseExp();
            eat("do");
            Statement doProgram=parseProgram();
            eat("end");
            return new While(whileExp,doProgram);
        }
        else
        {
        	return null;
        }
    }
    
    /**
     * If the current token is an integer, returns a Number containing the integer.
     * precondition: current token is an integer
     * postcondition: number token has been eaten
     * @return a Number object containing the parsed integer
     */
    public Expression parseNumber()
    {
        int num=Integer.parseInt(currentToken);
        eat(currentToken);
        return new Number(num);
    }

    /**
     * Evaluates a Value according to the following grammar:
     * Value -> id | number | (Expression)
     * postcondition: all tokens associated with the Value have been eaten
     * @return an Expression containing the evaluated Value
     */
    public Expression parseValue()
    {
        char first=currentToken.charAt(0);
        if(currentToken.equals("("))
        {
            eat("(");
            Expression exp=parseExp();
            eat(")");
            return exp;
        }
        else if(Scanner.isLetter(first))
        {
            String id=currentToken;
            eat(currentToken);
            return new Identifier(id);
        }
        else
        {
            return parseNumber();
        }
    }
    
    /**
     * Evaluates a NegExpr according to the following grammar:
     * NegExpr -> -Value | Value
     * postcondition: all tokens associated with the NegExpr have been eaten
     * @return an Expression containing the evaluated NegExpr
     */
    public Expression parseNegExpr()
    {
    	if(currentToken.equals("-"))
        {
            eat("-");
            return new BinOp(new Number(0),"-",parseValue());
        }
    	else
    	{
    		return parseValue();
    	}
    }
    

    /**
     * Evaluates a MultExpr according to the following grammar:
     * MultExpr -> NegExpr whileMult
     * whileMult -> * NegExpr whileMult | / NegExpr whileMult | e
     * postcondition: all tokens associated with the MultExpr have been eaten
     * @return an Expression containing the evaluated MultExpr
     */
    public Expression parseMultExpr()
    {
        Expression multExpr=parseNegExpr();
        while(currentToken.equals("*") || currentToken.equals("/"))
        {
        	String op=currentToken;
        	eat(currentToken);
            multExpr=new BinOp(multExpr,op,parseNegExpr());
        }
        return multExpr;
    }
    
    /**
     * Evaluates a AddExpr according to the following grammar:
     * AddExpr -> MultExpr whileAdd
     * whileAdd -> + MultExpr whileAdd | - MultExpr whileAdd | e
     * postcondition: all tokens associated with the AddExpr have been eaten
     * @return an Expression containing the evaluated AddExpr
     */
    public Expression parseAddExpr()
    {
    	Expression addExpr=parseMultExpr();
    	while(currentToken.equals("+") || currentToken.equals("-"))
    	{
    		String op=currentToken;
    		eat(currentToken);
    		addExpr=new BinOp(addExpr,op,parseMultExpr());
    	}
    	return addExpr;
    }

    /**
     * Evaluates an Expression according to the following grammar:
     * Expression -> AddExpr whileExp
     * whileExp -> relop AddExpr | e
     * postcondition: all tokens associated with the Expression have been eaten
     * @return an Expression containing the evaluated Expression
     */
    public Expression parseExp()
    {
        Expression exp=parseAddExpr();
        while(isRelop())
        {
        	String relop=currentToken;
        	eat(currentToken);
        	exp=new ExpOp(exp,relop,parseAddExpr());
        }
        return exp;
    }
    
    /**
     * Determines whether the current token is a relop according to the following grammar:
     * relop -> < | > | >= | <= | <> | =
     * @return true if currentToken is a relop; otherwise false
     */
    public boolean isRelop()
    {
    	if(currentToken.equals("=") || currentToken.equals("<>") || currentToken.equals("<")
    		|| currentToken.equals(">") || currentToken.equals("<=") || currentToken.equals(">="))
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    
    /**
     * Evaluates a Program according to the following grammar:
     * Program -> Statement P
     * P -> Program | e
     * @return a Statement containing the evaluated Program
     */
    public Statement parseProgram()
    {
    	Statement st=parseStatement();
    	if(!currentToken.equals("") && !currentToken.equals("else") && !currentToken.equals("end"))
    	{
    		return new Program(st, parseProgram());
    	}
    	return new Program(st, null);
    }
}
