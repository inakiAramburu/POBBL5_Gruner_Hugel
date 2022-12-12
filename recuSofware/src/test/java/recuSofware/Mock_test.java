package recuSofware;

import static org.junit.Assert.assertFalse;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.Before;
import org.junit.Test;

import org.junit.Test;

public class Mock_test extends EasyMockSupport{
	//Test the exam function using partial mocks and imagine that "squareCalc" and "getDigit" are under development
	exam2 exam;
	@Before
	public void setUp () {
		exam = partialMockBuilder(exam2.class).addMockedMethods("squareCalc", "getDigit").createMock();
	}
	
	@Test
	public void test() {
		EasyMock.expect(exam.squareCalc(2)).andReturn(4);
		EasyMock.expect(exam.getDigit(4)).andReturn(4);
		replayAll();
		assertFalse(exam.exam(2));
		verifyAll();		
	}

}
