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
public class SelectionSortOrdenacao extends Ordenacao {

    private String nomeMetodo;

    public SelectionSortOrdenacao() {
        super();
        this.nomeMetodo = "SelectionSort";
    }

    @Override
    public void realizarOrdenarcao(ElementoCollection elementoCollection, Boolean direcao) {
        ArrayList<Double> arraySelecao = elementoCollection.getElementos();
        if (direcao == true) {

            for (int posicaoAtual = 0; posicaoAtual < arraySelecao.size(); posicaoAtual++) {

                int indexPosicao = posicaoAtual;
                for (int posicaoAuxiliar = posicaoAtual; posicaoAuxiliar < arraySelecao.size(); posicaoAuxiliar++) {
                    if (arraySelecao.get(posicaoAuxiliar) < arraySelecao.get(indexPosicao)) {
                        indexPosicao = posicaoAuxiliar;
                    }
                }

                double min = arraySelecao.get(indexPosicao);
                arraySelecao.set(indexPosicao, arraySelecao.get(posicaoAtual));
                arraySelecao.set(posicaoAtual, min);
            }
        } else {
            for (int posicaoAtual = 0; posicaoAtual < arraySelecao.size(); posicaoAtual++) {

                int indexPosicao = posicaoAtual;
                for (int posicaoAuxiliar = posicaoAtual; posicaoAuxiliar < arraySelecao.size(); posicaoAuxiliar++) {
                    if (arraySelecao.get(posicaoAuxiliar) > arraySelecao.get(indexPosicao)) {
                        indexPosicao = posicaoAuxiliar;
                    }
                }

                double min = arraySelecao.get(indexPosicao);
                arraySelecao.set(indexPosicao, arraySelecao.get(posicaoAtual));
                arraySelecao.set(posicaoAtual, min);
            }

        }

        elementoCollection.setElementos(arraySelecao);
    }

    @Override
    public String getNomeMetodo() {
        return this.nomeMetodo;
    }
}
