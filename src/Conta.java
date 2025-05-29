
public class Conta {

    public String pin;
    public float saldo;
    public long iban;
    public long randomIBAN;
    public long min,max;
    private Utilizador titular;
    public String tipoDeConta;

    

    //CONSTRUTOR DE CONTAS
    public Conta(String pin, float saldo, long iban, Utilizador titular) {
        this.pin = pin;
        this.saldo = saldo;
        this.iban = iban;
        this.titular = titular;
        this.tipoDeConta="Conta à ordem";
    }

    //METODOS GETTERS E SETTERS
    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
    
    public float getSaldo() {
        return saldo;
    }
    
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
    
    public long getIban() {
        return iban;
    }
    
    public String getTipoDeConta() {
        return tipoDeConta;
    }

    public void setTipoDeConta(String tipoDeConta) {
        this.tipoDeConta = tipoDeConta;
    }

    public void setIban(long iban) {
        this.iban = iban;
    }

    public Utilizador getTitular() {
        return titular;
    }

    public void setTitular(Utilizador titular) {
        this.titular = titular;
    }

    //METODO PARA CRIAR O IBAN DA CONTA 
    public long criarIban() {
        
        min = 100000000000000000L;
        max = 999999999999999999L;

        iban = (long)Math.floor(Math.random()*(max-min+1)+min);

        return iban;
        
    }

}
