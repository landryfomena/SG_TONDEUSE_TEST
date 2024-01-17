package com.example.sg_tondeuse_test.domain;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NextPosition {
    private String nextLeft;
    private String nextRight;
}
