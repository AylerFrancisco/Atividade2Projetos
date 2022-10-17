/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package collection;

import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author Ayler
 */
public class ElementoCollection {

    private ArrayList<Double> elementos;

    public ElementoCollection(ArrayList<Double> elementos) {
        this.elementos = elementos;
    }

    public ArrayList<Double> getElementos() {
        return elementos;
    }

    public void setElementos(ArrayList<Double> elementos) {
        this.elementos = elementos;
    }

}
