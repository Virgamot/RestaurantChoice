package ru.graduation.model;

import ru.graduation.HasId;

import javax.persistence.*;


@MappedSuperclass
@Access(AccessType.FIELD)
public class BaseEntity implements HasId {

    @Id
    @SequenceGenerator(name="global_seq",sequenceName = "global_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "global_seq")
    private Integer id;

    public BaseEntity() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public boolean isNew() {
        return (this.id == null);
    }

    @Override
    public String toString() {
        return String.format("Entity of type %s with id: %s", getClass().getName(), getId());
    }
}
