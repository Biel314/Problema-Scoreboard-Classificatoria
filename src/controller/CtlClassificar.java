package controller;

import model.Equipe;
import java.util.*;

public class CtlClassificar {

    private String sede;
    private int vagasTotais;
    private int vagasExtras;

    public List<Equipe> classificadosFinal = new ArrayList<>();
    public List<Equipe> listaEspera = new ArrayList<>();
    public List<Equipe> desclassificados = new ArrayList<>();

    public CtlClassificar(String sede, int vagasTotais, int vagasExtras) {
        this.sede = sede;
        this.vagasTotais = vagasTotais;
        this.vagasExtras = vagasExtras;
        
    }

    public void ordenarPontuacao(List<Equipe> lista) {
        lista.sort((a, b) -> {
            if (b.getProblemasResolvidos() != a.getProblemasResolvidos()) {
                return b.getProblemasResolvidos() - a.getProblemasResolvidos();
                
            }

            return a.getTempoTotal() - b.getTempoTotal();
        });
    }

    public void criterioDesqualificar(List<Equipe> listaDeTimes) {

        List<Equipe> participantes = new ArrayList<>();

        for (Equipe t : listaDeTimes) {
            if (t.getProblemasResolvidos() > 0) {
                participantes.add(t);
                
            } else {
                desclassificados.add(t);
                
            }
        }

        ordenarPontuacao(participantes);

        criterioMelhorSede(participantes);
        criterioMelhorFatecs(participantes);
        criterioRemanescentes(participantes);

        for (Equipe t : participantes) {
            if (!classificadosFinal.contains(t)) {
                listaEspera.add(t);
                
            }
        }
    }

    public void criterioMelhorSede(List<Equipe> participantes) {

        int limiteSede = vagasExtras + 1;
        int qtdAtual = 0;

        for (Equipe t : participantes) {
            if (t.getNomeFatec().equals(sede) && qtdAtual < limiteSede) {
                classificadosFinal.add(t);
                qtdAtual++;
                
            }
        }
    }

    public void criterioMelhorFatecs(List<Equipe> participantes) {
    	
        Set<String> fatecsComVaga = new HashSet<>();

        for (Equipe t : classificadosFinal) {
            fatecsComVaga.add(t.getNomeFatec());
            
        }

        for (Equipe t : participantes) {
            if (classificadosFinal.size() < vagasTotais) {
                if (!fatecsComVaga.contains(t.getNomeFatec())) {
                    classificadosFinal.add(t);
                    fatecsComVaga.add(t.getNomeFatec());
                    
                }
            }
        }
    }

    public void criterioRemanescentes(List<Equipe> participantes) {

        for (Equipe t : participantes) {
            if (classificadosFinal.size() < vagasTotais) {
                if (!classificadosFinal.contains(t)) {
                    classificadosFinal.add(t);
                    
                }
            }
        }
    }
}