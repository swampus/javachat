package org.example.javachat.service.factory;

import org.example.javachat.model.User;
import org.example.javachat.service.JavaChatPasswordEncoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserFactoryUnitTest {

    @Mock
    private JavaChatPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserFactory userFactory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        String username = "testUser";
        String password = "testPassword";
        String encodedPassword = "encodedPassword";
        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);

        User user = userFactory.createUser(username, password);

        assertEquals(username, user.getUsername());
        assertEquals(encodedPassword, user.getPassword());
        verify(passwordEncoder, times(1)).encode(password);
    }
}