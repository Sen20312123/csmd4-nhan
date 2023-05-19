package com.example.model.DTO;

import com.example.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Setter
@Getter
@NoArgsConstructor
//@AllArgsConstructor
public class RoleDTO {

    @NotNull(message = "Roles cannot be left blank ")
    private Long id;

    private String code;

    public RoleDTO(Long id, String code) {
        this.id = id;
        this.code = code;
    }

    public Role toRole(){
       return new Role()
               .setId(id)
               .setCode(code)
               ;
   }
}
