package lib;

import java.util.Scanner;

import org.junit.Test;

import static org.junit.Assert.*;

import com.stefanmuenchow.arithmetic.Arithmetic;

public class GenericArithmaticTests
{
	@Test
	public void addTwoInt_returnsInt()
	{
		int a = 2;
		int b =3;
		int result = 5;
		Arithmetic ar = new Arithmetic<Number>(a);

		assertEquals(result, ar.add(b).value());
	}

	@Test
	public void addTwoFloat_returnsFloat()
	{
		float a = 2.4f;
		float b = 3.2f;
		float result = 5.6000004f;
		Arithmetic ar = new Arithmetic<Number>(a);

		assertEquals(result, ar.add(b).value());
	}

	@Test
	public void addTwoDouble_returnsDouble()
	{
		double a = 2;
		double b =3;
		double result = 5;
		Arithmetic ar = new Arithmetic<Number>(a);

		assertEquals(result, ar.add(b).value());
	}

	@Test
	public void addTwoStrings_returnsNumberInStringFormat()
	{
		String a =  "1";
		String b = "2";
		String result = "3";
		Scanner sc = new Scanner(a);
		//sc.n
		//Arithmetic ar = new Arithmetic<Number>();

	}
}
