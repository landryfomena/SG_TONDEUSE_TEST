package com.example.sg_tondeuse_test.service;

import com.example.sg_tondeuse_test.domain.NextPositionsMap;
import com.example.sg_tondeuse_test.domain.Position;
import com.example.sg_tondeuse_test.repository.DataRepositoryUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicReference;
/**
 * Utility class for performing mower navigation based on instructions.
 */
public class MowerServiceUtils {
    private static final Logger logger = LoggerFactory.getLogger(DataRepositoryUtils.class);
    /**
     * Perform mower navigation based on instructions from the specified file path.
     *
     * @param filePath The path to the file containing mower navigation instructions.
     * @return A string representing the final positions of all mowers after navigation.
     */
    public static String mow(String filePath) {
        var entry = DataRepositoryUtils.getData(filePath);
        assert entry != null;
        final String[] result = {""};
        logger.debug("navigation started started ");
        entry.getMows().forEach(aMow->{

            AtomicReference<Position> currentPosition = new AtomicReference<>(new Position(
                    aMow.getInitialPosition().getX(),
                    aMow.getInitialPosition().getY(),
                    aMow.getInitialPosition().getOrientation()
            ));
            logger.debug("current position : {}", currentPosition);
            aMow.getInstructions().chars().forEach(direction -> {
                switch (direction) {
                    case 'D':
                        currentPosition.set(turnRight(currentPosition.get()));
                        logger.debug("moving right new position : {}", currentPosition);
                        break;
                    case 'G':
                        currentPosition.set(turnLeft(currentPosition.get()));
                        logger.debug("moving left new position : {}", currentPosition);
                        break;
                    case 'A':
                        currentPosition.set(moveForward(entry.getFieldMaxX(), entry.getFieldMaxY(), currentPosition.get()));
                        logger.debug("moving forward new position : {}", currentPosition);
                        break;
                }
            });
            if(result[0].isEmpty()){
                result[0] = result[0].concat(currentPosition.get().getStringValue());
            }else {
                result[0] = result[0].concat(" "+currentPosition.get().getStringValue());
            }

        });
        logger.debug("navigation over final position: {}", result[0]);

return result[0];

    }
    /**
     * Turn the mower left from its current orientation.
     *
     * @param position The current position of the mower.
     * @return The new position after turning left.
     */
    public static Position turnLeft(Position position) {
        position.setOrientation(NextPositionsMap
                .nextPositionsMap
                .get(position.getOrientation())
                .getNextLeft());
        return position;
    }


    /**
     * Turn the mower right from its current orientation.
     *
     * @param position The current position of the mower.
     * @return The new position after turning right.
     */
    public static Position turnRight(Position position) {
        position.setOrientation(NextPositionsMap
                .nextPositionsMap
                .get(position.getOrientation())
                .getNextRight());
        return position;
    }

    /**
     * Move the mower forward based on its current orientation.
     *
     * @param maxX     The maximum x-coordinate of the lawn.
     * @param maxY     The maximum y-coordinate of the lawn.
     * @param position The current position of the mower.
     * @return The new position after moving forward.
     */
    public static Position moveForward(int maxX, int maxY, Position position) {
        // Save current position in case the move is invalid
        var previousPosition = new Position(position.getX(), position.getY(), position.getOrientation());

        switch (position.getOrientation()) {
            case "N":
                position.setY(position.getY() + 1);
                break;
            case "E":
                position.setX(position.getX() + 1);

                break;
            case "S":
                position.setY(position.getY() - 1);

                break;
            case "W":
                position.setX(position.getX() - 1);

                break;


        }

        // Check if the new position is within the bounds of the lawn
        if (position.getX() < 0 || position.getX() > maxX || position.getY() < 0 || position.getY() > maxY) {
            // If the move is invalid, revert to the previous position
            logger.warn("You are trying to move out of the field position remains : {}",previousPosition);
            return previousPosition;
        }
        return position;
    }
}
