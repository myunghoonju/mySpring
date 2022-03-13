package org.hello.netty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestData {

    private int intVal;
    private String stringVal;

    @Override
    public String toString() {
        return "initVal :: " + intVal + "   " + "stringVal :: " + stringVal;
    }
}
