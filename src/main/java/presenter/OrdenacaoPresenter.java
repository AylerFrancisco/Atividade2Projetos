/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import collection.ElementoCollection;
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
import javax.swing.JRadioButton;
import javax.swing.ListModel;
import model.Ordenacao;
import service.LerSalarioArqService;
import view.OrdenacaoView;

/**
 *
 * @author Ayler
 */
public class OrdenacaoPresenter {

    private OrdenacaoView view;
    private ArrayList<Ordenacao> ordenacaoCollection;
    private LerSalarioArqService lerElementosService;

    public OrdenacaoPresenter(LerSalarioArqService LeOsElementosService, ArrayList<Ordenacao> ordenacaoCollection) {
        this.lerElementosService = LeOsElementosService;
        this.ordenacaoCollection = ordenacaoCollection;

        this.view = new OrdenacaoView();
        initListeners();
        view.setVisible(true);

    }

    private void initListeners() {
        view.getBtnCarregarArquivo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                carregarArquivo();

            }
        });
        JComboBox<String> cbmMetodo = view.getCmbMetodo();

        for (Ordenacao metodoOrdenacao : ordenacaoCollection) {
            cbmMetodo.addItem(metodoOrdenacao.getNomeMetodo());
        }
        view.getBtnOrdenar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ordenar();

            }
        });
        view.getRbtnCrescente().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                //mudaRadioBtn();

            }
        });
        view.getBtnLimpar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Limpa();
            }
        });

    }

    private void carregarArquivo() {
        ElementoCollection elementoColeCollection;
        try {
            elementoColeCollection = lerElementosService.realizarLeitura();

            JList<Double> listaSemOrdenados = view.getLstSemOrdenados();
            listaSemOrdenados.setListData(convertArrayListEmVector(elementoColeCollection.getElementos()));

        } catch (FileNotFoundException ex) {
            Logger.getLogger(OrdenacaoPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private ElementoCollection getElementosNaoOrdenados() {
        JList<Double> ordenacaoView = view.getLstSemOrdenados();
        int sizeList = ordenacaoView.getModel().getSize();

        ArrayList<Double> elementosNaoOrdenados = new ArrayList<>();

        for (int i = 0; i < sizeList; i++) {
            Double item = ordenacaoView.getModel().getElementAt(i);
            elementosNaoOrdenados.add(item);
        }

        return new ElementoCollection(elementosNaoOrdenados);
    }

    private void setElementosOrdenados(ElementoCollection elementoCollection) {
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
        ElementoCollection elementoCollectionOrdenacao = getElementosNaoOrdenados();

        for (Ordenacao metodoOrdenacao : ordenacaoCollection) {
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

    }

    private void setLabelTempo(long tempo) {
        var tempoMilisegundos = tempo * 1000;
        view.getLblTempo().setText(String.valueOf(tempoMilisegundos).concat("ms"));
    }
}
