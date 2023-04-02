import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;


public class App {
  public static void main(String[] args)throws Exception {
    //fazer uma conexão HTTP com os top movies
    String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
    URI endereco = URI.create(url);
    var client = HttpClient.newHttpClient();
    var request = HttpRequest.newBuilder(endereco).GET().build();
    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    String body = response.body();
    System.out.println(body);
    
    //extrair só os dados que interessam (título, poster e classificação)
    var parser = new JsonParser(); //parser = extrair algo assim
    List<Map<String, String>> listaDeFilmes = parser.parse(body);

    var diretorio = new File("saida/");
    diretorio.mkdir();
    
    //exibir e manipular os dados
    for(Map<String, String> filme: listaDeFilmes) {
     
      String titulo = (filme.get("title"));
      String urlImagem = (filme.get("image"));
      
      InputStream inputStream = new URL(urlImagem).openStream();
      String nomeArquivo = "./saida/" + titulo + ".png";
       
      var geradora = new GeradoraDeFigurinhas();
      geradora.cria(inputStream, nomeArquivo);
      
      System.out.println(filme.get("title"));
      System.out.println();
    	
    } 
  }
}