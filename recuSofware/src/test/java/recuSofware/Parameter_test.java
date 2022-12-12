package recuSofware;

import static org.junit.Assert.assertFalse;


import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith (value = Parameterized.class)
public class Parameter_test {

	int n;
	
	
	
	@Parameters
	public static Collection<Object []> numbers() {
		return Arrays.asList(new Object [] [] {
			{2},
			{25},
			{6}
		});
	}
	
	public Parameter_test (int n) {
		this.n = n;
	
	}
	
	@Test
	public void test() {
		exam2 exam2 = new exam2();
		assertFalse(exam2.exam(n));
	}

}
