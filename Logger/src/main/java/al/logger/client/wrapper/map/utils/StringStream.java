package al.logger.client.wrapper.map.utils;

import lombok.Getter;

public class StringStream {
    private String string;
    @Getter
    private int index;
    public StringStream(String s){
        this.string = s;
        this.index = 0;
    }
    public boolean available(){
        return index + 1 != string.length();
    }
    public char next(){
        char c = string.charAt(index);
        index++;
        return c;
    }
}
