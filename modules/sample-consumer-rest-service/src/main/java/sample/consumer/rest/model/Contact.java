package sample.consumer.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contact {

    @SerializedName("correoElectronico")
    @Expose
    private String correoElectronico;
    @SerializedName("telefonoMovil")
    @Expose
    private Long telefonoMovil;
    @SerializedName("telefonoFijo")
    @Expose
    private Long telefonoFijo;
    @SerializedName("telefonoLaboral")
    @Expose
    private Long telefonoLaboral;
    @SerializedName("fechaActualizacion")
    @Expose
    private String fechaActualizacion;

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Long getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(Long telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public Long getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(Long telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public Long getTelefonoLaboral() {
        return telefonoLaboral;
    }

    public void setTelefonoLaboral(Long telefonoLaboral) {
        this.telefonoLaboral = telefonoLaboral;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

}