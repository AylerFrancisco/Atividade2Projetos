/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import collection.ElementoCollection;
import java.util.ArrayList;

/**
 *
 * @author Ayler
 */
public class BubbleSortOrdenacao extends Ordenacao {

    public BubbleSortOrdenacao() {
        super();
        this.nomeMetodo = "BubbleSort";
    }

    @Override
    public void realizarOrdenarcao(ElementoCollection elementoCollection, Boolean direcao) {
        ArrayList<Double> arraySelecao = elementoCollection.getElementos();
        boolean sorted = false;
        double temp;
        if (direcao == true) {
            while (!sorted) {
                sorted = true;
                for (int i = 0; i < arraySelecao.size() - 1; i++) {
                    if (arraySelecao.get(i).compareTo(arraySelecao.get(i + 1)) > 0) {
                        temp = arraySelecao.get(i);
                        arraySelecao.set(i, arraySelecao.get(i + 1));
                        arraySelecao.set(i + 1, temp);
                        sorted = false;
                    }
                }
            }
        } else {
            while (!sorted) {
                sorted = true;
                for (int i = 0; i < arraySelecao.size() - 1; i++) {
                    if (arraySelecao.get(i).compareTo(arraySelecao.get(i + 1)) < 0) {
                        temp = arraySelecao.get(i);
                        arraySelecao.set(i, arraySelecao.get(i + 1));
                        arraySelecao.set(i + 1, temp);
                        sorted = false;
                    }
                }
            }
        }

        elementoCollection.setElementos(arraySelecao);
    }

    @Override
    public String getNomeMetodo() {
        return this.nomeMetodo;
    }

}
