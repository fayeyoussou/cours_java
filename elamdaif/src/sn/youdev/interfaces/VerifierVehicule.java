package sn.youdev.interfaces;

import sn.youdev.model.Chauffeur;
import sn.youdev.model.Vehicule;

@FunctionalInterface
public interface VerifierVehicule {
    boolean verifier(Vehicule v);
}
