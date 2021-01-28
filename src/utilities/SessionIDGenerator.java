package utilities;

import java.util.Random;

/**
 * This class will implemet the IdGenerator interface and will generate id.
 * 
 * @author  : Utsav Parajuli
 * @version : 1.0
 * @since   : 12/01/2020
 * 
 */
public class SessionIDGenerator implements IdGenerator {

    /**
     * This method will override the interface method and will generate a random id. The id will start with a letter and have random
     * numbers
     */
    @Override
    public String generateId() {
        
        Random r = new Random();
        char c   = (char)(r.nextInt(26) + 'a');
        
        int n = new Random().nextInt();
        return c + String.valueOf(n);
    }  
}
