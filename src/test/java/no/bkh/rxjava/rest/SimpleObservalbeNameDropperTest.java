package no.bkh.rxjava.rest;

import org.apache.log4j.Logger;
import org.junit.Test;

public class SimpleObservalbeNameDropperTest {

	Logger logger = Logger.getLogger(getClass());
	SimpleObservableNameDropper sond = new SimpleObservableNameDropper();

	@Test
	public void testHello() {
		String sayHello = sond.sayHello(new String[] { "Bjarte", "Odin" });
		logger.debug(sayHello);
	}

}	
