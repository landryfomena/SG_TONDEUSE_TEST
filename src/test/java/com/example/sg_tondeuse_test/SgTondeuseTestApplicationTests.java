package com.example.sg_tondeuse_test;

import com.example.sg_tondeuse_test.domain.Entry;
import com.example.sg_tondeuse_test.domain.Mow;
import com.example.sg_tondeuse_test.repository.DataRepositoryUtils;
import com.example.sg_tondeuse_test.service.MowerServiceUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class SgTondeuseTestApplicationTests {


    @Test
    public void testReadAndProcessFile() {
        // Get the test file path from the resources
        URL resourceUrl = getClass().getClassLoader().getResource("data.txt");
        assertNotNull(resourceUrl, "The test file was not found");

        try {
            File testFile = new File(resourceUrl.toURI());
            Entry entry = DataRepositoryUtils.getData(testFile.getAbsolutePath());

            assertNotNull(entry, "The entry should not be null");
            assertNotNull(entry);
            assertEquals(5, entry.getFieldMaxX());
            assertEquals(5, entry.getFieldMaxY());

            List<Mow> mows = entry.getMows();


            assertNotNull(mows);
            assertEquals(2, mows.size());

            Mow firstMow = mows.get(0);
            assertEquals(1, firstMow.getInitialPosition().getX());
            assertEquals(2, firstMow.getInitialPosition().getY());
            assertEquals("N", firstMow.getInitialPosition().getOrientation());
            assertEquals("GAGAGAGAA", firstMow.getInstructions());

            Mow secondMow = mows.get(1);
            assertEquals(3, secondMow.getInitialPosition().getX());
            assertEquals(3, secondMow.getInitialPosition().getY());
            assertEquals("E", secondMow.getInitialPosition().getOrientation());
            assertEquals("AADAADADDA", secondMow.getInstructions());
        } catch (URISyntaxException e) {
            fail("Error while retrieving the test file path");
        }
    }

    @Test
    public void testMowPositive() {
        // Get the test file path from the resources
        URL resourceUrl = getClass().getClassLoader().getResource("data.txt");
        assertNotNull(resourceUrl, "The test file was not found");

        try {
            File testFile = new File(resourceUrl.toURI());
            String entry = MowerServiceUtils.mow(testFile.getAbsolutePath());
            assertNotNull(entry, "The entry should not be null");
            assertEquals("1 3 N 5 1 E",entry );
        } catch (URISyntaxException e) {
            fail("Error while retrieving the test file path");
        }
    }
    @Test
    public void testMowOutOfBand() {
        // Get the test file path from the resources
        URL resourceUrl = getClass().getClassLoader().getResource("data-out-of-band.txt");
        assertNotNull(resourceUrl, "The test file was not found");

        try {
            File testFile = new File(resourceUrl.toURI());
            String entry = MowerServiceUtils.mow(testFile.getAbsolutePath());
            assertNotNull(entry, "The entry should not be null");
            assertEquals("1 5 N 5 1 E",entry );
        } catch (URISyntaxException e) {
            fail("Error while retrieving the test file path");
        }
    }

}
