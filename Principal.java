import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Principal {
    public static void main(String[] args) throws IOException {
        Arvore genealogica = new Arvore();
        Path path1 = Paths.get("caso3.txt");
        try(BufferedReader reader = Files.newBufferedReader(path1, Charset.forName("utf8"))){
            String line = null;
            int primLinha = 1;
            int terra = 0;
            while ((line = reader.readLine()) != null) {
                if(primLinha == 1){
                    String[] info = line.split(" ");
                    terra = Integer.parseInt(info[0]);
                    primLinha = 2;
                }
                else{
                    String[] dados = line.split(" ");
                    String pai = dados[0];
                    String filho = dados[1];
                    int terras = Integer.parseInt(dados[2]);
                    if (primLinha == 2){
                        genealogica.add(terra, null, pai);
                        primLinha++;
                    }
                    genealogica.add(terras, pai, filho);
                }
            }
        }

        genealogica.maisRico(genealogica.quaseMata());
        //ler arquivo e adicionar nodos com base nas infos do arquivo - FEITO
        //separação de posições pelo espaço - FEITO
        //transformar última info em int - FEITO
    }
}
