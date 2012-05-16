GivenWhenThen
=============

"Given When Then" Behavior Driven Development - implemented in Java and Smalltalk.

I volunteered to do a demonstration of Given When Then coding.
As a business analyst working in a large corporate IT environment, 
I've used this format to write requirements for others to implement.
But I've not used it directly in my own coding (I code mainly in Smalltalk).

For the demonstration, I've provided two implementations.
One implementation in Smalltalk and one in Java.
Both implementations subclass the existing TestCase class
so that they run under the XUnit framework in the IDE
and you get "red" and "green" automatically.

Incidentally, I've looked over cuke4duke and JBehave,
but chose to create my own Java implementation [1]
because those implemenations had too much "magic"
to suit my taste.

[1] with a little help from my friends Nate and Amber at 
<a href="https://twitter.com/#!/CraftsmanGuild"> CraftsmanGuild</a>.

Since Smalltalk has closures, 
the Smalltalk implementation allows you to write relatively readable code.
For example:
<pre>
test01SimpleHappyPath
  "Given: a = 2, b = 3; when:  c := a / b, then: c is rational number 2/3, c is not a float"
	| a b c |
	self given: [ a := 2. b:= 3.] 
	       when: [ c := a / b] 
	        then: [ c = (2/3) and: [ ( c isKindOf: Float)  not] ].
</pre>

But Java does not have closures (yet), 
so I had to work a little harder,
and the the resulting TestCase code is a little less readable.

<pre>
public void test01integerDivision()  {
  			
		GivenWhenThen tdd = new GivenWhenThen(
				"Given: a is 2 and b is 3", 
				"When c is a divided by b", 
				new String[] {  "c is int 0", "c is not 0.6666" } )   {
        
			Integer a, b, c;
			
			public void given() { a = 2; b = 3; }
			public void when()  { c = a / b; }	
			public boolean[] then() {
				boolean[] results = new boolean[2];
				
				results[0] = (c == 0);
				results[1] = (c != 0.666666);
				return results;	
			}	
	 };
}
</pre>

In Smalltalk, implementing Given-When-Then is simple.
You simply subclass the SUnit class TestCase and add the following method.
SUnit will do everything else that is needed.
<pre>
given: givenBlock when: whenBlock then: thenBlock
	"Implement simple BDD Given []: When: [] Then: [] "
	|  |
	givenBlock value.
	whenBlock value.
	self assert: thenBlock value.
</pre>

In Java, the trick is to provide an abstract class 
with abstract methods that the programmer overrides in the test code, 
as seen above.
Again we subclass TestCase in JUnit.
But this time the implementation is a little more complex.
<pre>
public class GivenWhenThen  extends TestCase {
	
	String givenDescription;
	String whenDescription;
	String[] thenDescriptions;
	boolean[] results;
	
	/**
	 * Create an instance of a Given-When-Then test
	 * @param givenText - Describe your given (context / precondition state) in  English.
	 * @param whenText - Describe your when (event / additional precondition).
	 * @param thenTexts - Describe each post-condition test.  (Match to results from then() method call.)
	 */
	 public  GivenWhenThen( String givenText, 
			                              String whenText, 
			                              String[] thenTexts ) 
	{		
		this.givenDescription = givenText;
		this.whenDescription = whenText;
		this.thenDescriptions = thenTexts;
		
		// Run the test
		this.given();
		this.when();
		
		results = this.then();
		
		// Does the program match the documentation ?
		if( thenDescriptions.length != results.length ) { 
                        fail ("Number of results does not match descriptions."); 
                }
		
		for( int i = 0; i &lt; thenTexts.length; i++ ) {
			Assert.assertTrue( thenTexts[i], results[i] );
		};
	}
	
	/**
	 * Override this to create your "Given" block
	 */
	public void given(  )   { }
	
	/**
	 * Override this to implement your "When" block
	 * @param o
	 */
	public void when( )   { }
	
	/**
	 * Override this to implement your "Then" block.
	 * @param s
	 * @param b
	 * @return
	 */
	public boolean[]  then() {
		boolean[] dummy = { false };
		return dummy;
	}

	/**
	 * Call this to display test results on the console
	 */
	public void showOnConsole() {
		// Print Given and When
		System.out.println( "Given: " + givenDescription );
		System.out.println( "When: " + whenDescription );		
		// Print results
		for( int i = 0; i &lt; thenDescriptions.length; i++ ) {
			String label = results[i] ? "SUCCESS: " : "FAILURE: ";
			System.out.println( label + thenDescriptions[i] );
		}
	}
}
</pre>