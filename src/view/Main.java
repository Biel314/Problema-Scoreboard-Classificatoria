package view;

import controller.CtlClassificar;
import model.Equipe;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome da Sede da Fatec: ");
        String nomeFatec = scanner.nextLine();

        int vagasTotais = 0;
        int vagasExtras = 0;
        int timesParticipantes = 0;

        int teste = 11;

        while (vagasTotais < 2 || vagasTotais > 100) {

            System.out.print("Digite a quantidade total de vagas (2 a 100): ");
            vagasTotais = scanner.nextInt();

            if (vagasTotais < 2 || vagasTotais > 100) {
                System.out.println("Nao pode ser menor do que 2 e nem maior do que 100");
            }
        }

        while (vagasExtras < 2 || vagasExtras > 6) {

            System.out.print("Digite a quantidade de vagas extras (2 a 6): ");
            vagasExtras = scanner.nextInt();

            if (vagasExtras < 2 || vagasExtras > 6) {
                System.out.println("Nao pode ser menor do que 1 nem maior do que 6");
            }
        }

        while (timesParticipantes < teste || timesParticipantes > 500) {

            System.out.print("Digite a quantidade de times participantes (11 a 500): ");
            timesParticipantes = scanner.nextInt();
        }

        scanner.nextLine();

        CtlClassificar classificar = new CtlClassificar(nomeFatec, vagasTotais, vagasExtras);

        List<Equipe> listaDeTimes = new ArrayList<>();

        System.out.println("Digite os dados dos times (Time|Fatec|Problemas|Tempo):");
        System.out.println("Separe utilizando pipes ----> | ");
        
        for (int i = 0; i < timesParticipantes; i++) {
            String linha = scanner.nextLine();
            Equipe novoTime = new Equipe(linha);
            listaDeTimes.add(novoTime);
            
        }

        classificar.criterioDesqualificar(listaDeTimes);

        classificar.classificadosFinal.sort(
                Comparator.comparing(Equipe::getNomeTime)
                
        );

        System.out.println("Classificados para a Final");

        for (Equipe t : classificar.classificadosFinal) {
            System.out.println(t);
            
        }

        scanner.close();
    }
}