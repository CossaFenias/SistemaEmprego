package model.vo;

public class Provincia {
    private int idProvincia;
    private String nome;

    public Provincia(int idProvincia, String nome) {
        this.idProvincia = idProvincia;
        this.nome = nome;
    }

    public int getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}