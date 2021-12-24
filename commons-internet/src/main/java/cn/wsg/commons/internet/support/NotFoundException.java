package cn.wsg.commons.internet.support;

import java.io.IOException;

/**
 * Exceptions thrown when the target is not found.
 *
 * @author Kingen
 */
public class NotFoundException extends IOException {

    private static final long serialVersionUID = 5856760359416898520L;

    public NotFoundException(String message) {
        super(message);
    }
}
