package dev.piccodev.domain;

public abstract class AgregateRoot<ID extends Identifier> extends Entity<ID> {

    protected AgregateRoot(ID id) {
        super(id);
    }
}
