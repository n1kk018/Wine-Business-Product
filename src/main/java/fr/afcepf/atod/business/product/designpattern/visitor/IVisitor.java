/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afcepf.atod.business.product.designpattern.visitor;

import fr.afcepf.atod.vin.data.exception.WineException;

/**
 * Design Pattern Visitor
 * permet d'externaliser et de centraliser
 * des actions à effectuer sur des objets 
 * qui n'ont pas forcément de liens entre eux.
 * @author ronan
 */
public interface IVisitor {
    /**
     * methode pour visiter
     * @param visitable 
     */
    void visit(IVisitable visitable);
    /**
     * visit pour obtenir les produits selon
     * le type et l'argent
     * @param typeAndMoney
     * @throws WineException 
     */
    void visit(ProductTypeMoney typeAndMoney) throws WineException;
    /**
     * visit pour les produits selon le type et le millesime
     * @param typeAndVintage
     * @throws WineException 
     */
    void visit(ProductTypeVintage typeAndVintage) throws WineException;
}
