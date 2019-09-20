package com.edwardjones.avengers.community.ui.region;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Region {
    String name;
    String personType;

    public String getName() {
        return name;
    }

    public String getPersonType() {
        return personType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }
}
