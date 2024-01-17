package com.example.sg_tondeuse_test.repository;

import com.example.sg_tondeuse_test.domain.Entry;
import com.example.sg_tondeuse_test.domain.Mow;
import com.example.sg_tondeuse_test.domain.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for extracting data from a file and creating corresponding data objects.
 */
public class DataRepositoryUtils {
    private static final Logger logger = LoggerFactory.getLogger(DataRepositoryUtils.class);

    /**
     * Retrieves data from the specified file and constructs an Entry object.
     *
     * @param filePath The path to the file containing data.
     * @return An Entry object representing the data extracted from the file.
     */
    public static Entry getData(String filePath) {
        logger.debug("File found at the location : {}", filePath);
        Entry entry = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Read the coordinates of the top-right corner of the lawn
            String[] lawnCoordinates = reader.readLine().split(" ");
            int fieldMaxX = Integer.parseInt(lawnCoordinates[0]);
            int fieldMaxY = Integer.parseInt(lawnCoordinates[1]);

            // Initialize the list of mows for the current Entry
            List<Mow> mows = new ArrayList<>();

            // Read the data for each mower
            String line;
            while ((line = reader.readLine()) != null) {
                String[] initialPositionData = line.split(" ");
                int initialX = Integer.parseInt(initialPositionData[0]);
                int initialY = Integer.parseInt(initialPositionData[1]);
                String orientation = initialPositionData[2];

                String instructions = reader.readLine();

                // Create a new Mow object and add it to the list of mows
                Mow mow = new Mow(new Position(initialX, initialY, orientation), instructions);
                mows.add(mow);
            }

            // Create the final Entry object

            entry = new Entry(fieldMaxX, fieldMaxY, mows);
            logger.debug("Entry extracted successfully : {} ", entry);
        } catch (IOException e) {
            logger.error("Erreur lors de la lecture du fichier : {}", e.getMessage());
        }

        return entry;
    }
}
