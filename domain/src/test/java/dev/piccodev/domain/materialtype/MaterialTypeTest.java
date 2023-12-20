package dev.piccodev.domain.materialtype;

import dev.piccodev.domain.exception.DomainException;
import dev.piccodev.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;

public class MaterialTypeTest {

    @Test
    public void givenAValidParams_whenCallNewMaterialType_thenInstantiateAMaterialType(){

        final var expectedName = "Documentary";
        final var expectedDescription =
        """
        A documentary film is a non-fictional motion-picture intended to document
        reality, primarily for the purposes of instruction, education, or
        maintaining a historical record.
        """;
        final var expectedIsDeleted = false;

        final var createdMaterialType = MaterialType.newMaterialType(expectedName, expectedDescription, expectedIsDeleted);

        Assertions.assertNotNull(createdMaterialType);
        Assertions.assertEquals(createdMaterialType.getName(), expectedName);
        Assertions.assertEquals(createdMaterialType.getDescription(), expectedDescription);
        Assertions.assertFalse(createdMaterialType.isDeleted());

        Assertions.assertNotNull(createdMaterialType.getCreatedAt());
        Assertions.assertNotNull(createdMaterialType.getUpdatedAt());
        Assertions.assertNull(createdMaterialType.getDeletedAt());
    }
}
