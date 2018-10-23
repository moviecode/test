package com.itaiti.lambda;

import java.util.function.Function;

import org.junit.Test;

public class FunctionTest {
	
	@Test
	public void demo() {
		Function<Double, Double> f = x -> x + 1.0;
		Function<Double, Double> g = x -> x * 2.0;
		// andThen 先执行f,在执行g
		Function<Double, Double> h = f.andThen(g);
		double result = h.apply(1.1);
		System.out.println("result:" + result);
		// compose 先执行g在执行f
		// g(f(x)) 		g:1.2*2.0=2.4	f:2.4+1.0=3.4
		result = f.compose(g).apply(1.2);
		System.out.println(result);
	}
}
