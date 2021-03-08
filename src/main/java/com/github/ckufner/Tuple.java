package com.github.ckufner;

import lombok.Value;

@Value(staticConstructor = "of")
public class Tuple<A, B> {
    A item1;
    B item2;
}
