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
public class SelectionSortOrdenacao extends Ordena {

    private String nomeMetodo;

    public SelectionSortOrdenacao() {
        super();
        this.nomeMetodo = "SelectionSort";
    }

    @Override
    public void realizarOrdenarcao(ElementosCollection elementoCollection, Boolean direcao) {
        ArrayList<Double> arrayElements = elementoCollection.getElementos();
        if (direcao == true) {

            for (int posicaoAtual = 0; posicaoAtual < arrayElements.size(); posicaoAtual++) {

                int indexPosicao = posicaoAtual;
                for (int posicaoAuxiliar = posicaoAtual; posicaoAuxiliar < arrayElements.size(); posicaoAuxiliar++) {
                    if (arrayElements.get(posicaoAuxiliar) < arrayElements.get(indexPosicao)) {
                        indexPosicao = posicaoAuxiliar;
                    }
                }

                double min = arrayElements.get(indexPosicao);
                arrayElements.set(indexPosicao, arrayElements.get(posicaoAtual));
                arrayElements.set(posicaoAtual, min);
            }
        } else {
            for (int posicaoAtual = 0; posicaoAtual < arrayElements.size(); posicaoAtual++) {

                int indexPosicao = posicaoAtual;
                for (int posicaoAuxiliar = posicaoAtual; posicaoAuxiliar < arrayElements.size(); posicaoAuxiliar++) {
                    if (arrayElements.get(posicaoAuxiliar) > arrayElements.get(indexPosicao)) {
                        indexPosicao = posicaoAuxiliar;
                    }
                }

                double min = arrayElements.get(indexPosicao);
                arrayElements.set(indexPosicao, arrayElements.get(posicaoAtual));
                arrayElements.set(posicaoAtual, min);
            }

        }

        elementoCollection.setElementos(arrayElements);
    }

    @Override
    public String getNomeMetodo() {
        return this.nomeMetodo;
    }
}
