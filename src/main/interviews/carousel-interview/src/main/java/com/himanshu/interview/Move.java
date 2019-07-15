package com.himanshu.interview;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by himanshubhardwaj on 13/06/19.
 */

@Data
@AllArgsConstructor
public class Move {
    int row;
    int column;
    char sign;
}
