package recuSofware;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import org.junit.Test;

public class AllTest {

	@RunWith(Suite.class)
	@SuiteClasses({ Mock_test.class, Parameter_test.class })
	public void test() {
		
	}

}
