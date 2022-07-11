
//This class implements an efficient scheme for compressing a text message called Huffman Coding
//We will take a text file and, using a Huffman Tree, encode/decode its message 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*; 

public class HuffmanConverter 
{
	//VARIABLES 
	
	//the # of chars in the ASCIi table dictates
	//the size of the count[] and code[] arrays 
	public static final int NUMBER_OF_CHARACTERS = 256; 
	
	//the contents of our message 
	private String contents; 
	
	//the tree created from the msg 
	private HuffmanTree huffmanTree; 
	
	//tracks how often each character occurs 
	private int count[]; 
	
	//the huffman code for each character 
	private String code[]; 
	
	//stores the # of unique characters in contents 
	//private int uniqueChars = 0;  //optional 
	
	//CONSTRUCTOR 
	
	//takes input string to be converted
	public HuffmanConverter(String input) 
	{
		this.contents = input; 
		this.count = new int[NUMBER_OF_CHARACTERS]; 
		this.code = new String[NUMBER_OF_CHARACTERS]; 
	}
	
	//METHODS 
	
	/*
	 * Records the frequencies that each character of our message occurs... 
	 * We use contents to fill up the count[] list...
	 */
	public void recordFrequencies()
	{
		int length = contents.length(); 
		for(int i = 0; i < length; i++)
		{
			int c = (int)contents.charAt(i); 
			count[c] += 1; 
		}
	}
	
	/*
	 * converts out frequency list into a Huffman Tree. We do this by taking our count[]
	 * list of frequencies, and creating a binary heap in a manner similar to how a heap 
	 * was made in HuffmanTree's fileToHeap method. Then, we print the heap, and make a call
	 * to HuffmanTree.heapToTree() (createFromHeap)method to get our much desired HuffmanTree object, which
	 * we store as HuffmanTree
	 */
	public void frequenciesToTree()
	{
		//create binary heap of frequency huffman nodes
		BinaryHeap<HuffmanNode> heap = new BinaryHeap<HuffmanNode>(); 
		for(int i = 0; i < NUMBER_OF_CHARACTERS; i++)//iterate over legal characters 
		{
			if(count[i]!=0) //frequency > 0 
			{
				//Need a string (letter/character) to pass into HuffmanNode
				String letter = "" + (char)i; //(char)i does not work alone (not a string) 
				//Need a double (frequency) to pass into HuffmanNode
				Double frequency = (double)count[i]; 
				//create node
				HuffmanNode node = new HuffmanNode(letter,frequency); 
				//insert node into heap  
				heap.insert(node); 
			}
		}
		heap.printHeap(); //print heap
		huffmanTree = HuffmanTree.createFromHeap(heap); //create HuffmanTree object
	}
	
	/*
	 * interates over the HuffmanTree to get the code for each letter. The code for 
	 * letter i gets stored as code[i]... This method behaves similarly Huffman's tree printLegend()
	 * method... 
	 * Warning: Don't forget to initialize each code[i] to ""
	 * BEFORE calling the recursive version of treeToCode...
	 */
	public void treeToCode()
	{
		//initializes code[i] = ""; 
		for(int i = 0; i< NUMBER_OF_CHARACTERS; i++) //iterate over code[i]
		{
			code[i] = "";  
		}
		//iterate over tree by calling method below 
		treeToCode(huffmanTree.root,""); 
		
		
	}
	/*
	 * ​A​ ​private​ ​method​ ​to​ ​iterate​ ​over​ ​a​ ​HuffmanNode​ ​t​ ​using​ ​s,​ ​which
​	 *​ ​contains​ ​what​ ​we​ ​know​ ​of​ ​the​ ​HuffmanCode​ ​up​ ​to​ ​node​ ​t.​ ​This​ ​is
​	 *​ ​called​ ​by​ ​treeToCode(),​ ​and​ ​resembles​ ​the​ ​recursive​ ​printLegend
	 ​*​ ​method​ ​in​ ​the​ ​HuffmanTree​ ​class.​ ​Note​ ​that​ ​when​ ​t​ ​is​ ​a​ ​leaf​ ​node, 
	 ​*​ ​t's​ ​letter​ ​tells​ ​us​ ​which​ ​index​ ​i​ ​to​ ​access​ ​in​ ​code[],​ ​and​ ​tells ​
	 *​ ​us​ ​what​ ​to​ ​set​ ​code[i]​ ​to...
	 */
	private void treeToCode(HuffmanNode t, String s)
	{
		//if t contains multiple characters 
		if(t.letter.length() > 1) 
		{ 
			//t is not a leaf node 
			//recursively call treeToCode() on its left child 
			treeToCode(t.left, s + "1"); 
			//recursively call treeToCode() on its right child 
			treeToCode(t.right, s + "0");
		}
		//if t.letter is a single character 
		else if(t.letter.length() == 1)
		{
			//t is a leaf node 
			code[(int)t.letter.charAt(0)] = s; 

		}
	}
	
