import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;


public class App {
  public static void main(String[] args)throws Exception {
    //fazer uma conexão HTTP com os top movies
    //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
    //ExtratorDeConteudoDoIMDB extrator = new ExtratorDeConteudoDoIMDB();
    
    String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
    ExtratorDeConteudoDaNasa extrator = new ExtratorDeConteudoDaNasa();
    
    var http = new ClienteHttp();
    String json = http.buscaDados(url);
    
    var diretorio = new File("saida/");
    diretorio.mkdir();
    
    //exibir e manipular os dados
    List<Conteudo> conteudos = extrator.extraiConteudos(json);

    var geradora = new GeradoraDeFigurinhas();
    
      for (int i = 0; i < 3; i++) {

        Conteudo conteudo = conteudos.get(i);

        InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
        String nomeArquivo = "saida/" + conteudo.titulo() + ".png";

        geradora.cria(inputStream, nomeArquivo);

        System.out.println(conteudo.titulo());
        System.out.println();
      }
  }
}