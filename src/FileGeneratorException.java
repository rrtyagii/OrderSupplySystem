import java.io.FileNotFoundException;

public class FileGeneratorException extends  Exception {

    public FileGeneratorException(){
        super();
    }

    public FileGeneratorException(String statement){
        super(statement);
    }


}
