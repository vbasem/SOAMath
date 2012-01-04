package tests.functional;

import static org.junit.Assert.*;

import java.io.Closeable;
import java.io.IOException;

import org.junit.Test;

public class TestArithmaticServer {

	// @WebServiceRef(wsdlLocation =
	// "http://192.168.56.1:8080/VirtualSOA/RegistryAndLookUpService?wsdl")
	private services.arithmatic.AdditionServiceService service;

	@Test
	public void performArithmaticOperation() {
		assertEquals(5, add(2, 3));
	}

	protected int add(int x, int y) {
		service = new services.arithmatic.AdditionServiceService();

		services.arithmatic.AdditionService port =  service.getAdditionServicePort();
		int additionResult = port.calculate(x, y);

		try {
			((Closeable) port).close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return additionResult;
	}
}
