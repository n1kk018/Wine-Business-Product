package fr.afcepf.atod.business.product.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afcepf.atod.business.product.api.IBuCountry;
import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.data.product.api.IDaoCountry;
import fr.afcepf.atod.wine.entity.Country;

@Service
public class BuCountry implements IBuCountry {
	
	@Autowired
	private IDaoCountry daoCountry;

	@Override
	public Country addNewCountry(Country count) {
		Country country =null;
		try {
			country = daoCountry.insertObj(count);
		} catch (WineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return country;
	}

    @Override
    public List<Country> findAllCountries() throws WineException {
        List<Country> liste = new ArrayList<Country>();
        try {
            liste = daoCountry.findAllObj();
        } catch (WineException e) {
            e.printStackTrace();
        }
        return liste;
    }

}
