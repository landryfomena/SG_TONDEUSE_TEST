package com.example.sg_tondeuse_test.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
@Data
public class NextPositionsMap {
    public static final Map<String,NextPosition> nextPositionsMap= new HashMap<>(Map.of(
            "N",new NextPosition("W","E"),
            "S",new NextPosition("E","W"),
            "E",new NextPosition("N","S"),
            "W",new NextPosition("S","N")

    ));

}
