/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import collection.ElementosCollection;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author Ayler
 */
public class LerSalarioArqService {

    public ElementosCollection realizarLeituraArq() throws FileNotFoundException {
        ArrayList<Double> nums = new ArrayList<>();

        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        int returnValue = jfc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            System.out.println(selectedFile.getAbsolutePath());
            // String pathArquivo = "C:\\Users\\Ayler\\Downloads\\asd.txt";
            File arquivo = new File(selectedFile.getAbsolutePath());

            Scanner scan = new Scanner(arquivo);
            nums.clear();
            scan.nextLine();
            while (scan.hasNextLine()) {
                String linha = scan.nextLine();
                Scanner scanline = new Scanner(linha);

                while (scanline.hasNext()) {
                    String salario = scanline.next();

                    nums.add(Double.valueOf(salario));
                }
            }
        }

        return new ElementosCollection(nums);
        
    }

}
