package model.vo;

public class Distrito {
    private int idDistrito;
    private String nome;
    private int idProvincia;

    public Distrito(int idDistrito, String nome, int idProvincia) {
        this.idDistrito = idDistrito;
        this.nome = nome;
        this.idProvincia = idProvincia;
    }

    public int getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(int idDistrito) {
        this.idDistrito = idDistrito;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }
}