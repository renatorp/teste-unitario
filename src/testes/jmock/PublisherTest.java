package testes.jmock;

import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import classes.Publisher;
import classes.Subscriber;

public class PublisherTest {

	@Rule
	public JUnitRuleMockery context = new JUnitRuleMockery();
	
	@Test
	public void testSendMessage() {
		final String message = "Aweeee!!";
		final Subscriber s = context.mock(Subscriber.class);

		Publisher p = new Publisher();
		p.setSubscriber(s);
		
		context.checking(new Expectations() {{
			oneOf(s).receiveMessage(message);
		}});
		
		p.sendMessage(message);
	}
	
	@Test
	public void testGetStatusSubcriber() {
		final Subscriber s = context.mock(Subscriber.class);
		
		Publisher p = new Publisher();
		p.setSubscriber(s);
		final IllegalArgumentException e = new IllegalArgumentException(); 
		
		context.checking(new Expectations() {{
			oneOf(s).findById(3);
			will(returnValue(s));
			
			oneOf(s).calcularStatus(5, 7);
			will(returnValue(12));
			
			oneOf(s).makeComplicatedCalculations(24);
			will(throwException(e));
			
			atLeast(1).of(s).getId();
			will(returnValue(3));
		}});
		
		assertEquals("Ops 24 3", p.getStatusSubcriber(3));
	}

}
