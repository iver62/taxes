package org.sid.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TVA") //Dans la colonne type de la table Taxe la valeur TVA est associée  
public class TVA extends Taxe {

	private static final long serialVersionUID = 1L;

	public TVA() {
		super();
	}

	public TVA(String titre, Date dateTaxe, double montant, Entreprise entreprise) {
		super(titre, dateTaxe, montant, entreprise);
	}

}
