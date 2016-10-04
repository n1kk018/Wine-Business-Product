/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afcepf.atod.business.product.designpattern.visitor;

import fr.afcepf.atod.vin.data.exception.WineException;

/**
 * Design Pattern Visitor
 * Interface Visitable
 * Implemente par les classes ou des actions comme
 * aller chercher des vins mises.
 * @author ronan
 */
public interface IVisitable {
    /**
     * Accept un visitor
     * @param visitor
     * @throws WineException 
     */
    void accept(IVisitor visitor) throws WineException;
}
