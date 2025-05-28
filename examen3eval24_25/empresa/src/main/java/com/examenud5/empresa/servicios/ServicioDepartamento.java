package com.examenud5.empresa.servicios;

import com.examenud5.empresa.repositorio.RepositorioDepartamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioDepartamento {
    @Autowired
    private RepositorioDepartamento repoDepartamento;
}
