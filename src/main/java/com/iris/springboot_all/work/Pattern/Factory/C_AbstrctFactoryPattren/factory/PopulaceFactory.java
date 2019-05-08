package com.iris.springboot_all.work.Pattern.Factory.C_AbstrctFactoryPattren.factory;

import com.iris.springboot_all.work.Pattern.Factory.C_AbstrctFactoryPattren.Populace;
import com.iris.springboot_all.work.Pattern.Factory.C_AbstrctFactoryPattren.IMan;

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
