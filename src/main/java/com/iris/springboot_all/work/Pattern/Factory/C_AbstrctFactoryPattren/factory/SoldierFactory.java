package com.iris.springboot_all.work.Pattern.Factory.C_AbstrctFactoryPattren.factory;

import com.iris.springboot_all.work.Pattern.Factory.C_AbstrctFactoryPattren.Soldier;
import com.iris.springboot_all.work.Pattern.Factory.C_AbstrctFactoryPattren.IMan;

/**
 * @author iris
 * @date 2019/5/5
 */
public class SoldierFactory implements IManFactory {

    @Override
    public IMan create() {
        return new Soldier();
    }
}
