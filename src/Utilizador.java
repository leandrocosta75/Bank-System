import java.util.ArrayList;
import java.time.LocalDate;
import java.time.Period;

public class Utilizador {
    
    public int anoNascimento;
    public int mesNascimento;
    public int diaNascimento;
    public String nome;
    public int id;
    public int idade;
    public LocalDate dataNascimento,dataAtual;
    
    //ARRAYLIST PARA ARMAZENAR AS CONTAS QUE PERTENCEM AO MESMO CLIENTE
    ArrayList<Conta> contasClientes= new ArrayList<Conta>();

    //CONSTRUTOR DE UTILIZADORES
    public Utilizador(int anoNascimento, int mesNascimento, int diaNascimento, String nome, int id) {
        this.anoNascimento = anoNascimento;
        this.mesNascimento = mesNascimento;
        this.diaNascimento = diaNascimento;
        this.nome = nome;
        this.id = id;
    }

    //METODOS GETTERS E SETTERS
    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public int getMesNascimento() {
        return mesNascimento;
    }

    public void setMesNascimento(int mesNascimento) {
        this.mesNascimento = mesNascimento;
    }

    public int getDiaNascimento() {
        return diaNascimento;
    }

    public void setDiaNascimento(int diaNascimento) {
        this.diaNascimento = diaNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //METODO PARA CALCULAR A IDADE DO UTILIZADOR
    public int calcularIdade(){
        dataNascimento = LocalDate.of(this.anoNascimento, this.mesNascimento, this.diaNascimento);
        dataAtual = LocalDate.now();
        idade = Period.between(dataNascimento, dataAtual).getYears();
        return idade;
    }
  
public int criarID(){
    int min = 100;
    int max = 999;
    id = (int)Math.floor(Math.random()*(max-min+1)+min);
    return id;
}

}
