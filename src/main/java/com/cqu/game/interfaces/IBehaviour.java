package com.cqu.game.interfaces;

import com.cqu.game.datastructure.Node;
/**
 * @author Madahapola Kankanamalage Kasun Eranda
 */
public interface IBehaviour {
   
    public Node emptyTree(); 
    
    public boolean processNonLeafNode(Node n);
    
    public boolean processLeafNode(Node n);
}
