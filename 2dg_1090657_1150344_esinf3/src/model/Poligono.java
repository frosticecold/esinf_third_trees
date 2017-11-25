/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class Poligono implements Comparable<Poligono> {

    private int num_lados;
    private String prefixo;

    public Poligono(int num_lados, String prefixo) {
        this.num_lados = num_lados;
        this.prefixo = prefixo;
    }

    public int getNumLados() {
        return num_lados;
    }

    public String getPrefixo() {
        return prefixo;
    }

    @Override
    public int compareTo(Poligono o) {
        return Integer.compare(num_lados, o.num_lados);
    }

    public int compareToString(Poligono o) {
        if (o.prefixo.contains(prefixo)) {
            return 0;
        }
        if (this.prefixo.compareTo(o.prefixo) < 0) {
            return -1;
        } else {
            return 1;
        }

    }

    @Override
    public String toString() {
        return "{Lados:" + num_lados + " Prefixo:" + prefixo + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Poligono other = (Poligono) obj;
        if (this.num_lados != other.num_lados) {
            return false;
        }
        return prefixo.equals(other.prefixo);
    }

}
