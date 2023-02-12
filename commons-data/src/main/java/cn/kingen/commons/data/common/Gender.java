package cn.kingen.commons.data.common;

import lombok.Getter;

/**
 * @author Kingen
 */
@Getter
public enum Gender {

    FEMALE("女"),
    MALE("男");

    private final String displayName;

    Gender(String displayName) {
        this.displayName = displayName;
    }
}
