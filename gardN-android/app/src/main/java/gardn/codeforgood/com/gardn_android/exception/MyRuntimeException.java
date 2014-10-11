package gardn.codeforgood.com.gardn_android.exception;

public class MyRuntimeException extends RuntimeException {


        public MyRuntimeException(Exception e){
            super();
            e.printStackTrace();
        }

}
