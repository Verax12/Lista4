import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Lista4 {

	private static int comparacoes = 0;
	private static int movimentacoes = 0;

	public static void main(String[] args) throws IOException {

		MyIO.setCharset("UTF-8");
		String arquivo = ReadFile();
		int tamanhoVetor = 1429;
		Livro[] livros = new Livro[tamanhoVetor];
		ProcessarLivros(tamanhoVetor, livros, arquivo);
		ArrayList<Livro> ListaPesquisa = new ArrayList<Livro>();
		PesquisaLivros(livros, ListaPesquisa);

		ArrayList<Livro> BubbleSortLivros = ListaPesquisa;
		bubbleSort(BubbleSortLivros);
		ToString("BUBBLE", BubbleSortLivros);
	}

	public static String ReadFile() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("src/Files/livros.txt"));

		StringBuilder sb = new StringBuilder();
		String line = br.readLine();

		while (line != null) {
			sb.append(line);
			sb.append(System.lineSeparator());
			line = br.readLine();
		}
		String everything = sb.toString();

		br.close();
		return everything;

	}

	public static void ProcessarLivros(int tamanhoVetor, Livro[] livros, String arquivo) {

		String[] dados = arquivo.split("\n");

		for (int i = 0; i < (tamanhoVetor); i++) {
			String linha = dados[i];

			String[] registro = linha.split("\\|");

			livros[i] = new Livro();

			livros[i].setISBN(Long.parseLong(registro[0]));

			livros[i].setTitulo(registro[1]);

			livros[i].setAutorPrincipal(registro[2]);

			livros[i].setSegundoAutor(registro[3]);

			livros[i].setCategoria(registro[4]);

			livros[i].setDescricao(registro[5]);

			livros[i].setanoPublicacao(Integer.parseInt(registro[6]));

			livros[i].setQuantidadePaginas(Integer.parseInt(registro[7]));

			livros[i].setNotaAvaliacao(Double.parseDouble(registro[8]));

			livros[i].setQuantidadeAvaliacoes(Integer.parseInt(registro[9].replace("\r", "")));

		}
	}

	private static void PesquisaLivros(Livro[] livros, ArrayList listaPesquisa) {

		boolean stopProcess = false;

		while (!stopProcess) {
			String[] criterios = MyIO.readLine().split(";");
			if (criterios[0].equals("FIM")) {
				stopProcess = true;
			}
			for (int j = 0; j < livros.length; j++) {
				if (criterios[0].equals(livros[j].getTitulo())
						&& criterios[1].equals(livros[j].getAnoPublicacaoToString())
						&& criterios[2].equals(livros[j].getAutorPrincipal())) {
					listaPesquisa.add(livros[j]);
					break;
				}
			}
		}

	}

	private static void ToString(String metodo, ArrayList<Livro> livros) {
		String livroPrint = new String();
		for (int i = 0; i < livros.size() - 1; i++) {
			if (!(livros.get(i).getSegundoAutor() == null)) {
				livroPrint = "[" + livros.get(i).getCategoria() + "] [" + livros.get(i).getNotaAvaliacao() + "] ["
						+ livros.get(i).getQuantidadeAvaliacoes() + "] " + livros.get(i).getAutorPrincipal() + ". "
						+ livros.get(i).getTitulo() + ". " + livros.get(i).getanoPublicacao() + ". ISBN: "
						+ livros.get(i).getISBN();
			} else {
				livroPrint = "[" + livros.get(i).getCategoria() + "] [" + livros.get(i).getNotaAvaliacao() + "] ["
						+ livros.get(i).getQuantidadeAvaliacoes() + "] " + livros.get(i).getAutorPrincipal() + ", "
						+ livros.get(i).getSegundoAutor() + ". " + livros.get(i).getTitulo() + ". "
						+ livros.get(i).getanoPublicacao() + ". ISBN: " + livros.get(i).getISBN();
			}
			MyIO.println(livroPrint);
		}
		MyIO.println("## " + metodo + " [COMPARACOES] [" + comparacoes + "] [MOVIMENTACOES] [" + movimentacoes + "]");
	}

	public static void bubbleSort(ArrayList<Livro> listaPesquisa) {
		for (int j = 0; j < listaPesquisa.size(); j++) {
			for (int i = 0; i < (listaPesquisa.size() - 1); i++) {
				if (ehMaior(listaPesquisa.get(i), listaPesquisa.get(i + 1))) {
					swap(listaPesquisa, i);
				}
			}
		}
	}

	private static boolean ehMaior(Livro livro, Livro livro2) {
		if (livro.getCategoria().compareTo(livro2.getCategoria()) > 0) {
			comparacoes++;
			return true;
		} else if (livro.getCategoria().compareTo(livro2.getCategoria()) == 0) {
			comparacoes += 2;
			if (livro.getNotaAvaliacao() < livro2.getNotaAvaliacao()) {
				comparacoes++;
				return true;
			}
		} else if (livro.getNotaAvaliacao() == livro2.getNotaAvaliacao()) {
			comparacoes += 3;
			if (livro.getQuantidadeAvaliacoes() < livro2.getQuantidadeAvaliacoes()) {
				comparacoes++;
				return true;
			}
		}
		comparacoes += 3;
		return false;
	}
	
	private static boolean ehMenor(Livro livro, Livro livro2) {
		if (livro.getCategoria().compareTo(livro2.getCategoria()) < 0) {
			comparacoes++;
			return true;
		} else if (livro.getCategoria().compareTo(livro2.getCategoria()) == 0) {
			comparacoes += 2;
			if (livro.getNotaAvaliacao() > livro2.getNotaAvaliacao()) {
				comparacoes++;
				return true;
			}
		} else if (livro.getNotaAvaliacao() == livro2.getNotaAvaliacao()) {
			comparacoes += 3;
			if (livro.getQuantidadeAvaliacoes() > livro2.getQuantidadeAvaliacoes()) {
				comparacoes++;
				return true;
			}
		}
		comparacoes += 3;
		return false;
	}

	private static void ehMenor() {

	}

	private static void swap(ArrayList<Livro> listaPesquisa, int i) {
		Livro aux;
		Livro aux2;
		aux = listaPesquisa.get(i);
		aux2 = listaPesquisa.get(i + 1);

		listaPesquisa.remove(i);
		listaPesquisa.add(i, aux2);
		listaPesquisa.remove(i + 1);
		listaPesquisa.add(i + 1, aux);
		movimentacoes++;

	}
}

