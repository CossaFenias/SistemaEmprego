package model.vo;

public class ClienteTelefone {
    private int idCliente;
    private String telefone;

    public ClienteTelefone() {
    }

    public ClienteTelefone(int idCliente, String telefone) {
        this.idCliente = idCliente;
        this.telefone = telefone;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}