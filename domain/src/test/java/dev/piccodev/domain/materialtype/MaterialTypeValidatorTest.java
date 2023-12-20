package dev.piccodev.domain.materialtype;

import dev.piccodev.domain.exception.DomainException;
import dev.piccodev.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MaterialTypeValidatorTest {

    @Test
    public void givenAnInvalidNullName_whenCallNewMaterialType_thenReceiveAnError(){

        final String expectedName = null;
        final var expectedDescription =
                """
                A documentary film is a non-fictional motion-picture intended to document
                reality, primarily for the purposes of instruction, education, or
                maintaining a historical record.
                """;
        final var expectedIsDeleted = false;

        final var createdMaterialType = MaterialType.newMaterialType(expectedName, expectedDescription, expectedIsDeleted);

        final var thrownException = Assertions.assertThrows(DomainException.class, () -> createdMaterialType.validate(new ThrowsValidationHandler()));
        final var expectedExceptionMessage = "'name' should not be null";
        Assertions.assertEquals(thrownException.getErrors().get(0).message(), expectedExceptionMessage);

        final var expectedErrorCount = 1;
        Assertions.assertEquals(thrownException.getErrors().size(), expectedErrorCount);

        Assertions.assertNotNull(createdMaterialType);
        Assertions.assertEquals(createdMaterialType.getName(), expectedName);
        Assertions.assertEquals(createdMaterialType.getDescription(), expectedDescription);
        Assertions.assertFalse(createdMaterialType.isDeleted());

        Assertions.assertNotNull(createdMaterialType.getCreatedAt());
        Assertions.assertNotNull(createdMaterialType.getUpdatedAt());
        Assertions.assertNull(createdMaterialType.getDeletedAt());
    }

    @Test
    public void givenAnInvalidEmptyName_whenCallNewMaterialType_thenReceiveAnError(){

        final String expectedName = "";
        final var expectedDescription =
                """
                A documentary film is a non-fictional motion-picture intended to document
                reality, primarily for the purposes of instruction, education, or
                maintaining a historical record.
                """;
        final var expectedIsDeleted = false;

        final var createdMaterialType = MaterialType.newMaterialType(expectedName, expectedDescription, expectedIsDeleted);

        final var thrownException = Assertions.assertThrows(DomainException.class, () -> createdMaterialType.validate(new ThrowsValidationHandler()));
        final var expectedExceptionMessage = "'name' should not be empty";
        Assertions.assertEquals(thrownException.getErrors().get(0).message(), expectedExceptionMessage);

        final var expectedErrorCount = 1;
        Assertions.assertEquals(thrownException.getErrors().size(), expectedErrorCount);

        Assertions.assertNotNull(createdMaterialType);
        Assertions.assertEquals(createdMaterialType.getName(), expectedName);
        Assertions.assertEquals(createdMaterialType.getDescription(), expectedDescription);
        Assertions.assertFalse(createdMaterialType.isDeleted());

        Assertions.assertNotNull(createdMaterialType.getCreatedAt());
        Assertions.assertNotNull(createdMaterialType.getUpdatedAt());
        Assertions.assertNull(createdMaterialType.getDeletedAt());
    }
}
