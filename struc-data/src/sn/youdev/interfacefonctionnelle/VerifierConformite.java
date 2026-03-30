package sn.youdev.interfacefonctionnelle;

import sn.youdev.model.Chauffeur;
import sn.youdev.model.Vehicule;

@FunctionalInterface
public interface VerifierConformite {
    public boolean verifier(Vehicule vehicule, Chauffeur chauffeur);
}
