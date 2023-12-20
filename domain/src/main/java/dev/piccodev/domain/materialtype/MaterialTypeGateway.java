package dev.piccodev.domain.materialtype;

import dev.piccodev.domain.pagination.Pagination;

import java.util.Optional;

public interface MaterialTypeGateway {

    MaterialType create(MaterialType materialType);
    MaterialType update(MaterialType materialType);
    void deleteById(MaterialTypeID id);
    Optional<MaterialType> findById(MaterialTypeID id);
    Pagination<MaterialType> findAll(MaterialTypeSearchQuery query);
}
