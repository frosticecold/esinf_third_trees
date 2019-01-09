/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class Ficheiro {

    /**
     * Método que lê um ficheiro de texto génerico e retorna uma lista com o
     * conteúdo
     *
     * @param nomeFicheiro String com o nome do ficheiro
     * @return List<String> com conteúdo do ficheiro
     */
    public List<String> lerFicheiro(String nomeFicheiro) {
        Scanner scn = null;                                                     //O(1)
        List<String> lista = new ArrayList<>();                                 //O(1)
        try {
            scn = new Scanner(new FileReader(nomeFicheiro));                    //O(1)
            while (scn.hasNext()) {                                             //O(n)
                lista.add(scn.next());                                          //O(1)
            }
        } catch (FileNotFoundException ex) {
            System.out.printf("Foi impossível ler o ficheiro %s\n", nomeFicheiro);//O(1)
        } finally {
            if (scn != null) {                                                  //O(1)
                scn.close();                                                    //O(1)
            }
        }
        return lista;                                                           //O(1)
    }                                                                           //Total O(n)

    /**
     * Método para ler Poligonos
     *
     * @param nomeFicheiro - Nome do Ficheiro
     * @param app Aplicação
     */
    public void lerPoligonos(String nomeFicheiro, App app) {
        List<String> conteudoFich = lerFicheiro(nomeFicheiro);                          //O(n)

        String linhaSplit[];                                                            //O(1)
        for (String linha : conteudoFich) {                                             //O(n) * O(logn)
            linhaSplit = linha.split(";");                                              //O(1)
            Poligono p = new Poligono(Integer.parseInt(linhaSplit[0]), linhaSplit[1]);  //O(1)
            app.inserirPoligono(p);                                                     //O(logn)
        }
    }                                                                                   //Total:O(nlogn)
}
