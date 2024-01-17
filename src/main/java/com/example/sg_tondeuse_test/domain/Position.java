package com.example.sg_tondeuse_test.domain;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Position {
    private int x;
    private int y;
    private String Orientation;
    public String getStringValue(){
        return x+" "+y+" "+this.Orientation;
    }
}
