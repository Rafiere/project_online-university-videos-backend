package dev.piccodev.domain.materialtype;

import dev.piccodev.domain.validation.Error;
import dev.piccodev.domain.validation.ValidationHandler;
import dev.piccodev.domain.validation.Validator;

public class MaterialTypeValidator extends Validator {

    private final MaterialType materialType;

    public MaterialTypeValidator(final MaterialType materialType,
                                 final ValidationHandler validationHandler) {
        super(validationHandler);
        this.materialType = materialType;
    }

    @Override
    public void validate() {

        checkMaterialTypeNameConstraints();
    }

    private void checkMaterialTypeNameConstraints() {

        final var name = this.materialType.getName();

        if(name == null){
            this.validationHandler().append(new Error("'name' should not be null"));
            return;
        }

        if(name.isBlank()){
            this.validationHandler().append(new Error("'name' should not be empty"));
            return;
        }

        final int NAME_MIN_LENGTH = 3;
        final int NAME_MAX_LENGTH = 255;

        final int length = name.trim().length();

        if(length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH){
            this.validationHandler().append(new Error("'name' must be between 3 and 255 characters"));
        }
    }
}
