package org.hello.netty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseData {

    private int intVal;

    @Override
    public String toString() {
        return "intVal :: " + intVal;
    }
}
