package model.vo;

public class Empresa {
    private int idEmpresa;
    private String nome;
    private String sector;
    private String email;

    public Empresa(int idEmpresa, String nome, String sector, String email) {
        this.idEmpresa = idEmpresa;
        this.nome = nome;
        this.sector = sector;
        this.email = email;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}