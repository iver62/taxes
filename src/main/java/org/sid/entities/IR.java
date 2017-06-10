package org.sid.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("IR") //Dans la colonne type de la table Taxe la valeur IR est associée  
public class IR extends Taxe {

	private static final long serialVersionUID = 1L;

	public IR() {
		super();
	}

	public IR(String titre, Date dateTaxe, double montant, Entreprise entreprise) {
		super(titre, dateTaxe, montant, entreprise);
	}
	
}
