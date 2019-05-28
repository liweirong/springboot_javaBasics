package com.iris.springboot_all.work.Pattern.prototype.deep;

import java.io.Serializable;

/**
 * Created by iris
 */
public class JinGuBang implements Serializable {
    public float h = 100;
    public float d = 10;

    public void big(){
        this.d *= 2;
        this.h *= 2;
    }

    public void small(){
        this.d /= 2;
        this.h /= 2;
    }
}
