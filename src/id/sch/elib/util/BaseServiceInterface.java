/*
 * Aug 17, 2015
 * File name   : BaseServiceInterface.java
 * Project     : PPM Frontend
 * Description : The purpose of this file is to
 */

package id.sch.elib.util;

/**
 * @author priyono
 */
public interface BaseServiceInterface {

    public Object listAll();
    public Object get(long id);
    public Object save(Object obj);
    public Object update(Object obj);
    public Object delete(Object obj);
    public Object search(Object obj);
    
}
