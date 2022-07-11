
public class HuffmanTree 
{
	//main method is now in the HuffmanConverter class!
	/*public static void main(String[] args) 
	{
		//Call legendToHeap on the legend string and return a Binary Heap (Bheap) 
		BinaryHeap bheap = legendToHeap("A 20 E 24 G 3 H 4 I 17 L 6 N 5 O 10 S 8 V 1 W 2"); 
		
		System.out.println("Heap:"); //formatting addition 
		
		//print the heap 
		bheap.printHeap(); 
		//call createFromHeap() to run the huffman algorithm which returns a tree (htree)
		HuffmanTree htree = createFromHeap(bheap); 
		
		System.out.println("Binary encodings of each letter: "); //formatting addition
		
		////print the binary encodings for each of the letters 
		htree.printLegend(); 
	}*/

	HuffmanNode root; 
	
	//METHODS
	
	/*
	 * This CONSTRUCTOR sets this.root to huff
	 * Turns final Huffman node into a Huffman tree object
	 */
	public HuffmanTree(HuffmanNode huff)
	{
		this.root = huff; 
	}
	
	/*
	 * Calls printLegend(root, "") 
	 */
	public void printLegend()
	{
		printLegend(root, ""); 
	}
	/*
	 * Called by the above method 
	 */
	private void printLegend(HuffmanNode t, String s)
	{
		//if t contains multiple characters 
		if(t.letter.length() > 1) 
		{
			
			//t is not a leaf node 
			//recursively call printLegend() on its left child 
			printLegend(t.right, s + "0"); 
			//recursively call printLegen() on its right child 
			printLegend(t.left, s + "1");
		}
		//if t.letter is a single character 
		if(t.letter.length() == 1)
		{
			//t is a leaf node 
			System.out.println("'" + t.letter + "'" + " = " + s);
			
		}
	}
	/*
	 * Converts string to binary heap 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static BinaryHeap legendToHeap(String legend)
	{
		//create new binary heap 
		BinaryHeap bheap = new BinaryHeap();
		HuffmanNode n; //node that will be added to the heap
		
		if(legend.length() == 0)
		{
			n = new HuffmanNode(" ", 0.0); 
		}
		else 
		{
			String[] parts = legend.split(" "); //use split method to help organize the legend
			
			for(int i = 0; i < parts.length; i++)
			{
				String letter = parts[i];
				i = i+1; //move over one position to access the frequency 
				double frequency =  Double.valueOf(parts[i]); //convert the string frequency to a double 
				n = new HuffmanNode(letter,frequency); //create the huffman node for each letter and frequency
				bheap.insert(n); //add the huffman node to the heap 
				
			} 
		}
		return bheap; 
		
	}
	
	//RUN HUFFMAN ALGORITHM HERE 
	/*
	 * 2. ​ ​While​ ​the​ ​Binary​ ​Heap​ ​has​ ​more​ ​than​ ​one​ ​element:
	a. Remove​ ​the​ ​two​ ​nodes​ ​with​ ​minimum​ ​frequency.
	b. Create​ ​a​ ​new​ ​HuffmanNode​ ​with​ ​those​ ​minimum​ ​frequency​ ​nodes​ ​as​ ​children
	(using​ ​the​ ​the​ ​HuffmanNode​ ​constructor​ ​with​ ​left​ ​and​ ​right​ ​nodes​ ​as​ ​parameters)
	and​ ​insert​ ​that​ ​node​ ​into​ ​the​ ​BinaryHeap.
	3. The​ ​BinaryHeap’s​ ​only​ ​element​ ​will​ ​be​ ​the​ ​root​ ​of​ ​the​ ​Huffman​ ​Tree.​ ​Pass​ ​this​ ​node​ ​into
	the​ ​HuffmanTree​ ​constructor​ ​and​ ​return​ ​the​ ​result.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static HuffmanTree createFromHeap(BinaryHeap b)
	{
		HuffmanNode n = new HuffmanNode(" ", 0.0); //temp
		while(b.getSize() != 1)
		{
			HuffmanNode left = (HuffmanNode) b.deleteMin(); //create new child node with minFrequency node
			HuffmanNode right = (HuffmanNode) b.deleteMin(); //create new child node with minFrequency node
			n = new HuffmanNode(left, right);//create new node with left and right children 
			n.left = left; 
			n.right = right; 
			b.insert(n);  
		}
		//pass the only element of the binary heap (the roots) into the HuffmanTree constructor 
		HuffmanTree root = new HuffmanTree((HuffmanNode)b.findMin()); 
		//return the tree 
		return root; 
	}


}
