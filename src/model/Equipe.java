package model;

public class Equipe {

    private String nomeTime;
    private String nomeFatec;
    private int problemasResolvidos;
    private int tempoTotal;

    public Equipe(String pipe) {

        String[] dados = pipe.trim().split("\\|");

        setNomeTime(dados[0]);
        setNomeFatec(dados[1]);
        this.problemasResolvidos = Integer.parseInt(dados[2]);
        this.tempoTotal = Integer.parseInt(dados[3]);
    }

    public String getNomeTime() {
        return nomeTime;
    }

    public void setNomeTime(String nomeTime) {
        if (nomeTime.length() > 25) {
            throw new IllegalArgumentException("Nome do time deve ter no máximo 25 caracteres");
            
        }
        
        this.nomeTime = nomeTime;
    }

    public String getNomeFatec() {
    	
        return nomeFatec;
    }

    public void setNomeFatec(String nomeFatec) {
        if (nomeFatec.length() > 25) {
            throw new IllegalArgumentException("Nome da Fatec deve ter no máximo 25 caracteres");
            
        }
        
        this.nomeFatec = nomeFatec;
    }

    public int getProblemasResolvidos() {
    	
        return problemasResolvidos;
    }

    public int getTempoTotal() {
    	
        return tempoTotal;
    }

    @Override
    public String toString() {
    	
        return nomeTime + " – " + nomeFatec + " (" + problemasResolvidos + "," + tempoTotal + ")";
    }
}