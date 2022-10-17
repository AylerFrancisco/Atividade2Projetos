/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package principal;

import java.util.ArrayList;
import java.util.Arrays;
import model.BubbleSortOrdenacao;
import model.Ordenacao;
import model.SelectionSortOrdenacao;
import service.LerSalarioArqService;
import presenter.OrdenacaoPresenter;

/**
 *
 * @author Ayler
 */
public class Main {

    public static void main(String[] args) {
        ArrayList<Ordenacao> ordenacaoCollection = new ArrayList<Ordenacao>(Arrays.asList(
                new BubbleSortOrdenacao(),
                new SelectionSortOrdenacao()
        ));

        new OrdenacaoPresenter(new LerSalarioArqService(), ordenacaoCollection);
    }
}
