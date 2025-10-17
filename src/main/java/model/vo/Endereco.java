package model.vo;

public class Endereco {
    private int idEndereco;
    private int idDistrito;
    private String ruaAvenida;
    private String numero;

    public Endereco(int idEndereco, int idDistrito, String ruaAvenida, String numero) {
        this.idEndereco = idEndereco;
        this.idDistrito = idDistrito;
        this.ruaAvenida = ruaAvenida;
        this.numero = numero;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public int getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(int idDistrito) {
        this.idDistrito = idDistrito;
    }

    public String getRuaAvenida() {
        return ruaAvenida;
    }

    public void setRuaAvenida(String ruaAvenida) {
        this.ruaAvenida = ruaAvenida;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}