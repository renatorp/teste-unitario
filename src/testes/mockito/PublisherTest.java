package testes.mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import classes.Publisher;
import classes.Subscriber;

@RunWith(MockitoJUnitRunner.class)
public class PublisherTest {

	@Mock
	public Subscriber s; //Não precisa ser interface
	
	@Test
	public void testSendMessage() {
		final String message = "Aweeee!!";

		Publisher p = new Publisher();
		p.setSubscriber(s);
		
		p.sendMessage(message);
		verify(s).receiveMessage(message);
	}
	
	@Test
	public void testGetStatusSubcriber() {
		Publisher p = new Publisher();
		p.setSubscriber(s);
		final IllegalArgumentException e = new IllegalArgumentException();
		
		doReturn(s).when(s).findById(3);
		doReturn(12).when(s).calcularStatus(5, 7);
		doThrow(e).when(s).makeComplicatedCalculations(24);
		
		when(s.getId()).thenReturn(3);
		//doReturn(3).when(s).getId(); //o mesmo
		
		assertEquals("Ops 24 3", p.getStatusSubcriber(3));;
	}

}
