package com.MoreiraJunior.desafio;

import com.MoreiraJunior.desafio.processors.InputProcessor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InputProcessorTest {

    private InputProcessor inputProcessor = new InputProcessor();
    private static String data1;
    private static String data2;
    private static String data3;
    private static String data4;

    @BeforeAll
    public static void setUp(){
        data1 = "src/test/resources/temp1.dat";
        data2 = "src/test/resources/temp2.dat";
        data3 = "src/test/resources/temp4.dat";
        data4 = "src/test/resources/temp6.dat";
    }

    @Test
    public void shouldReadData() throws IOException {
        assertTrue(inputProcessor.treatFile(data1));
    }

    @Test
    public void shouldReadDataWithBlanksBetweenData() throws IOException {
        assertTrue(inputProcessor.treatFile(data2));
    }

    @Test
    public void shouldNotReadIncompleteData() throws IOException{
        assertFalse(inputProcessor.treatFile(data3));
    }

    @Test
    public void shouldNotReadBadlyFormattedData() throws IOException{
        assertFalse(inputProcessor.treatFile(data4));
    }
}
