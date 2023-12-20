package dev.piccodev.domain.materialtype;

public record MaterialTypeSearchQuery(int page,
                                      int perPage,
                                      String terms,
                                      String sort,
                                      String direction) {
}
