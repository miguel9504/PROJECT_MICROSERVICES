package com.company.client_service.model.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClientTest {

    @Test
    public void testClientInheritanceAndProperties() {
        // Arrange
        Client client = new Client();
        
        // Act - Set properties from Person (Parent)
        client.setName("Jose Lema");
        client.setGender("Male");
        client.setAge(30);
        client.setIdentification("1234567890");
        client.setAddress("Otavalo sn y principal");
        client.setPhone("098254785");
        
        // Act - Set properties from Client (Child)
        client.setClientId("jose.lema");
        client.setPassword("1234");
        client.setStatus(true);

        // Assert
        Assertions.assertEquals("Jose Lema", client.getName());
        Assertions.assertEquals("Male", client.getGender());
        Assertions.assertEquals(30, client.getAge());
        Assertions.assertEquals("1234567890", client.getIdentification());
        Assertions.assertEquals("jose.lema", client.getClientId());
        Assertions.assertTrue(client.getStatus());
        
        // Verify it is instance of Person
        Assertions.assertTrue(client instanceof Person);
    }
}
