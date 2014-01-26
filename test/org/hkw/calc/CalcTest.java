package org.hkw.calc;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalcTest {
	
	@Test
	public void testAdd()
	{
		assertEquals(3,Calc.add(1, 2));
	}	
	
	@Test
	public void testAdd1()
	{
		assertEquals(4,Calc.add(1, 3));
	}

}
