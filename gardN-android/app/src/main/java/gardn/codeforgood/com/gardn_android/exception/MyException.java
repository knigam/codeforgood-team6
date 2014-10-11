package gardn.codeforgood.com.gardn_android.exception;

import android.content.Context;

/**
 * Created by kushal on 4/19/14.
 */
public class MyException extends Exception{
    //TODO
    public MyException(Exception e){
        super();
        e.printStackTrace();
    }
    public MyException(Context context, Exception e){
        super();
        e.printStackTrace();
        //Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
