
@SuppressWarnings("rawtypes")
public class HuffmanNode implements Comparable 
{
	public String letter; 
	public Double frequency; 
	public HuffmanNode left, right; 
	
	//Inherited method from Comparable interface
	/*
	 * Casts Object o into a HuffmanNode object called "huff" 
	 * Compare this.frquency to new object (huff's) frequency 
	 */
	public int compareTo(Object o) 
	{
		HuffmanNode huff = (HuffmanNode) o; 
		int test = Double.compare(this.frequency, huff.frequency); 
		int compare = this.frequency.compareTo(huff.frequency); 
		return compare; 
	}
	
	//METHODS
	
	/*
	 * This CONSTRUCTOR creates a new HuffmanNode 
	 *letter is set to this.letter and frequency is set to this.frequency
	 *Left and right are set to null
	 */
	public HuffmanNode(String letter,Double frequency)
	{
		this.letter = letter; 
		this.frequency = frequency; 
		left = null; 
		right = null; 
	}
	/*
	 * This CONSTRUCTOR creates a new HuffmanNode from its two children 
	 *The two children passed are the children of the new node 
	 *Sets letter variable to the concatenation of left.letter and right.letter
	 *Sets frequency as the sum of the frequencies of the two nodes 
	 */
	public HuffmanNode(HuffmanNode left, HuffmanNode right)
	{
		letter = left.letter + right.letter; 
		frequency = left.frequency + right.frequency; 
		this.left = null; 
		this.right = null; 
	}
	
	/*
	 * This method returns a string that contains the letter and frequency 
	 */
	public String toString() 
	{
		//String format = Double.toString(frequency); 
		String LF = "<" + letter + ", " + frequency + ">"; 
		return LF; 
		
	}

}
