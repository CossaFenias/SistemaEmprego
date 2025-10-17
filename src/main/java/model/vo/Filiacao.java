package model.vo;

public class Filiacao {
    private int idCandidato;
    private String nomePai;
    private String nomeMae;

    public Filiacao(int idCandidato, String nomePai, String nomeMae) {
        this.idCandidato = idCandidato;
        this.nomePai = nomePai;
        this.nomeMae = nomeMae;
    }

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }
}