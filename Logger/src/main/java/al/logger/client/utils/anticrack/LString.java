package al.logger.client.utils.anticrack;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Locale;

public class LString implements Serializable, Comparable<String>, CharSequence {

    private static final long serialVersionUID = -204188373852348874L;

    /**
     * 定义char型数组,核心变量
     */
    private final char[] value;

    /**
     * 空构造函数
     */
    public LString() {
        value = new char[0];
    }

    /**
     * @param str 默认String值的构造方法
     */
    public LString(String str) {
        this.value = str.toCharArray();
    }

    public LString(char[] value) {
        this.value = value;
    }
    public LString(byte[] bytes) {
         this.value = new char[bytes.length];
         for(int i = 0; i < bytes.length; i++) {
             this.value[i] = (char) bytes[i];
         }
    }
    public LString(char value[], int offset, int count) {
        if(offset < 0) {
            throw new StringIndexOutOfBoundsException(offset);
        }
        if(count <= 0) {
            if(count < 0) {
                throw new StringIndexOutOfBoundsException(count);
            }
            if(offset <= value.length) {
                this.value = new char[0];
                return;
            }
        }
        if(offset > value.length-count) {
            throw new StringIndexOutOfBoundsException(offset);
        }
        this.value = Arrays.copyOfRange(value, offset, count);
    }


    @Override
    public int length() {
        return value.length;
    }

    @Override
    public char charAt(int index) {
        if(index > this.value.length || index < 0) {
            throw new StringIndexOutOfBoundsException(index);
        }
        return this.value[index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        if(start < 0) {
            throw new StringIndexOutOfBoundsException(start);
        }
        if(end > value.length) {
            throw new StringIndexOutOfBoundsException(end);
        }
        if(start > end) {
            throw new StringIndexOutOfBoundsException("start index is bigger than end index.");
        }

        LString result = new LString(Arrays.copyOfRange(value, start, end));
        return result;
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }

    public int compareTo(LString anotherString) {
        if(this == anotherString) {
            return 0;
        }

        return 0;
    }

    @Override
    public String toString() {
        return new String(this.value);
    }

    public boolean equals(Object anObject) {
        if(this == anObject) {
            return true;
        }
        if(anObject instanceof LString) {
            LString anString = (LString)anObject;
            if(anString.length() == value.length) {
                char[] v1 = anString.value;
                char[] v2 = value;
                int n = v1.length;
                while(n-- != 0) {
                    if(v1[n] != v2[n]) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    //TODO
    public boolean contains(CharSequence s) {

        return true;
    }

    public int indexOf(int ch) {
        return indexOf(0, ch);
    }

    public int indexOf(int ch, int fromIndex){
        for(int i = 0; i < value.length; i++) {
            if(value[i] == ch) {
                return i;
            }
        }
        return -1;
    }
    public int indexOf(LString str) {
        return indexOf(str, 0);
    }

    public int indexOf(LString str, int fromIndex){
        char[] source = value;
        char[] target = str.value;
        for(int i = fromIndex; i < source.length; i++) {
            if(source[i] == target[0]){
                int index = i;
                int sameLen = 1;
                for(int j = 1; j < target.length && (i+j) < source.length; j++) {
                    if(target[j] != source[i+j]) {
                        break;
                    }
                    sameLen++;
                }
                if(sameLen == target.length) {
                    return index;
                }
            }
        }
        return -1;
    }

    public LString concat(LString str) {
        if(str.length() == 0) {
            return this;
        }
        int len = value.length;
        int otherLen = str.length();
        char[] buf = Arrays.copyOf(value, len + otherLen);  //定义一个新的char[]数组,长度为原数组+要拼接的数组长度，并将原来的数组内容复制到新的数组
        System.arraycopy(str.value, 0, buf, len, otherLen);
        return new LString(buf);
    }

    public boolean startsWith(LString prefix) {
        return this.startsWith(prefix, 0);
    }

    /**
     * 检测字符串是否以某子串开头
     * @param   prefix  子串
     * @param   toffset 字符串的起始位置
     * @return  {@code true} 如果字符串以该子串开头就返回true; {@code false} 否则返回false.
     * @since   1. 0
     * @author SpringChang
     */

    public boolean startsWith(LString prefix, int toffset){
        char[] target = prefix.value;
        char[] source = value;
        int sameLen = 0; //相同字符长度
        for(int i = 0; i < target.length && (toffset+i) < source.length; i++) {
            if(source[toffset+i] != target[i]) {
                break;
            }
            sameLen++;
        }
        if(sameLen == target.length) {
            return true;
        }
        return false;
    }

    public LString toLowerCase(){
        return this.toLowerCase(Locale.getDefault());
    }
    public LString toLowerCase(Locale locale){
        if(locale == null) {
             throw new NullPointerException();
        }
        char[] newString = new char[value.length];
        for(int i = 0; i < value.length; i++) {
            if(value[i] >= 'A' && value[i] <= 'Z') {
                newString[i] = (char) (value[i] + 32);
            } else {
                newString[i] = value[i];
            }
        }

        return new LString(newString);
    }

    public LString toUpperCase(){
        return this.toUpperCase(Locale.getDefault());
    }
    public LString toUpperCase(Locale locale) {
        if(locale == null) {
             throw new NullPointerException();
        }
        char[] newString = new char[value.length];
        for(int i = 0; i < value.length; i++) {
            if(value[i] >= 'a' && value[i] <= 'z') {
                newString[i] = (char) (value[i] - 32);
            } else {
                newString[i] = value[i];
            }
        }

        return new LString(newString);
    }

    public LString substring(int beginIndex){
        if(beginIndex < 0) {
            throw new StringIndexOutOfBoundsException(beginIndex);
        }
        return beginIndex==0?this:new LString(value, beginIndex, value.length-beginIndex);
    }
    public LString substring(int beginIndex, int endIndex){
        if(beginIndex < 0) {
            throw new StringIndexOutOfBoundsException(beginIndex);
        }
        if(endIndex > value.length) {
            throw new StringIndexOutOfBoundsException(endIndex);
        }
        return beginIndex==0?this:new LString(value, beginIndex, endIndex);
    }


    /**
     * 将字符串转换为字符数组
     * @return 新的字符数组，不能是原来的数组，否则用户可以操作和修改String内容,这样违反String final设计原则。
     */
    public char[] toCharArray() {
        char[] charArr = new char[value.length];
        System.arraycopy(value, 0, charArr, 0, value.length);
        return charArr;
    }
}