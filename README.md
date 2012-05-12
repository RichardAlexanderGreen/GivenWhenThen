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
  "Given: a = 2, b = 3; when:  c := a + b, then: c == 5"
	| a b c |
	self given: [ a := 4. b:= 6.] 
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
