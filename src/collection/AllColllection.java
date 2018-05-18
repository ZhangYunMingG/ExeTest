package collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class AllColllection {
	
	public static void main(String[] args)
	{
		
		new ArrayList<String>();
		new Vector<String>();
		new LinkedList<String>().iterator();
		new HashSet<String>();
		
		new HashMap<Integer, String>();
		new TreeMap<String, Integer>();
		new Hashtable<String, Integer>();
		new ConcurrentHashMap<String, Integer>();
	}

}
