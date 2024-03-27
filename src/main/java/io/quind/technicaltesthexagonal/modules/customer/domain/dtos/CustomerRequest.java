package io.quind.technicaltesthexagonal.modules.customer.domain.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CustomerRequest {
    private String tipoIdentificacion;
    private Integer numeroIdentificacion;
    private String nombres;
    private String apellidos;
    private String correoElectronico;
    private Date fechaNacimiento;

}
