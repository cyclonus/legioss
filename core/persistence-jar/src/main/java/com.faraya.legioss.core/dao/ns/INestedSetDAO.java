package com.faraya.legioss.core.dao.ns;

import com.faraya.legioss.core.dao.IGenericDAO;
import com.faraya.legioss.core.entity.ns.NestedSetNode;
import com.faraya.legioss.core.entity.ns.NestedSetTree;

import java.util.List;

/**
 * User: fabrizzio
 * Date: 6/9/13
 * Time: 10:10 PM
 */
public interface INestedSetDAO <N extends NestedSetNode, T extends NestedSetTree> extends IGenericDAO<N,Long> {

     N findById(Long id);

     N findByName(String name, T tree);

     N findRoot(T tree);

     N add(N node, T tree);

     N add(N newNode, N parent, T tree);

     boolean delete(N node, T tree);

     List<N> getTree(Long rootId);

     N getRightMostNodeFor(N parent);

     N getLeftMostNodeFor(N parent);

}
