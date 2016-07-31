/*
 * Aug 13, 2015
 * File name   : BaseBeanInterface.java
 * Project     : PPM Frontend
 * Description : The purpose of this file is to
 */

package id.sch.elib.util;

/**
 * @author priyono
 */
public interface BaseBeanInterface {

    public Object search(Object completeList, String param, String column);
    
    public Object fetchData(Object target, boolean init);

    /**
     * Action when user save the SAA
     * @return 
     */
    public String save();
}
