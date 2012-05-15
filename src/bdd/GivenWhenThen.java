package bdd;

import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

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
		if( thenDescriptions.length != results.length ) { fail ("Number of results does not match descriptions."); }
		
		for( int i = 0; i < thenTexts.length; i++ ) {
			Assert.assertTrue( thenTexts[i], results[i] );
		};
		// Cascade to showOnConsole() if desired.
	}
	/**
	 * Call this to display test on console
	 */
	public void showOnConsole() {
		// Print Given and When
		System.out.println( "Given: " + givenDescription );
		System.out.println( "When: " + whenDescription );		
		// Print results
		for( int i = 0; i < thenDescriptions.length; i++ ) {
			String label = results[i] ? "SUCCESS: " : "FAILURE: ";
			System.out.println( label + thenDescriptions[i] );
		}
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
	
}
