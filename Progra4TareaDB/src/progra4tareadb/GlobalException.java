/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progra4tareadb;

/**
 *
 * @author fbriceno
 */
public class GlobalException extends Exception {

    /**
     * Creates a new instance of <code>GlobalException</code> without detail
     * message.
     */
    public GlobalException() {
    }

    /**
     * Constructs an instance of <code>GlobalException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public GlobalException(String msg) {
        super(msg);
    }
}
