package environment;

import java.util.HashMap;

/**
 * An Environment object encompasses the HashMap that stores and links 
 * certain integers to their String variables.
 * @author Jackelyn Shen
 * @version October 17, 2014
 */

public class Environment
{
	HashMap<String,Integer> map;
	
	/**
	 * Creates an Environment object and initializes the HashMap.
	 * @param m  the HashMap to be set to the instance variable map
	 */
	public Environment(HashMap<String,Integer> m)
	{
		map=m;
	}
	
	/**
	 * Adds a variable and its value to the HashMap.
	 * @param variable  the variable added to the HashMap
	 * @param value  the value of the variable added to the HashMap
	 */
	public void setVariable(String variable, int value)
	{
		map.put(variable,value);
	}
	
	/**
	 * Gets the integer value of a specific String in the HashMap.
	 * @param variable  the String whose value is returned
	 * @return the integer value of the variable
	 */
	public int getVariable(String variable)
	{
		return map.get(variable);
	}
}
