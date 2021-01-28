package utilities;

import java.util.UUID;

/**
 * This class will implemet the IdGenerator interface and will generate id.
 * 
 * @author  : Utsav Parajuli
 * @version : 1.0
 * @since   : 12/01/2020
 * 
 */ 
public class UUIDGenerator implements IdGenerator{

    /**
     * This method will override the interface method and will generate a random uuid.
     */
    @Override
    public String generateId() {
        return UUID.randomUUID().toString();
    }
    
}
