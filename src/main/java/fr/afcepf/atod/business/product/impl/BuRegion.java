package fr.afcepf.atod.business.product.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afcepf.atod.business.product.api.IBuRegion;
import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.data.product.api.IDaoRegion;
import fr.afcepf.atod.wine.entity.Region;

@Service
public class BuRegion implements IBuRegion {
	
	@Autowired
	private IDaoRegion daoRegion;

	@Override
	public Region addNewRegion(Region reg) {
	    Region region =null;
		try {
			region = daoRegion.insertObj(reg);
		} catch (WineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return region;
	}

}
