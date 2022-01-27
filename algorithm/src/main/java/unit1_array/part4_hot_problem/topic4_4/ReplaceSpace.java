package unit1_array.part4_hot_problem.topic4_4;

/**
 * 替换空格
 */
public class ReplaceSpace {
    public static void main(String[] args) {
        StringBuffer  sb =new StringBuffer("We are happy.") ;
        System.out.println(replaceSpace(sb));
    }
    public static String replaceSpace(StringBuffer str) {
        if(str==null)
            return null;
        int numOfblank = 0;//空格数量
        int len=str.length();
        for(int i=0;i<len;i++){  //计算空格数量
            if(str.charAt(i)==' ')
                numOfblank++;
        }
        str.setLength(len+2*numOfblank); //设置长度
        int oldIndex=len-1;  //两个指针
        int newIndex=(len+2*numOfblank)-1;

        while(oldIndex>=0 && newIndex>oldIndex){
            char c=str.charAt(oldIndex);
            if(c==' '){
                oldIndex--;
                str.setCharAt(newIndex--,'0');
                str.setCharAt(newIndex--,'2');
                str.setCharAt(newIndex--,'%');
            }else{
                str.setCharAt(newIndex,c);
                oldIndex--;
                newIndex--;
            }
        }
        return str.toString();
    }
}
