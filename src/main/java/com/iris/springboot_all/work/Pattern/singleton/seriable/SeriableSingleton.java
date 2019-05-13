package com.iris.springboot_all.work.Pattern.singleton.seriable;

import java.io.Serializable;

/**
 * Created by iris.
 * JDK考虑了序列化时被破坏，提供了机制
 *
 * 先new对象，后检查readResolve方法（后面加的，所以在后面判断），
 * 有的话返回单例，覆盖了反序列话的对象，发生在jvm层面，比较安全
 */

//反序列化时导致单例破坏
public class SeriableSingleton implements Serializable {

    //序列化就是说把内存中的状态通过转换成字节码的形式
    //从而转换一个IO流，写入到其他地方(可以是磁盘、网络IO)
    //内存中状态给永久保存下来了

    //反序列化
    //讲已经持久化的字节码内容，转换为IO流
    //通过IO流的读取，进而将读取的内容转换为Java对象
    //在转换过程中会重新创建对象new

    private final static SeriableSingleton INSTANCE = new SeriableSingleton();

    private SeriableSingleton() {
    }

    public static SeriableSingleton getInstance() {
        return INSTANCE;
    }

    /**
     * 必须重写readResolve 防止序列化破坏
     * FileInputStream fis = new FileInputStream("SeriableSingleton.obj");
     * ObjectInputStream ois = new ObjectInputStream(fis);
     * s1 = (SeriableSingleton)ois.readObject();
     *
     *    if (obj != null &&
     *             handles.lookupException(passHandle) == null &&
     *             desc.hasReadResolveMethod())
     *         {
     *             Object rep = desc.invokeReadResolve(obj);
     *
     * @return
     */
    private Object readResolve() {
        return INSTANCE;
    }

}
