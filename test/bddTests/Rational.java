package bddTests;

import com.sun.tools.internal.ws.processor.model.java.JavaArrayType;

@SuppressWarnings("serial")
public class Rational  {
	
	protected long numerator = 1L;
	protected long denominator = 1L;
	
	public Rational( long aLong ) {
		this.numerator = aLong;
		this.denominator = 1L;
	}
	
	public Rational( long aNumerator, long aDenominator ){
		this.numerator = aNumerator;
		this.denominator = aDenominator;
	}
	public Rational( int aLong ) {
		this.numerator = aLong;
	}
	
	public Rational( int aNumerator, int aDenominator ){
		this.numerator = aNumerator;
		this.denominator = aDenominator;
	}
	public Rational( Rational aNumerator, Rational aDenominator ){
		this.numerator = aNumerator.numerator * aDenominator.denominator;
		this.denominator = aNumerator.denominator * aDenominator.numerator;
	}

}
