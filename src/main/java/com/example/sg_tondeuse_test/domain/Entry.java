package com.example.sg_tondeuse_test.domain;


import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Entry {
    private int fieldMaxX;
    private int fieldMaxY;
    private List<Mow> mows;
}
