package sn.youdev.java.avance.enums;

import java.util.Optional;

public enum Mention {
    AJOURNEE(0f,10f),
    // Les constantes sont, par convention, en majuscules.
    PASSABLE(10f,12f),
    ASSEZ_BIEN(12f,14f),
    BIEN(14f,16f),
    TRES_BIEN(16f,18f),
    EXCELLENT(18f,20f);
    private final float minValue;
    private final float maxValue;
    public static Optional<Mention> fromNote(float note){
        if(note <0 || note >20){
            return Optional.empty();
        } else if(note < 10){
            return Optional.of(Mention.AJOURNEE);
        }else if(note <=12){
            return Optional.of(Mention.PASSABLE);
        }else if(note <=14){
            return Optional.of(Mention.ASSEZ_BIEN);
        }else if(note <=16){
            return Optional.of(Mention.BIEN);
        }else if(note <=18){
            return Optional.of(Mention.TRES_BIEN);
        } else{
            return Optional.of(Mention.EXCELLENT);
        }
    }
    Mention(float minValue, float maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public float getMinValue() {
        return minValue;
    }

    public float getMaxValue() {
        return maxValue;
    }
}
