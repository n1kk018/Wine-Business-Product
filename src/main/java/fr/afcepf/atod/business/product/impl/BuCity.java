package fr.afcepf.atod.business.product.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afcepf.atod.business.product.api.IBuCity;
import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.data.product.api.IDaoCity;
import fr.afcepf.atod.wine.entity.City;
import java.io.Serializable;

@Service
public class BuCity implements IBuCity {

	
	@Autowired
    private IDaoCity daoCity;
    
	@Override
	public List<City> findAllCities() throws WineException {
		List<City> liste = new ArrayList<City>();
		try {
			liste = daoCity.findAllObj();
		} catch (WineException e) {
			e.printStackTrace();
		}
		return liste;
	}

}
