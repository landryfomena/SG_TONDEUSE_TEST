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
    private String orientation;
    public String getStringValue(){
        return this.x+" "+this.y+" "+this.orientation;
    }
}