class Livro {

	private long ISBN;

	private String titulo;

	private String autorPrincipal;

	private String segundoAutor;

	private String categoria;

	private String descricao;

	private int anoPublicacao;

	private int quantidadePaginas;

	private double notaAvaliacao;

	private int quantidadeAvaliacoes;

	public Livro() {

	}

	public Livro(String titulo, String autor, int anoPublicacao) {
		this.titulo = titulo;
		this.autorPrincipal = autor;
		this.anoPublicacao = anoPublicacao;

	}

	public long getISBN() {

		return ISBN;

	}

	public void setISBN(long iSBN) {

		ISBN = iSBN;

	}

	public String getTitulo() {

		return titulo;

	}

	public void setTitulo(String titulo) {

		this.titulo = titulo;

	}

	public String getAutorPrincipal() {

		return autorPrincipal;

	}

	public void setAutorPrincipal(String autorPrincipal) {

		this.autorPrincipal = autorPrincipal;

	}

	public String getSegundoAutor() {

		return segundoAutor;

	}

	public void setSegundoAutor(String segundoAutor) {

		this.segundoAutor = segundoAutor;

	}

	public String getCategoria() {

		return categoria;

	}

	public void setCategoria(String categoria) {

		this.categoria = categoria;

	}

	public String getDescricao() {

		return descricao;

	}

	public void setDescricao(String descricao) {

		this.descricao = descricao;

	}

	public int getanoPublicacao() {

		return anoPublicacao;

	}

	public String getAnoPublicacaoToString() {

		return Integer.toString(anoPublicacao);

	}

	public void setanoPublicacao(int anoPublicacao) {

		this.anoPublicacao = anoPublicacao;

	}

	public int getQuantidadePaginas() {

		return quantidadePaginas;

	}

	public void setQuantidadePaginas(int quantidadePaginas) {

		this.quantidadePaginas = quantidadePaginas;

	}

	public double getNotaAvaliacao() {

		return notaAvaliacao;

	}

	public void setNotaAvaliacao(double notaAvaliacao) {

		this.notaAvaliacao = notaAvaliacao;

	}

	public int getQuantidadeAvaliacoes() {

		return quantidadeAvaliacoes;

	}

	public void setQuantidadeAvaliacoes(int quantidadeAvaliacoes) {

		this.quantidadeAvaliacoes = quantidadeAvaliacoes;

	}
}