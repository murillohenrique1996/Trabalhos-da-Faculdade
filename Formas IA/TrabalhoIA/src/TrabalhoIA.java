import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/*
 * 	Trabalho I de Inteligência Artificial.
 * 
 * 	Murillo Henrique da Silva e Silva
 * 	Vitor Honorato da Silva
 * 
 */
public class TrabalhoIA {

    public static void main(String[] args) {

        //Armazena o nome das formas
        List<String> nomeFormas = new ArrayList<String>();
        int i, j, k;

        //Exibe a janela para selecionar a pasta com as formas
        File pasta = new File("");
        
        JOptionPane.showMessageDialog(null, "Selecione a pasta com os arquivos referentes as figuras em .txt.");
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fileChooser.showOpenDialog(fileChooser);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            pasta = fileChooser.getSelectedFile();
            System.out.println(pasta.getAbsolutePath());
            System.out.println(pasta.getName());
        }

        //Seleciona os arquivos da pasta e armazena os nomes e e cria a matriz
        File[] arquivos = pasta.listFiles();
        Scanner scan = new Scanner("");
        try {
            scan = new Scanner(arquivos[0]);
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        };
        String conteudo = new String();
        j = 0;
        double[][] formas = new double[arquivos.length][scan.next().length()+1];
        for (File f : arquivos) {
            if (f.isFile()) {
                nomeFormas.add(removeExt(f.getName()));
            }
            try {
                scan = new Scanner(f);
                conteudo = scan.next();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            formas[j][0] = 1;
            for (i = 1; i <= conteudo.length(); i++) {
                formas[j][i] = charToInt(conteudo.charAt(i-1));
            }
            j++;
        }

        //Matriz resultado com o tamanho do numero de figuras existentes
        double[][] resultado = new double[nomeFormas.size()][nomeFormas.size()];
        for (i = 0; i < nomeFormas.size(); i++) {
            resultado[i][i] = 1;
        }
        
        //declarando matriz de peso e preechendo com 0
        double[][] pesos = matrizZeros(formas[0].length,formas.length);
        
        //declarando matriz mult e degrau que iram auxiliar no aprendizado
        double[][] mult = matrizZeros(formas.length,formas.length);
        double[][] degrau = matrizZeros(formas.length,formas.length);
           
        //realizando o aprendizado e definição dos pesos
        boolean hasFailed = true;
        
        while(hasFailed){
            hasFailed = false;
            
            for(i = 0; i < formas.length; i++){
                for(j = 0; j < formas.length; j++){
                    for(k = 0; k < formas[i].length; k++){
                        mult[i][j] += formas[i][k] * pesos[k][j]; 
                    }
                    degrau[i][j] = degrau(mult[i][j]);
                    
                    if(degrau[i][j] != resultado[i][j]){
                        hasFailed = true;
                        
                        for(k = 0; k < formas[i].length; k++){
                            pesos[k][j] = pesos[k][j] + (0.3 * (resultado[i][j] - degrau[i][j]) * formas[i][k]);
                        }
                    }
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Treinamento Concluído");
        
        String[] options = { "Sim", "Não" };
        int option = 0;
        
        while(option == 0){
            
            option = JOptionPane.showOptionDialog(null, "Deseja Inserir uma imagem de teste?", "Teste dos Dados",
				JOptionPane.WARNING_MESSAGE, 0, null, options, options[0]);
            
            if(option == 1) break;
            
            JOptionPane.showMessageDialog(null, "Selecione o arquivo .txt com a imagem desejada.");
        
            fileChooser = new JFileChooser();
            returnVal = fileChooser.showOpenDialog(fileChooser);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                pasta = fileChooser.getSelectedFile();
                System.out.println(pasta.getAbsolutePath());
                System.out.println(pasta.getName());
            }

            try{
                scan = new Scanner(pasta);
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            conteudo = scan.next();
            double[] decifrar = new double[conteudo.length()+1];

            decifrar[0] = 1;
             for (i = 1; i <= conteudo.length(); i++) {
                decifrar[i] = charToInt(conteudo.charAt(i-1));
            }

            double aux = 0;
            for(i = 0; i < pesos[0].length; i++){
                for(j = 0; j < decifrar.length; j++){
                    aux += decifrar[j] * pesos[j][i];
                }
                if(degrau(aux) == 1){
                    JOptionPane.showMessageDialog(null, "Forma: "+nomeFormas.get(i));
                    break;
                }
                aux = 0;
            }
        }
        
        /*for(i = 0; i < pesos.length; i++){
            for(j = 0; j < pesos[i].length; j++){
                System.out.print(pesos[i][j]+"-");
            }
            System.out.println("");
        }*/
    }

    public static String removeExt(String s) {
        return s.substring(0, s.length() - 4);
    }
    
    public static double charToInt(char c){
        return ((int) c) - 48;
    }
    
    public static double[][] matrizZeros(int l, int c){
        double[][] result = new double[l][c];
        for(int i = 0; i < l; i++){
            for(int j = 0; j < c; j++){
                result[i][j] = 0;
            }
        }
        return result;
    }
    
    public static double degrau(double a){
        return a > 0 ? 1 : 0;
    }
}