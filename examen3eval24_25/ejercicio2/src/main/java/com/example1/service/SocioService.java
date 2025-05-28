package com.example1.service;

import com.example1.repositories.SocioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SocioService {
    @Autowired
    private final SocioRepository socioRespository;

}
