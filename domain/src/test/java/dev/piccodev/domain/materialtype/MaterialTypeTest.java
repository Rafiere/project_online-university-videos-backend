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

    @Test
    public void givenAValidTrueIsDeleted_whenCallNewMaterialType_thenInstantiateADeletedMaterialType(){

        final var expectedName = "Documentary";
        final var expectedDescription =
                """
                A documentary film is a non-fictional motion-picture intended to document
                reality, primarily for the purposes of instruction, education, or
                maintaining a historical record.
                """;
        final var expectedIsDeleted = true;

        final var createdMaterialType = MaterialType.newMaterialType(expectedName, expectedDescription, expectedIsDeleted);

        Assertions.assertDoesNotThrow(() -> createdMaterialType.validate(new ThrowsValidationHandler()));

        Assertions.assertNotNull(createdMaterialType);
        Assertions.assertEquals(createdMaterialType.getName(), expectedName);
        Assertions.assertEquals(createdMaterialType.getDescription(), expectedDescription);
        Assertions.assertTrue(createdMaterialType.isDeleted());

        Assertions.assertNotNull(createdMaterialType.getCreatedAt());
        Assertions.assertNotNull(createdMaterialType.getUpdatedAt());
        Assertions.assertNotNull(createdMaterialType.getDeletedAt());
    }

    @Test
    public void givenAValidDeactivatedMaterialType_whenCallActivate_thenActivateAMaterialType(){

        final var expectedName = "Documentary";
        final var expectedDescription =
                """
                A documentary film is a non-fictional motion-picture intended to document
                reality, primarily for the purposes of instruction, education, or
                maintaining a historical record.
                """;
        final var expectedIsDeleted = true;

        final var createdMaterialType = MaterialType.newMaterialType(expectedName, expectedDescription, expectedIsDeleted);

        Assertions.assertDoesNotThrow(() -> createdMaterialType.validate(new ThrowsValidationHandler()));

        Assertions.assertNotNull(createdMaterialType);
        Assertions.assertTrue(createdMaterialType.isDeleted());
        Assertions.assertNotNull(createdMaterialType.getDeletedAt());

        final Instant createdMaterialTypeCreatedAt = createdMaterialType.getCreatedAt();
        final Instant createdMaterialTypeUpdatedAt = createdMaterialType.getUpdatedAt();

        final var activatedMaterialType = createdMaterialType.activate();

        Assertions.assertDoesNotThrow(() -> activatedMaterialType.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(createdMaterialType.getName(), expectedName);
        Assertions.assertEquals(createdMaterialType.getDescription(), expectedDescription);
        Assertions.assertFalse(createdMaterialType.isDeleted());

        Assertions.assertEquals(createdMaterialTypeCreatedAt, activatedMaterialType.getCreatedAt());
        Assertions.assertTrue(createdMaterialTypeUpdatedAt.isBefore(activatedMaterialType.getUpdatedAt()));
        Assertions.assertNull(createdMaterialType.getDeletedAt());
    }

    @Test
    public void givenAValidActivatedMaterialType_whenCallDeactivate_thenDeactivateAMaterialType(){

        final var expectedName = "Documentary";
        final var expectedDescription =
                """
                A documentary film is a non-fictional motion-picture intended to document
                reality, primarily for the purposes of instruction, education, or
                maintaining a historical record.
                """;
        final var expectedIsDeleted = false;

        final var createdMaterialType = MaterialType.newMaterialType(expectedName, expectedDescription, expectedIsDeleted);

        Assertions.assertDoesNotThrow(() -> createdMaterialType.validate(new ThrowsValidationHandler()));

        Assertions.assertNotNull(createdMaterialType);
        Assertions.assertFalse(createdMaterialType.isDeleted());
        Assertions.assertNull(createdMaterialType.getDeletedAt());

        final Instant createdMaterialTypeCreatedAt = createdMaterialType.getCreatedAt();
        final Instant createdMaterialTypeUpdatedAt = createdMaterialType.getUpdatedAt();

        final var activatedMaterialType = createdMaterialType.deactivate();

        Assertions.assertDoesNotThrow(() -> activatedMaterialType.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(createdMaterialType.getName(), expectedName);
        Assertions.assertEquals(createdMaterialType.getDescription(), expectedDescription);
        Assertions.assertTrue(createdMaterialType.isDeleted());

        Assertions.assertEquals(createdMaterialTypeCreatedAt, activatedMaterialType.getCreatedAt());
        Assertions.assertTrue(createdMaterialTypeUpdatedAt.isBefore(activatedMaterialType.getUpdatedAt()));
        Assertions.assertNotNull(createdMaterialType.getDeletedAt());
    }

    @Test
    public void givenAValidMaterialType_whenCallUpdate_thenUpdateMaterialType(){

        final var expectedName = "Documentary";
        final var expectedDescription =
                """
                A documentary film is a non-fictional motion-picture intended to document
                reality, primarily for the purposes of instruction, education, or
                maintaining a historical record.
                """;
        final var expectedIsDeleted = false;

        final var createdMaterialType = MaterialType.newMaterialType(expectedName, expectedDescription, expectedIsDeleted);

        Assertions.assertDoesNotThrow(() -> createdMaterialType.validate(new ThrowsValidationHandler()));

        Assertions.assertNotNull(createdMaterialType);
        Assertions.assertFalse(createdMaterialType.isDeleted());
        Assertions.assertNull(createdMaterialType.getDeletedAt());

        final Instant createdMaterialTypeCreatedAt = createdMaterialType.getCreatedAt();
        final Instant createdMaterialTypeUpdatedAt = createdMaterialType.getUpdatedAt();

        final var updatedExpectedName = "Lesson";
        final var updatedExpectedDescription = "A new description";
        final var updatedExpectedIsDeleted = true;

        createdMaterialType.update(updatedExpectedName, updatedExpectedDescription, updatedExpectedIsDeleted);

        Assertions.assertDoesNotThrow(() -> createdMaterialType.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(createdMaterialType.getName(), updatedExpectedName);
        Assertions.assertEquals(createdMaterialType.getDescription(), updatedExpectedDescription);
        Assertions.assertTrue(createdMaterialType.isDeleted());

        Assertions.assertEquals(createdMaterialTypeCreatedAt, createdMaterialType.getCreatedAt());
        Assertions.assertTrue(createdMaterialTypeUpdatedAt.isBefore(createdMaterialType.getUpdatedAt()));
        Assertions.assertNotNull(createdMaterialType.getDeletedAt());
    }
}
