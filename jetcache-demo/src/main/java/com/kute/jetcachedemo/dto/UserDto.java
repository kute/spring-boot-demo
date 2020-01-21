package com.kute.jetcachedemo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * created by bailong001 on 2019/09/26 15:45
 */
@Data
@Accessors(chain = true)
public class UserDto implements Serializable {
    private static final long serialVersionUID = 1660095065754487084L;

    private Long id;

    private String name;

    private Double money;
}
