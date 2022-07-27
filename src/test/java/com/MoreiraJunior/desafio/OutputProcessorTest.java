package com.MoreiraJunior.desafio;

import com.MoreiraJunior.desafio.processors.InputProcessor;
import com.MoreiraJunior.desafio.processors.OutputProcessor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OutputProcessorTest {

    private OutputProcessor outputProcessor = new OutputProcessor();
    private InputProcessor inputProcessor = new InputProcessor();
    private static String data1;
    private static String data2;
    private static String data3;
    private static String data4;
    private static String data5;

    @BeforeAll
    public static void setUp(){
        data1 = "src/test/resources/temp1.dat";
        data2 = "src/test/resources/temp2.dat";
        data3 = "src/test/resources/temp4.dat";
        data4 = "src/test/resources/temp6.dat";
        data5 = "src/test/resources/teste1.dat";
    }

    @Test
    public void shouldGenerateFileStatsCorrectly() throws IOException {
        String fileName = "temp1.dat";
        inputProcessor.treatFile(data1);
        assertEquals(2, outputProcessor.amountOfClients(fileName));
        assertEquals(2, outputProcessor.amountOfSalesmen(fileName));
        assertEquals("10", outputProcessor.mostExpensiveSale());
        assertEquals("Renato", outputProcessor.worstSalesmanEver());
    }

    @Test
    public void shouldGenerateOverallStatsCorrectly() throws IOException {
        String fileName = "teste1.dat";
        inputProcessor.treatFile(data5);
        assertEquals("1354", outputProcessor.mostExpensiveSale());
        assertEquals("Renato", outputProcessor.worstSalesmanEver());
    }

    @Test
    public void shouldNotGenerateResultForCompromisedData() throws IOException {
        String fileName = "temp6.dat";
        inputProcessor.treatFile(data4);
        assertThrows(RuntimeException.class, () -> outputProcessor.amountOfClients(fileName));
    }
}
