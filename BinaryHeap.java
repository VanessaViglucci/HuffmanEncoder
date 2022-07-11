
@SuppressWarnings("rawtypes")
public class BinaryHeap<E extends Comparable>
{
	//the heap class as posted 
	public BinaryHeap( )
    {
        this( DEFAULT_CAPACITY );
    }
	@SuppressWarnings("unchecked")
	public BinaryHeap( int capacity )
    {
        currentSize = 0;
        array = (E[]) new Comparable[ capacity + 1 ];
    }
	@SuppressWarnings("unchecked")
	public BinaryHeap( E [ ] items )
    {
      currentSize = items.length;
      array = (E[]) new Comparable[ ( currentSize + 2 ) * 11 / 10 ];
      
      int i = 1;
      for( E item : items )
          array[ i++ ] = item;
      buildHeap( );
	}
	@SuppressWarnings("unchecked")
	public void insert( E x )
    {
        if( currentSize == array.length - 1 )
            enlargeArray( array.length * 2 + 1 );

            // Percolate up
        int hole = ++currentSize;
        for( ; hole > 1 && x.compareTo( array[ hole / 2 ] ) < 0; hole /= 2 )
            array[ hole ] = array[ hole / 2 ];
        array[ hole ] = x;
    }
	@SuppressWarnings("unchecked")
	private void enlargeArray( int newSize )
    {
	E [] old = array;
		array = (E []) new Comparable[ newSize ];
		      for( int i = 0; i < old.length; i++ )
			       array[ i ] = old[ i ];	  
    }
	public E findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return array[ 1 ];
    }
	public E deleteMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );

        E minItem = findMin( );
        array[ 1 ] = array[ currentSize-- ];
        percolateDown( 1 );

        return minItem;
    }
	private void buildHeap( )
    {
        for( int i = currentSize / 2; i > 0; i-- )
            percolateDown( i );
    }
	 public boolean isEmpty( )
	{
	       return currentSize == 0;
	}
	 public void makeEmpty( )
	{
	       currentSize = 0;
	}
	 public void printHeap( )
	{
	    for( int i = 1; i <= currentSize; i++ )
	    	System.out.print( array[ i ] + " " );
	    		
	    System.out.println( );
	}
	 
	private static final int DEFAULT_CAPACITY = 100;

	private int currentSize;      // Number of elements in heap
	private E [ ] array; // The heap array
	
	@SuppressWarnings("unchecked")
	private void percolateDown( int hole )
    {
        int child;
        E tmp = array[ hole ];

        for( ; hole * 2 <= currentSize; hole = child )
        {
            child = hole * 2;
            if( child != currentSize &&
                    array[ child + 1 ].compareTo( array[ child ] ) < 0 )
                child++;
            if( array[ child ].compareTo( tmp ) < 0 )
                array[ hole ] = array[ child ];
            else
                break;
        }
        array[ hole ] = tmp;
    }
	
	//Added get size method 
	public int getSize()
	{
		return currentSize; 
	}
	
}
