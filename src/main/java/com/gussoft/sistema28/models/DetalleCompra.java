package com.gussoft.sistema28.models;

public class DetalleCompra {

    private String strIdCompra;
    private String strIdProducto;
    private String strCantidadDet;
    private String strPrecioDet;
    private String strTotalDet;

    public DetalleCompra() {
    }

    public String getStrIdCompra() {
        return strIdCompra;
    }

    public void setStrIdCompra(String strIdCompra) {
        this.strIdCompra = strIdCompra;
    }

    public String getStrIdProducto() {
        return strIdProducto;
    }

    public void setStrIdProducto(String strIdProducto) {
        this.strIdProducto = strIdProducto;
    }

    public String getStrCantidadDet() {
        return strCantidadDet;
    }

    public void setStrCantidadDet(String strCantidadDet) {
        this.strCantidadDet = strCantidadDet;
    }

    public String getStrPrecioDet() {
        return strPrecioDet;
    }

    public void setStrPrecioDet(String strPrecioDet) {
        this.strPrecioDet = strPrecioDet;
    }

    public String getStrTotalDet() {
        return strTotalDet;
    }

    public void setStrTotalDet(String strTotalDet) {
        this.strTotalDet = strTotalDet;
    }

}
