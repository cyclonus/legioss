package com.faraya.legioss.core.entity.ns;

import com.faraya.legioss.core.IIdentifiable;

import javax.persistence.*;

/**
 *
 * Created by fabrizzio on 4/5/15.
 */

/*
@Entity
@Table(name = "ns_tree",
        indexes =  {
                @Index(name = "name", unique = true, columnList = "name")
        }
)*/

@MappedSuperclass
public abstract class NestedSetTree<T extends NestedSetNode> implements IIdentifiable<Long>{

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    //Must be set manually
    @Transient
    private T root;

    public NestedSetTree() {
    }

    public NestedSetTree(String name) {
        this.name = name;
    }

    /*
     * I'm Commenting this out, cuz it might slow down things considerably
     @OneToMany(mappedBy = "tree", fetch = FetchType.LAZY)
     private Set<T> nodes;
    */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getRoot() {
        return root;
    }

    public void setRoot(T root) {
        this.root = root;
    }

    /*
    public Set<T> getNodes() {
        return nodes;
    }

    public void setNodes(Set<T> nodes) {
        this.nodes = nodes;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NestedSetTree)) return false;

        NestedSetTree tree = (NestedSetTree) o;

        if (!id.equals(tree.id)) return false;
        return name.equals(tree.name);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
