package dev.piccodev.domain.materialtype;

import dev.piccodev.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class MaterialTypeID extends Identifier {

    private final String value;


    public MaterialTypeID(final String value) {
        Objects.requireNonNull(value, "'value' cannot be null");
        this.value = value;
    }

    public static MaterialTypeID unique(){

        return MaterialTypeID.from(UUID.randomUUID());
    }

    public static MaterialTypeID from(final UUID id){

        return new MaterialTypeID(id.toString().toLowerCase());
    }

    public static MaterialTypeID from(final String id){

        return new MaterialTypeID(id);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterialTypeID that = (MaterialTypeID) o;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}