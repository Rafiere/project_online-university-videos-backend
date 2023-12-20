package dev.piccodev.domain.materialtype;

import dev.piccodev.domain.AgregateRoot;
import dev.piccodev.domain.validation.ValidationHandler;

import java.time.Instant;

public class MaterialType extends AgregateRoot<MaterialTypeID> {

    private String name;
    private String description;
    private boolean isDeleted;

    private final Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private MaterialType(final MaterialTypeID materialTypeID,
                         final String name,
                         final String description,
                         final boolean isDeleted,
                         final Instant createdAt,
                         final Instant updatedAt,
                         final Instant deletedAt){

        super(materialTypeID);
        this.name = name;
        this.description = description;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static MaterialType newMaterialType(final String name,
                                               final String description,
                                               final boolean isDeleted){

        final var id = MaterialTypeID.unique();
        final var now = Instant.now();
        final var deletedAt = isDeleted ? now : null;

        return new MaterialType(id, name, description, isDeleted, now, now, deletedAt);
    }

    @Override
    public void validate(ValidationHandler handler) {

        new MaterialTypeValidator(this, handler).validate();
    }

    public MaterialType activate(){

        this.isDeleted = false;
        this.deletedAt = null;
        this.updatedAt = Instant.now();

        return this;
    }

    public MaterialType deactivate(){

        if(getDeletedAt() == null){
            this.deletedAt = Instant.now();
        }

        this.isDeleted = true;
        this.updatedAt = Instant.now();

        return this;
    }

    public MaterialType update(final String name,
                               final String description,
                               final boolean isDeleted){

        if(isDeleted){
            deactivate();
        } else {
            activate();
        }

        this.name = name;
        this.description = description;
        this.updatedAt = Instant.now();

        return this;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }
}
