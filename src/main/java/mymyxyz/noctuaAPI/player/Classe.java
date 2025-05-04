package mymyxyz.noctuaAPI.player;

import mymyxyz.noctuaAPI.player.classe.EnumClasse;
import mymyxyz.noctuaAPI.player.classe.Assassin;
import mymyxyz.noctuaAPI.player.classe.Chevalier;
import mymyxyz.noctuaAPI.player.classe.Druid;

public class Classe {

    private EnumClasse isActive;
    private Assassin assassin;
    private Chevalier chevalier;
    private Druid druid;

    public Classe() {
    }

    public EnumClasse isActive() {
        return isActive;
    }

    public Assassin getAssassin() {
        return assassin;
    }

    public Chevalier getChevalier() {
        return chevalier;
    }

    public Druid getDruid() {
        return druid;
    }

    public void setActive(EnumClasse classeActive) {
        isActive = classeActive;
    }

    public void setAssassin(Assassin assassin) {
        this.assassin = assassin;
    }

    public void setChevalier(Chevalier chevalier) {
        this.chevalier = chevalier;
    }

    public void setDruid(Druid druid) {
        this.druid = druid;
    }
}