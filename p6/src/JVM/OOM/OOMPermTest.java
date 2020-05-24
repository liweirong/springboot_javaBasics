package JVM.OOM;

/**
 *
 * -Xms50m -Xmx50m  -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./
 *
 * #出现 OOME 时生成堆 dump:
 *
 * -XX:+HeapDumpOnOutOfMemoryError
 *
 * #生成堆文件地址：
 *
 * -XX:HeapDumpPath=./
 * @author iris
 * @date 2020/4/26
 */
public class OOMPermTest {
    public static void main(String[] args){
        oom();
    }

    private static void oom(){
        Object[] array = new Object[10000000];
        for(int i=0; i<10000000; i++){
            String d = String.valueOf(i).intern();
            array[i]=d;
        }
    }
}
