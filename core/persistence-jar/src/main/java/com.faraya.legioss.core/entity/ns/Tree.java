package com.faraya.legioss.core.entity.ns;

import com.faraya.legioss.core.IIdentifiable;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by fabrizzio on 4/5/15.
 */

@Entity
@Table(name = "ns_tree",
        indexes =  {
                @Index(name = "name", unique = true, columnList = "name")
        }
)
public class Tree implements IIdentifiable<Long>{

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public Tree() {
    }

    public Tree(String name) {
        this.name = name;
    }

    /*
    @OneToMany(mappedBy = "tree", fetch = FetchType.LAZY)
    private Set<Tree> nodes;
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

    /*
    public Set<Tree> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Tree> nodes) {
        this.nodes = nodes;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tree)) return false;

        Tree tree = (Tree) o;

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
