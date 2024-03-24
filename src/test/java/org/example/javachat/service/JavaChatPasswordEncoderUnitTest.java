package org.example.javachat.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JavaChatPasswordEncoderUnitTest {

    private JavaChatPasswordEncoder javaChatPasswordEncoder = new JavaChatPasswordEncoder();
    @Test
    void checkMatches() throws Exception {
        assertTrue(javaChatPasswordEncoder
                .matches("password1",
                        "$2a$10$3ht.deamX2JzO01YWuEQ2eVE5GbPiDWkiXF9n2Tk3LJB.I.GT0Suu"));

        assertTrue(javaChatPasswordEncoder
                .matches("password2",
                        "$2a$10$KH1ubOy9tYqAuuW.PcoFUuPJq.jf0HFilbhnVncm9S0q5zPSo.z66"));

        assertTrue(javaChatPasswordEncoder
                .matches("password3",
                        "$2a$10$jzFRxrzC870uZKkVviOvhuWJt0zSFKNvs2J4XJ9kmOVXaTfpX9sgK"));
    }

}