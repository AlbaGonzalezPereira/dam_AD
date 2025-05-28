package com.example1;

import com.example1.entities.Libro;
import com.example1.entities.Prestamo;
import com.example1.entities.Socio;
import com.example1.service.LibroService;
import com.example1.service.PrestamoService;
import com.example1.service.SocioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
