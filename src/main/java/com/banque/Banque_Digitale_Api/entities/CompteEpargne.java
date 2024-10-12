package com.banque.Banque_Digitale_Api.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@DiscriminatorValue("COMPTE_EPARGNE")
@Entity
public class CompteEpargne extends Compte{
    private   double touxInteret;

    public double getTouxInteret() {
        return touxInteret;
    }

    public void setTouxInteret(double touxInteret) {
        this.touxInteret = touxInteret;
    }
}
