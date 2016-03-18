package interpreter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import scanner.Scanner;
import environment.Environment;

/**
 * Creates a Scanner and Interpreter object and parses a file containing SIMPLE expressions.
 * @author Jackelyn Shen
 * @version November 12, 2014
 */
public class InterpreterTester
{
    /**
     * Creates a FileInputStream object with a specific tester file to be
     * inputted into the Scanner.
     * Creates a variable HashMap with parameters String and Integer to pass into a new 
     * Environment used for the Interpreter.
     * Constructs a Scanner object and an Interpreter object to parse the text file
     * until the end of the file by parsing a Program and executing the resulting
     * abstract syntax tree.
     */
    public static void main(String[] args)
    {
        FileInputStream inStream=null;
        try
        {
            inStream=new FileInputStream(new File("./simple1.txt"));
        }
        catch(IOException e)
        {
            System.out.println("Not a valid file");
            System.exit(-1);
        }
        Scanner scan=new Scanner(inStream);
        HashMap<String,Integer> map=new HashMap<String,Integer>();
        Environment env=new Environment(map);
        Interpreter i=new Interpreter(scan);
        i.parseProgram().exec(env);
    }
}