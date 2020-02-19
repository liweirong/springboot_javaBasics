package JVM.stack;

/**
 * 参数带 -verbose:gc
 * 局部变量表slot复用对gc的影响
 *
 * @author iris
 * @date 2020/2/19
 */
public class StackSlot {
    public static void main(String[] args) {
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }
        // [Full GC (System.gc())  66320K->66206K(123904K), 0.0088338 secs]
        // int a =0; //加上此行后： [Full GC (System.gc())  66304K->670K(123904K), 0.0098657 secs]
        System.gc();
    }
}
