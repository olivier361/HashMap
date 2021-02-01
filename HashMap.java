//HashMap.java
//Written by Olivier Gervais-Gougeon


import java.util.*;

public class HashMap<K extends Comparable<K>, V> implements Map<K,V> {

    private long			getLoops;
    private long			putLoops;
    private int       tableSize;

    private List< List<Entry<K,V>> > 	table;
    private int			count;

    public HashMap() {
        tableSize = 1000003; // prime number
        table = new ArrayList<List<Entry<K,V>>>(tableSize);
        // initializes table as a list of empty lists
        for (int i = 0; i < tableSize; i++)
            table.add(new LinkedList<Entry<K,V>>());
        count = 0;

        resetGetLoops();
        resetPutLoops();
    }

    public long getGetLoopCount() {
        return getLoops;
    }

    public long getPutLoopCount() {
        return putLoops;
    }

    public void resetGetLoops() {
        getLoops = 0;
    }
    public void resetPutLoops() {
        putLoops = 0;
    }
    
    /*
	 * Purpose:
	 * 	Check to see if key is stored in the map.
	 *
	 * Pre-conditions:
	 *	None.
	 *
	 * Returns:
	 * 	true if key is in the map, false otherwise.
	 *
	 */
    public boolean containsKey(K key) {
        // gets the index in the table this key should be in
        int index = Math.abs(key.hashCode()) % tableSize;
        // TODO... complete this method
        // Tip: use Java's List and Iterator operations to go through a chain at a specific index
	   
	   //gets the list at specified index in table
	   List<Entry<K,V>> list = table.get(index);
	   // gets an Iterator that can iterate over the above list
	   Iterator<Entry<K,V>> iter = list.iterator();
	   
	   while( iter.hasNext() == true ) {
		   // System.out.println(iter.next());
		   Entry<K,V> curEntry = iter.next();
		   if (curEntry.getKey().compareTo(key) == 0) {
			   return true;
		   }
	   }
        return false;
    }
    
    /*
	 * Purpose:
	 * 	Return the value stored at key in the map
	 *
	 * Pre-conditions:
	 * 	None.
	 *
	 * Returns:
	 *	the value stored at key.
	 *
	 * Throws:
	 *	KeyNotFoundException if key is not in the map.
	 *
	 */
    public V get (K key) throws KeyNotFoundException {
        // gets the index in the table this key should be in
        int index = Math.abs(key.hashCode()) % tableSize;
        // TODO... complete this method
        // Tip: use Java's List and Iterator operations to go through a chain at a specific index
	   
	   //gets the list at specified index in table
	   List<Entry<K,V>> list = table.get(index);
	   // gets an Iterator that can iterate over the above list
	   Iterator<Entry<K,V>> iter = list.iterator();
	   
	   // int testCount = 0;
	   // System.out.println("Looking at Index: " + index);
	   while( iter.hasNext() == true ) {
		   // System.out.println(iter.next());
		   // testCount++;
		   Entry<K,V> curEntry = iter.next();
		   if (curEntry.getKey().compareTo(key) == 0) {
			   // System.out.println("Found: K:" + curEntry.getKey() + " V:" + curEntry.getValue() );
			   return curEntry.getValue();
		   }
	   }
	   // System.out.println("Oops! I threw a KeyNotFoundException!");
	   // System.out.println("TestCount: " + testCount);
        throw new KeyNotFoundException();
    }
    
    /*
	 * Purpose:
	 * 	Return a List of Entry types which contain the
	 *	key/value pairs of every entry in the map.
	 *
	 * Pre-conditions:
	 * 	None.
 	 *
	 * Returns:
	 *	An instance of List with all the key/value pairs in
	 *	the map.
	 */
    public List<Entry<K,V> >	entryList() {
        List <Entry<K,V>> l = new LinkedList<Entry<K,V>>();
        // TODO... complete this method
        // Tip: use Java's List and Iterator operations to go through every index in the table
        //      use a second Iterator to go through each element in a chain at a specific index
        //      and add each element to l
	   for(int index = 0; index<tableSize; index++) {
		   //gets the list at specified index in table
		   List<Entry<K,V>> list = table.get(index);
		   // gets an Iterator that can iterate over the above list
		   Iterator<Entry<K,V>> iter = list.iterator();
		   
		   while( iter.hasNext() == true ) {
			   Entry<K,V> curEntry = iter.next();
			   l.add(curEntry);
		   }
	   }
        return l;
    }
    
    /*
	 * Purpose:
	 *	Insert the key/value pair into the map.
	 *	If the key already exists in the map, instead
	 *	update the entry to include the new value.
	 *
	 * Pre-conditions:
	 *	None.
	 *
	 * Examples:
	 *	if m is {("hello", 5)} and m.put("joe",8") is called
	 *	then m is: {("hello", 5), ("joe",8)}
	 *
	 *	if m is {("hello", 5)} and m.put("hello", 99) is called
	 *	then m is {("hello", 99)}
	 *
	 *	NOTE: 	Maps do not provide ordering, so these examples
	 * 		have chosen an arbitrary ordering.  Your implementation
	 *		may store items in a different order than the examples.
	 */
    public void put (K key, V value){
        // gets the index in the table this key should be in
        int index = Math.abs(key.hashCode())%tableSize;
        // TODO... complete this method
        // Tip: use Java's List and Iterator operations to go through a chain at a specific index
        //  if key is found, update value.  if key is not found add a new Entry with key,value
	   
	   //gets the list at specified index in table
	   List<Entry<K,V>> list = table.get(index);
	   // gets an Iterator that can iterate over the above list
	   Iterator<Entry<K,V>> iter = list.iterator();
	   
	   Entry<K,V> curEntry;
	   while( iter.hasNext() == true ) {
		   // System.out.println(iter.next());
		   curEntry = iter.next();
		   if (curEntry.getKey().compareTo(key) == 0) {
			   curEntry.setValue(value);
			   return;
		   }
	   }
	   Entry<K,V> newEntry = new Entry<K,V>(key, value);
	   list.add(newEntry);
	   count++;
	   // System.out.println("Just put Entry: K:" + newEntry.getKey() + " V:" + newEntry.getValue() + " at index: " + index);
    }

    public int size() {
        return count;
    }

    public void clear() {
        table.clear();
        count = 0;
    }

}
