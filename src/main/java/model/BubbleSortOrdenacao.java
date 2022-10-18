/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import collection.ElementosCollection;
import java.util.ArrayList;

/**
 *
 * @author Ayler
 */
public class BubbleSortOrdenacao extends Ordena {

    public BubbleSortOrdenacao() {
        super();
        this.nomeMetodo = "BubbleSort";
    }

    @Override
    public void realizarOrdenarcao(ElementosCollection elementoCollection, Boolean direcao) {
        ArrayList<Double> arrayElements = elementoCollection.getElementos();
        boolean sorted = false;
        double temp;
        if (direcao == true) {
            while (!sorted) {
                sorted = true;
                for (int i = 0; i < arrayElements.size() - 1; i++) {
                    if (arrayElements.get(i).compareTo(arrayElements.get(i + 1)) > 0) {
                        temp = arrayElements.get(i);
                        arrayElements.set(i, arrayElements.get(i + 1));
                        arrayElements.set(i + 1, temp);
                        sorted = false;
                    }
                }
            }
        } else {
            while (!sorted) {
                sorted = true;
                for (int i = 0; i < arrayElements.size() - 1; i++) {
                    if (arrayElements.get(i).compareTo(arrayElements.get(i + 1)) < 0) {
                        temp = arrayElements.get(i);
                        arrayElements.set(i, arrayElements.get(i + 1));
                        arrayElements.set(i + 1, temp);
                        sorted = false;
                    }
                }
            }
        }

        elementoCollection.setElementos(arrayElements);
    }

    @Override
    public String getNomeMetodo() {
        return this.nomeMetodo;
    }

}
