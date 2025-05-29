import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.*;

public class App {

    public static void main(String[] args) {

        boolean a=true,b=true,validacao=false,d=true;
        int opcao,i,opcao2,quantias2,c,verificar=1,posicao=0,identificacao,anoNascimento,mesNascimento,diaNascimento,adminId,opcaoGestor,IdIgual;
        int opcaoManutencaoConta,idUtilizador,opcaopin,ibanIgual,diaUtilizador,mesUtilizador,anoUtilizador,opcaomanutencaoCliente;
        long iban,transferenciaIban,nib;
        String menu,menuIban,menuPin,menuConta,menuQuantia,menuTransferenciaIban,quantias,nome,loginAdminId,adminPass,menuGestor,manutencaoConta,contaEscolhida;
        String tipodeconta,codigo,pin,nomeUtilizador,manutencaoCliente;
        String[] tiposDeContas={"Conta à ordem","Conta estudante"};
        Object[] confirmacao ={"OK", "Cancelar"};
        float valor=0,quantia,dinheiro;

        ArrayList<Conta> contas = new ArrayList<Conta>(); //ARRAYLIST PARA GUARDAR AS CONTAS
        ArrayList<Utilizador> utilizadores = new ArrayList<Utilizador>(); //ARRAYLIST PARA GUARDAR OS UTILIZADORES

        //CODIGO PARA CARREGAR OS DADOS QUE ESTAO NO FICHEIRO Dados.txt PARA A ARRAYLIST<Utilizador> 
        try {
            FileReader fr = new FileReader("Dados.txt");
            BufferedReader br = new BufferedReader(fr);

            String linha = br.readLine();

            while (linha!= null) {
                String[] textoSplit =linha.split(";"); 

                anoNascimento = Integer.parseInt(textoSplit[7]);
                mesNascimento = Integer.parseInt(textoSplit[6]);
                diaNascimento = Integer.parseInt(textoSplit[5]);
                nome = textoSplit[4];
                identificacao = Integer.parseInt(textoSplit[8]);

                nib = Long.parseLong(textoSplit[0]);
                codigo = textoSplit[2];
                dinheiro = Float.parseFloat(textoSplit[1]);
                tipodeconta=textoSplit[3];

                if(verificar==1){
                    Utilizador utilizador = new Utilizador(anoNascimento,mesNascimento,diaNascimento,nome,identificacao);
                    utilizadores.add(utilizador);
                    if(tipodeconta.contains("Conta à ordem")){
                        Conta co = new Conta(codigo, dinheiro, nib, utilizador);
                        utilizador.contasClientes.add(co);
                    }
                    if(tipodeconta.contains("Conta estudante")){
                        ContaEstudante ce = new ContaEstudante(codigo, dinheiro, nib, utilizador);
                        utilizador.contasClientes.add(ce);
                    }
                    
                }
                if(verificar!=1){
                    for(i=0;i<utilizadores.size();i++){
                        if(identificacao==utilizadores.get(i).getId()){
                           validacao=true;
                           posicao=i;
                        }
                    }
                }
                if(validacao==false&&verificar!=1){
                    Utilizador utilizador = new Utilizador(anoNascimento,mesNascimento,diaNascimento,nome,identificacao);
                    utilizadores.add(utilizador);
                    if(tipodeconta.contains("Conta à ordem")){
                        Conta co = new Conta(codigo, dinheiro, nib, utilizador);
                        utilizador.contasClientes.add(co);
                    }
                    if(tipodeconta.contains("Conta estudante")){
                        ContaEstudante ce = new ContaEstudante(codigo, dinheiro, nib, utilizador);
                        utilizador.contasClientes.add(ce);
                    }
                    
                }
                if(validacao==true&&verificar!=1){
                    if(tipodeconta.contains("Conta à ordem")){
                        Conta co = new Conta(codigo, dinheiro, nib, utilizadores.get(posicao));
                        utilizadores.get(posicao).contasClientes.add(co);
                    }
                    if(tipodeconta.contains("Conta estudante")){
                        ContaEstudante ce = new ContaEstudante(codigo, dinheiro, nib, utilizadores.get(posicao));
                        utilizadores.get(posicao).contasClientes.add(ce);
                    }
                }
                linha=br.readLine();
                verificar=0;
                validacao=false;
                posicao=0;
            }
            fr.close();
        } 
        catch(Exception e) {
           System.out.println(e); 
        }

        //CODIGO PARA INTRODUZIR NA ARRAYLIST<Conta> AS CONTAS DE CADA CLIENTE
        for(i=0;i<utilizadores.size();i++){
            for(c=0;c<utilizadores.get(i).contasClientes.size();c++){
                contas.add(utilizadores.get(i).contasClientes.get(c));
            }
        }
        
        Administrador admin = new Administrador("admin123", 1234);

        //INICIO DO CODIGO PRINCIPAL    
        while(a == true){

            while (true) {
                menu = JOptionPane.showInputDialog(null, "1-Entrar na conta\n2-Sair\nQual é a opção que deseja?");
            
                try {
                    opcao = Integer.parseInt(menu);
                    if (opcao > 2 && opcao != 9999) {
                        JOptionPane.showMessageDialog(null, "Digite um número válido (1 ou 2)");
                    } else {
                        break; // Se o número for válido, saia do loop
                    }
                } catch (NumberFormatException e) {
                   System.out.print(e);
                }
            }

            switch (opcao) {
                case 1:

                do {
                    menuIban = JOptionPane.showInputDialog(null,"Introduza o seu IBAN");
                    
                } while (menuIban.length()!=18);

                iban=Long.parseLong(menuIban);

                for(i=0;i<contas.size();i++){
                    
                    if(contas.get(i).getIban()==iban){
                        do {
                            menuPin = JOptionPane.showInputDialog(null,"Introduza o seu PIN");
                                
                        } while (menuPin.length()!=4);
                                
                        pin=menuPin;

                        if(contas.get(i).getPin().equals(pin)){

                            //GUARDA NO FICHEIRO logins.txt A DATA E HORA DE ENTRADA DE CADA CONTA, NESTE CASO O IBAN
                            try{
                                FileWriter login = new FileWriter("logins.txt", true);
                                String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
                                login.write("\nIBAN: "+contas.get(i).getIban()+"    Data/Hora: "+timeStamp);
                                login.close();
                            }
                            catch (Exception e) {
                                System.out.println(e);
                            }
                            //Menu de Operações
                            while(b==true){

                                while (true) {
                                     menuConta = JOptionPane.showInputDialog(null, "1-Verificar saldo\n2-Levantamentos\n3-Depositar\n4-Transferências\n5-Sair\nQual é a opção que deseja?");
                                
                                    try {
                                        opcao2 = Integer.parseInt(menuConta);
                                        if (opcao2 > 5) {
                                            JOptionPane.showMessageDialog(null, "Digite um número válido (de 1 a 5)!");
                                        } else {
                                            break; // Se o número for válido, sai do loop
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.print(e);
                                    }
                                }

                                if(opcao2==1){
                                        
                                    JOptionPane.showMessageDialog(null,String.format("O seu saldo é: %.2f €",contas.get(i).getSaldo()));
                                            
                                }
                                //Opções de Dinheiro  
                                if(opcao2==2||opcao2==3){

                                    do{
                                        quantias=JOptionPane.showInputDialog(null,"1-20€\n2-50€\n3-100€\n4-200€\n5-Outra quantia\n6-Voltar");
                                        quantias2=Integer.parseInt(quantias);
                                    }
                                    while(quantias2<1||quantias2>6);
                                            
                                    switch (quantias2) {
                                        case 1:
                                            valor=20;                                                                                                      
                                            break;
                                            
                                        case 2:
                                            valor=50;                                                   
                                            break;
                                                
                                        case 3:
                                            valor=100;                                 
                                            break;

                                        case 4:
                                            valor=200;
                                            break;
                                                
                                        case 5:
                                            do{
                                                menuQuantia=JOptionPane.showInputDialog(null,"Digite a quantia");
                                                quantia=Float.parseFloat(menuQuantia);
                                            }
                                            while(quantia<0);
                                            valor=quantia;                                                   
                                                    
                                            break;
                                                 
                                        default:
                                            break;
                                    }
                                    //LEVANTAMENTO
                                    if(opcao2==2){ 

                                        if(contas.get(i).getSaldo()<valor){
                                            JOptionPane.showMessageDialog(null,"O seu saldo é insuficiente");
                                        }

                                        if(contas.get(i).getSaldo()>=valor){
                                            contas.get(i).setSaldo(contas.get(i).getSaldo()-valor);
                                        }
                                    }
                                    //DEPOSITO
                                    if(opcao2==3){ 

                                        contas.get(i).setSaldo(contas.get(i).getSaldo()+valor);

                                    }

                                }
                                //TRANSFERENCIA
                                if(opcao2==4){
                                       
                                     
                                    do{
                                        menuTransferenciaIban=JOptionPane.showInputDialog(null,"Digite o IBAN da conta destinatária");
                                        transferenciaIban=Long.parseLong(menuTransferenciaIban);

                                    }    
                                    while(menuTransferenciaIban.length()!=18 || transferenciaIban==contas.get(i).getIban());

                                    do{
                                        menuQuantia=JOptionPane.showInputDialog(null,"Digite a quantia a ser transferida");
                                        quantia=Float.parseFloat(menuQuantia);
                                    }
                                    while(quantia<0);

                                    valor=quantia;

                                    if(contas.get(i).getSaldo()<valor){
                                        JOptionPane.showMessageDialog(null,"O seu saldo é insuficiente");
                                    }
                                    
                                    if(contas.get(i).getSaldo()>=valor){
                                        boolean existe=false;
                                        for(c=0; c<contas.size();c++){
                                            
                                            if(contas.get(c).getIban()==transferenciaIban){
                                                existe=true;                                            /*Verifica se a conta existe
                                                                                                        /Se existir tira o dinheiro e deposita na outra conta*/
                                                contas.get(c).setSaldo(contas.get(c).getSaldo()+valor);
                                                contas.get(i).setSaldo(contas.get(i).getSaldo()-valor);

                                            }

                                        }

                                        if(existe==false){
                                            JOptionPane.showMessageDialog(null,"Não foi possível encontrar a conta");
                                        }
       
                                    }

                                }

                                if(opcao2==5){

                                    b=false;//Volta para a tela inicial
                                }

                            }

                        }

                    }
                }
                //Quando saimos do programa ele grava as alterações no ficheiro dados.txt
                    break;
                case 2:

                    a=false;

                    //CODIGO PARA GUARDAR OS DADOS QUE ESTAO NA ARRAYLIST<Utilizador> PARA O FICHEIRO Dados.txt
                    try{
                        FileWriter dados = new FileWriter("Dados.txt");
                        
                        for(i=0;i<utilizadores.size();i++){
                            if(utilizadores.get(i).contasClientes.size()==0){
                                dados.write(0+";"+0+";"+0+";"+null+";"+utilizadores.get(i).getNome()+";"+utilizadores.get(i).getDiaNascimento()+";"+utilizadores.get(i).getMesNascimento()+";"+utilizadores.get(i).getAnoNascimento()+';'+utilizadores.get(i).getId()+";"+utilizadores.get(i)+"\n");
                            }
                            else{
                                for(c=0;c<utilizadores.get(i).contasClientes.size();c++){
                                    dados.write(utilizadores.get(i).contasClientes.get(c).getIban()+";"+utilizadores.get(i).contasClientes.get(c).getSaldo()+";"+utilizadores.get(i).contasClientes.get(c).getPin()+";"+utilizadores.get(i).contasClientes.get(c).getTipoDeConta()+";"+utilizadores.get(i).getNome()+";"+utilizadores.get(i).getDiaNascimento()+";"+utilizadores.get(i).getMesNascimento()+";"+utilizadores.get(i).getAnoNascimento()+';'+utilizadores.get(i).getId()+";"+utilizadores.get(i)+";"+utilizadores.get(i).contasClientes.get(c)+"\n");
                            }
                        }
                        }
                    
                        dados.close();
                    }
                    catch (Exception e) {
                        System.out.println(e);
                    }

                    break;
                    //Caso do Administrador
                case 9999:
                    do {
                        loginAdminId=JOptionPane.showInputDialog(null,"Introduza o ID");
                    } while (loginAdminId.length()!=4);

                    adminId=Integer.parseInt(loginAdminId);

                    adminPass=JOptionPane.showInputDialog(null,"Introduza a palavra-passe");
                    
                    if(admin.getId()==adminId && admin.getPassword().equals(adminPass)){
                        //Caso a palavra passe coincida com o ID do Administador ele tem acesso ao menu
                        while (d==true) {
                            opcaoGestor = 0;
                            boolean entradaValida = false;
                            
                            while (!entradaValida) {
                                menuGestor = JOptionPane.showInputDialog(null, "O que deseja fazer?\n1-Manutenção de Contas\n2-Manutenção de Utilizadores\n3-Sair");
                            
                                try {
                                    opcaoGestor = Integer.parseInt(menuGestor);
                                    if (opcaoGestor >= 1 && opcaoGestor <= 3) {
                                        entradaValida = true;
                                    } else {
                                        // Mensagem de erro caso o número seja maior que 3
                                        JOptionPane.showMessageDialog(null, "Opção inválida. Por favor, escolha uma opção de 1 a 3.");
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.print(e);
                                }
                            }
                            if(opcaoGestor==1){
                                //Criação de Contas
                                manutencaoConta=JOptionPane.showInputDialog(null,"1-Criar Conta\n2-Sair");
                                opcaoManutencaoConta=Integer.parseInt(manutencaoConta);

                                switch (opcaoManutencaoConta) {
                                    case 1:
                                       idUtilizador=0;
                                       try{
                                        idUtilizador=Integer.parseInt(JOptionPane.showInputDialog(null,"Introduza o ID do utilizador"));
                                       }
                                       catch(Exception e){
                                            System.out.print(e);
                                       } 
                                       for(c=0;c<utilizadores.size();c++){
                                            if(utilizadores.get(c).getId()==idUtilizador){//Mensagem com a opção do tipo de contas existentes
                                                contaEscolhida=(String) JOptionPane.showInputDialog(null,"Selecione uma conta","Escolher conta",JOptionPane.QUESTION_MESSAGE, null, tiposDeContas, tiposDeContas[0]);    
                                                pin= "";
                                                if(contaEscolhida!=null){
                                                    do{
                                                        JPasswordField campoPin = new JPasswordField();//O cliente escolhe o pin
                                                        opcaopin = JOptionPane.showOptionDialog(null,campoPin,"Escolha o Pin",JOptionPane.NO_OPTION,JOptionPane.PLAIN_MESSAGE,null,confirmacao,confirmacao[0]);
                                                        try{
                                                            if(opcaopin==0){
                                                                Integer.parseInt(String.valueOf(campoPin.getPassword()));
                                                                pin=String.valueOf(campoPin.getPassword());
                                                            }
                                                            if(opcaopin !=0){
                                                                break;
                                                            }
                                                        }
                                                        catch(Exception e){
                                                            JOptionPane.showMessageDialog(null,"Codigo Pin só pode conter quatro dígitos");
                                                            System.out.println(e);
                                                        }
                                                        campoPin=null;

                                                    }while(pin.length()!=4);

                                                    
                                                }//Se a conta for estudante e o cliente tiver 21 ou menos, a conta é criada com 100 de saldo automaticamente
                                                if(contaEscolhida.equals("Conta estudante")){
                                                    if(utilizadores.get(c).calcularIdade()<=21){
                                                        ContaEstudante ce = new ContaEstudante(pin, 100, 0, utilizadores.get(c));
                                                        ce.setIban(ce.criarIban());
                                                        do{
                                                            ibanIgual=0;//Cria um iban diferente das contas existentes
                                                            for(int y=0;y<contas.size();y++){
                                                                if(ce.getIban()==contas.get(y).getIban()){
                                                                    ibanIgual=1;
                                                                    ce.setIban(ce.criarIban());

                                                                }
                                                            }

                                                        }while(ibanIgual==1);//Adiciona a conta à array das contas
                                                        utilizadores.get(c).contasClientes.add(ce);
                                                        contas.add(ce);
                                                        JOptionPane.showMessageDialog(null,"Conta criada com sucesso");
                                                               

                                                }
                                                else {
                                                    JOptionPane.showMessageDialog(null,"Erro ao criar conta");
                                                }

                                                }
                                                else if(contaEscolhida.equals("Conta à ordem")){
                                                    
                                                        Conta co = new Conta(pin, 0, 0, utilizadores.get(c));
                                                        co.setIban(co.criarIban());
                                                        do{
                                                            ibanIgual=0;
                                                            for(int y=0;y<contas.size();y++){//Cria um iban diferente das contas existentes
                                                                if(co.getIban()==contas.get(y).getIban()){
                                                                    ibanIgual=1;
                                                                    co.setIban(co.criarIban());

                                                                }
                                                            }

                                                        }while(ibanIgual==1);
                                                        utilizadores.get(c).contasClientes.add(co);//Adiciona a conta à array das contas
                                                        contas.add(co);
                                                        JOptionPane.showMessageDialog(null,"Conta criada com sucesso");
                                                }
                                                else{
                                                    JOptionPane.showMessageDialog(null,"Erro ao criar conta");
                                                }
      

                                            }
                                        
                                        }

                                        
                                        break;
                                    case 2:
                                        d=false;//Volta para o menu de Administrador
                                    default:
                                        break;
                                }
                                
                            }
                            if(opcaoGestor==2){//Opção de criar Clientes
                                manutencaoCliente=JOptionPane.showInputDialog(null,"1-Criar Cliente\n2-Sair");
                                opcaomanutencaoCliente=Integer.parseInt(manutencaoCliente);
                                switch (opcaomanutencaoCliente) {
                                    case 1:

                                        nomeUtilizador=JOptionPane.showInputDialog(null,"Digite o seu nome");//Cliente escolhe o nome
                                        do{
                                            while (true) {
                                                String input = JOptionPane.showInputDialog(null, "Digite o seu dia de nascimento");
                                                //Esocolhe o dia de nascimento
                                                try {
                                                    diaUtilizador = Integer.parseInt(input);
                                                    if (diaUtilizador > 31) {//Caso o número seja maior que 31 da erro
                                                        JOptionPane.showMessageDialog(null, "Digite um número válido");
                                                    } else {
                                                        break; // Se o número for válido, sai do loop
                                                    }
                                                } catch (NumberFormatException e) {
                                                    System.out.print(e);
                                                }
                                            }
                                            while (true) {
                                                String input = JOptionPane.showInputDialog(null, "Digite o seu mês de nascimento em números");
                                                
                                                try {
                                                    mesUtilizador = Integer.parseInt(input);
                                                    if (mesUtilizador > 12) {//Caso o número seja maior que 12 da erro
                                                        JOptionPane.showMessageDialog(null, "Digite um número válido");
                                                    } else {
                                                        break; // Se o número for válido, saia do loop
                                                    }
                                                } catch (NumberFormatException e) {
                                                   System.out.print(e);
                                                }
                                            }
                                            while (true) {
                                                String input = JOptionPane.showInputDialog(null, "Digite o seu ano de nascimento");
                                            
                                                try {
                                                    anoUtilizador = Integer.parseInt(input);
                                                    if (anoUtilizador > 2023 || anoUtilizador < 1923) {//Caso o número seja maior que 2023 e menor que 1923 da erro
                                                        JOptionPane.showMessageDialog(null, "Digite um número válido (entre 1923 e 2023)");
                                                    } else {
                                                        break; // Se o número for válido, saia do loop
                                                    }
                                                } catch (NumberFormatException e) {
                                                    System.out.print(e);
                                                }
                                            }
                                    
                                        }while(diaUtilizador>28 && mesUtilizador==2);//Se a data no dia for maior que 28 e o mes for 2 da erro

                                        Utilizador cliente= new Utilizador(anoUtilizador, mesUtilizador, diaUtilizador, nomeUtilizador, 0);
                                        cliente.setId(cliente.criarID());
                                        JOptionPane.showMessageDialog(null,"Nome: "+cliente.getNome()+"\nData: "+cliente.getDiaNascimento()+"/"+cliente.getMesNascimento()+"/"+cliente.getAnoNascimento()+"\nID do cliente: "+cliente.getId());//Cria o cliente e mostra o ID
                                        do{
                                            IdIgual=0;
                                            for(int y=0;y<utilizadores.size();y++){
                                                if(cliente.getId()==utilizadores.get(y).getId()){//Cria um Id diferente dos outros criados
                                                    IdIgual=1;
                                                    cliente.setId(cliente.criarID());

                                                }
                                            }
                                            
                                        }while(IdIgual==1);
                                        utilizadores.add(cliente);//Adiciona o Cliente à Array
                                        
                                        
                                        break;
                                    case 2:
                                        d=false;
                                    default:
                                        break;
                                }

                            }
                            
                          
                            if(opcaoGestor==3){//Volta atrás
                                d=false;

                            }
                            
                        
                        }
    
                    }

                    break;
            
                default:
                    break;
            }
        }
    }  
}