	/*
	 * Reads in the message stored in contents, and the huffman conversions stored 
	 * in code[], we create the Huffman encoding for our message 
	 * (a String of 0's and 1's), and return it... 
	 */
	public String encodeMessage()
	{
		String encodedMessage = ""; //temp
		int length = contents.length(); 
		for(int i = 0; i<length; i++) //iterates over contents 
		{
			//iterate over contents and then adds its huffman encoding to a string 
			encodedMessage = encodedMessage + code[(int)contents.charAt(i)]; 
		}
		//format to add a line break after a certain amount of characters 
		/*String temp = "";
		for(int i = 0; i < encodedMessage.length();i++)
		{
			if(i > 0 && (i%100 == 0))
			{
				temp = temp + "\n"; 
			}
			temp = temp+ encodedMessage.charAt(i);
		}*/
		return encodedMessage; 
	}

	/*
	 * Reads in the contents of the file names filename and returns it as a String. 
	 * The main method calls this method on args[0]... 
	 */
	public static String readContents(String filename)
	{
		//throw necessary exception 
		String temp = "";
		String exception = ""; 
		try 
		{
			File file = new File(filename); 
			Scanner scanner = new Scanner(file); 
			while(scanner.hasNextLine()) 
			{
				//add each line of text in the file to a string
				temp = temp + scanner.nextLine(); 
				temp = temp + "\n"; 
			}
			scanner.close(); 
			//return the string containting content of file 
			return temp;
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		return exception; //in case it cannot read the file 
	}
	
	/*
	 * Using the encoded String argument, and the huffman codings,
	 * recreate the original message from our huffman encoding and return it...
	 */
	public String decodeMessage(String encodedStr)
	{
		String decodedMessage = ""; //temp 
		HuffmanNode temp = huffmanTree.root; //(begin at root) 
		System.out.println("Decoded message: "); 
		for(int i = 0; i < encodedStr.length(); i++)
		{
			int j = Integer.parseInt(String.valueOf(encodedStr.charAt(i))); //find value at each character of encoded string
			if(j==1) //if the value of the character at index i is 1									
			{
				temp = temp.left; //go to roots left child 
				if(temp.left == null && temp.right == null) //leaf? 
				{
					decodedMessage = decodedMessage + temp.letter; 
					temp = huffmanTree.root; 
				}
			}
			if(j==0) //if the value of the character at the indec i is 0;
			{
				temp = temp.right; //go to right child 
				if(temp.left == null && temp.right == null) //leaf?
				{
					decodedMessage = decodedMessage + temp.letter; 
					temp = huffmanTree.root; 
				}
			}
		}
		
		return decodedMessage; 
	}
	
	
	
	//MAIN METHOD 
	/*
	 * Uses args[0] as the filename, and reads in its contents. Then instantiated a HuffmanConverter
	 * object, using its methods to obtain our results and orint the necessary output. Finally, 
	 * decode the message and compare it to the input file.<p> 
	 */
	public static void main(String args[])
	{		
		String contents = HuffmanConverter.readContents(args[0]); //uses args[0] as filename and reads contents
		
		HuffmanConverter huffmanConverter = new HuffmanConverter(contents); //Instantiates H.C. object 
		
		//use HuffmanConcerter methos to obtain our results and print necessary output 
		huffmanConverter.recordFrequencies(); 
			//Output for this program should be: (in the following order)
				//The initial contents of the heap before converting to the HuffmanTree 
				System.out.println(""); 
				System.out.println("Contents of heap before converting to the HuffmanTree: "); 
				System.out.println(""); 
					huffmanConverter.frequenciesToTree();
				//The Huffman encodings for all letters 
					//A simple call to the huffman tree's printLegend() method is fine
				System.out.println(""); 
				System.out.println("Huffman Encodings: (for all letters)"); 
				//System.out.println(""); 
					huffmanConverter.huffmanTree.printLegend(); 
				//The encoded version of the message using the Huffman Encoding you found
				System.out.println(""); 
				System.out.println("Encoded Message: "); 
					huffmanConverter.treeToCode(); 
					String encodedMessage = huffmanConverter.encodeMessage(); 
					System.out.println(encodedMessage); 
					//Followed by the # of bits needed to encode the message in ASCII coding 
						//which is contents.length() times 8 
						System.out.println(""); 
						System.out.println("Message size in ASCII encoding: "+ huffmanConverter.contents.length()*8);
					//Followed by the # of bits needed to encode the message using huffman coding 
						//which is the length of the String returned by the encodedMessage() method
						System.out.println("Message size in Huffman coding: " + encodedMessage.length()); 
						
				//Print the decoded message, which should match exactly (character for character) the original msg
				System.out.println("");
				System.out.println(huffmanConverter.decodeMessage(encodedMessage)); 
				System.out.println("");
		
	}
}


















