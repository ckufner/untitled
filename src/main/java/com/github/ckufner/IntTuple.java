package com.github.ckufner;

import lombok.Value;

@Value(staticConstructor = "of")
public class IntTuple {
    int item1;
    int item2;
}
