package fr.afcepf.atod.business.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afcepf.atod.business.product.api.IBuCountry;
import fr.afcepf.atod.vin.data.exception.WineErrorCode;
import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.data.product.api.IDaoCountry;
import fr.afcepf.atod.wine.entity.Country;

@Service
public class BuCountry implements IBuCountry {

    @Autowired
    private IDaoCountry daoCountry;
    
    @Override
    public List<Country> listAllCountries() throws WineException{
        List<Country> list = null;
        try {
            list = daoCountry.findAllObj();
        } catch (Exception e) {
            throw new WineException(
                    WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                    "no country in db");
        }
        return list;
    }

}
