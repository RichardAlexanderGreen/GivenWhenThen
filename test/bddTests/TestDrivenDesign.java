package bddTests;

import bdd.GivenWhenThen;  // TDD example of GivenWhenThen 

import junit.framework.TestCase;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestDrivenDesign extends TestCase {

	public void test01integerDivision()  {
				
		GivenWhenThen tdd = new GivenWhenThen(
				"Given: a is 2 and b is 3", 
				"When c is a divided by b", 
				new String[] {  "c is int 0",
						              "c is not 0.6666"
								    }
				) {
			
			Integer a, b, c;
			
			public void given( ) {
				a = 2;
				b = 3;
			}
			
			public void when() {
				c = a / b;
			}
			
			public boolean[] then() {
				boolean[] results = new boolean[2];
				
				results[0] = (c == 0);
				results[1] = (c != 0.666666);
				return results;
				
			}
		
	    };
	   //  tdd.showOnConsole();
	}
	
	public void test02newRationalNumber() {
		GivenWhenThen tdd = new GivenWhenThen(
				"Given: a is 2 and b is 3", 
				"When c is defined as a over b", 
				new String[] {  "c numerator is 2",
						              "c denominator is 3"
								    }
				) {
			
			Rational a, b, c;
			
			public void given( ) {
				a = new Rational( 2 );
				b = new Rational( 3 );
			}
			
			public void when() {
				c = new Rational( a, b );
			}
			
			public boolean[] then() {
				boolean[] results = new boolean[2];
				
				results[0] = (c.numerator == 2);
				results[1] = (c.denominator == 3);
				return results;
				
			}
		
	    };
	    tdd.showOnConsole();				 
	}
	
	
	
	
}
