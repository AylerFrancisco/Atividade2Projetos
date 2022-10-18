/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import collection.ElementosCollection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ListModel;
import model.Ordena;
import service.LerSalarioArqService;
import view.OrdenacaoView;

/**
 *
 * @author Ayler
 */
public class OrdenacaoPresenter {

    private OrdenacaoView view;
    private ArrayList<Ordena> ordenacaoCollection;
    private LerSalarioArqService lerElementosService;

    public OrdenacaoPresenter(LerSalarioArqService LeOsElementosService, ArrayList<Ordena> ordenacaoCollection) {
        this.lerElementosService = LeOsElementosService;
        this.ordenacaoCollection = ordenacaoCollection;

        this.view = new OrdenacaoView();
        initListeners();
        view.setVisible(true);

    }

    private void initListeners() {
        view.getBtnCarregarArquivo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarArquivo();

            }
        });
        JComboBox<String> cbmMetodo = view.getCmbMetodo();

        for (Ordena metodoOrdenacao : ordenacaoCollection) {
            cbmMetodo.addItem(metodoOrdenacao.getNomeMetodo());
        }
        view.getBtnOrdenar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordenar();

            }
        });
        view.getRbtnCrescente().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //mudaRadioBtn();

            }
        });
        view.getBtnLimpar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Limpa();
            }
        });

    }

    private void carregarArquivo() {
        ElementosCollection elementoColeCollection;
        try {
            elementoColeCollection = lerElementosService.realizarLeituraArq();

            JList<Double> listaSemOrdenados = view.getLstSemOrdenados();
            listaSemOrdenados.setListData(convertArrayListEmVector(elementoColeCollection.getElementos()));

        } catch (FileNotFoundException ex) {
            JOptionPane.showInternalInputDialog(view, "Erro, Por favor tente novamente!");
        }

    }

    private ElementosCollection getElementosNaoOrdenados() {
        JList<Double> ordenacaoView = view.getLstSemOrdenados();
        int sizeList = ordenacaoView.getModel().getSize();

        ArrayList<Double> elementosNaoOrdenados = new ArrayList<>();

        for (int i = 0; i < sizeList; i++) {
            Double item = ordenacaoView.getModel().getElementAt(i);
            elementosNaoOrdenados.add(item);
        }

        return new ElementosCollection(elementosNaoOrdenados);
    }

    private void setElementosOrdenados(ElementosCollection elementoCollection) {
        view.getLstOrdenados().setListData(convertArrayListEmVector(elementoCollection.getElementos()));
    }

    private Vector<Double> convertArrayListEmVector(ArrayList<Double> arrayElementos) {
        return new Vector<Double>(arrayElementos);
    }

//    private void mudaRadioBtn() {
//        JRadioButton rbtnDecrescente;
//        rbtnDecrescente = view.getRbtnDecrescente();
//        JRadioButton rbtnCrescente;
//        rbtnCrescente = view.getRbtnCrescente();
//        boolean result = true;
//        if (rbtnDecrescente.isSelected()) {
//            result = false;
//            rbtnCrescente.setEnabled(false);
//        } else if (rbtnCrescente.isSelected()) {
//            result = true;
//            rbtnDecrescente.setEnabled(false);
//        }
//    }
    private void ordenar() {
        JRadioButton rbtnDecrescente;
        rbtnDecrescente = view.getRbtnDecrescente();
        JRadioButton rbtnCrescente;
        rbtnCrescente = view.getRbtnCrescente();
        boolean result = true;
        if (rbtnDecrescente.isSelected()) {
            result = false;
            rbtnCrescente.setEnabled(false);
        } else if (rbtnCrescente.isSelected()) {
            result = true;
            rbtnDecrescente.setEnabled(false);
        }
        JComboBox<String> cbmMetodo = view.getCmbMetodo();
        String itemSelecionado = cbmMetodo.getSelectedItem().toString();
        ElementosCollection elementoCollectionOrdenacao = getElementosNaoOrdenados();

        for (Ordena metodoOrdenacao : ordenacaoCollection) {
            if (itemSelecionado.equals(metodoOrdenacao.getNomeMetodo())) {
                Instant antes = Instant.now();

                metodoOrdenacao.realizarOrdenarcao(elementoCollectionOrdenacao, result);
                Instant depois = Instant.now();
                Duration duracao = Duration.between(antes, depois);
                setLabelTempo(duracao.getSeconds());

                setElementosOrdenados(elementoCollectionOrdenacao);
                break;
            }
        }

    }

    private void Limpa() {
        JButton btnLimpar;
        btnLimpar = view.getBtnLimpar();
        JList<Double> uen = new JList();
        ElementosCollection elementoCollectionOrdenacao = getElementosNaoOrdenados();
        setElementosOrdenados(elementoCollectionOrdenacao);

    }

    private void setLabelTempo(long tempo) {
        var tempoMilisegundos = tempo * 1000;
        view.getLblTempo().setText(String.valueOf(tempoMilisegundos).concat("ms"));
    }
}
