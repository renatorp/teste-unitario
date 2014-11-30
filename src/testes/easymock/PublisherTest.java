package testes.easymock;

import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.MockType;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import classes.Publisher;
import classes.Subscriber;

@RunWith(EasyMockRunner.class) 
public class PublisherTest extends EasyMockSupport {

	//1 - Create the mock
	@Mock(fieldName = "subscriber", type = MockType.STRICT)
	Subscriber s; //Não precisa ser interface, não precisa ser público
	
	@TestSubject
	Publisher p = new Publisher(); //2 - Have it set to the tested class
	
	@Test
	public void testSendMessage() {
		final String message = "Aweeee!!";
		
		//3 - Record what we expect the mock to do
		s.receiveMessage(message); 	

		//4 - Tell all mocks we are now doing the actual testing
		replay(s); 					
		
		//5 - Test
		p.sendMessage(message);  

		//6 - Make sure everything that was supposed to be called was called
		verify(s);					
	}
	
	@Test
	public void testGetStatusSubcriber() {
		final IllegalArgumentException e = new IllegalArgumentException();
		
		//3 - Record what we expect the mock to do
		expect(s.findById(3)).andReturn(s);
		expect(s.calcularStatus(5, 7)).andReturn(12);
		s.makeComplicatedCalculations(24);
		expectLastCall().andThrow(e);
		expect(s.getId()).andReturn(3).atLeastOnce();
		
		//4 - Tell all mocks we are now doing the actual testing
		replayAll();
		
		//5 - Test
		assertEquals("Ops 24 3", p.getStatusSubcriber(3));
		
		//6 - Make sure everything that was supposed to be called was called
		verifyAll(); //Para todos os mocks. Nesse caso só tem um mesmo
	}

}
