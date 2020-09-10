package com.quickstart.springboot.netty.action.common.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * Function:
 *
 * @author crossoverJie
 *         Date: 17/05/2018 17:50
 * @since JDK 1.8
 */
@Data
public class CustomProtocol implements Serializable{

    private static final long serialVersionUID = 4671171056588401542L;
    private long id ;
    private String content ;

    public CustomProtocol() {
    }

    public CustomProtocol(long id, String content) {
        this.id = id;
        this.content = content;
    }

}
