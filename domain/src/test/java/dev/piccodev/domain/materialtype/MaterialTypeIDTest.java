package dev.piccodev.domain.materialtype;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class MaterialTypeIDTest {

    @Test
    public void givenValidParams_whenCallUnique_shouldInstantiateAValidMaterialTypeId(){

        final var materialTypeId = MaterialTypeID.unique();
        final int EXPECTED_UUID_LENGTH = 36;

        Assertions.assertNotNull(materialTypeId);
        Assertions.assertEquals(materialTypeId.getValue().length(), EXPECTED_UUID_LENGTH);
    }

    @Test
    public void givenValidParams_whenCallFromWithAString_shouldInstantiateAMaterialTypeIdWithTheString(){

        final var expectedMaterialTypeId = "a689f57c-7364-4bce-944d-dab4714f6021";
        final var materialTypeId = MaterialTypeID.from(expectedMaterialTypeId);
        final int EXPECTED_UUID_LENGTH = 36;

        Assertions.assertEquals(materialTypeId.getValue().length(), EXPECTED_UUID_LENGTH);
        Assertions.assertEquals(materialTypeId.getValue(), expectedMaterialTypeId);
    }

    @Test
    public void givenValidParams_whenCallFromWithAnUUID_shouldInstantiateAMaterialTypeIdWithTheUUID(){

        final var expectedMaterialTypeId = UUID.randomUUID();
        final var materialTypeId = MaterialTypeID.from(expectedMaterialTypeId);
        final int EXPECTED_UUID_LENGTH = 36;

        Assertions.assertEquals(materialTypeId.getValue().length(), EXPECTED_UUID_LENGTH);
        Assertions.assertEquals(UUID.fromString(materialTypeId.getValue()), expectedMaterialTypeId);
    }
}
