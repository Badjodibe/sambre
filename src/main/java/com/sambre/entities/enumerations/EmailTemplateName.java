package com.sambre.entities.enumerations;


import lombok.Getter;

@Getter
public enum EmailTemplateName {
    ACTIVATE_ACCOUNT("activate_account"),
    CONFIRM_EMAIL("confirm-email"),
    VALIDATION_ACCOUNT_EMAIL("validation-account")
    ;

    private final String name;

    EmailTemplateName(String name){
        this.name = name;
    }

}