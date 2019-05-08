package com.iris.springboot_all.work.Pattern.Factory.B_FactoryMethodPattern.factory;

import com.iris.springboot_all.work.Pattern.Factory.B_FactoryMethodPattern.IMan;
import com.iris.springboot_all.work.Pattern.Factory.B_FactoryMethodPattern.Populace;

/**
 * @author iris
 * @date 2019/5/5
 */
public class PopulaceFactory implements IManFactory {

    @Override
    public IMan create() {
        return new Populace();
    }
}
