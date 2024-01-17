package com.example.sg_tondeuse_test.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Mow {
    private Position initialPosition;
    private String instructions;
}
