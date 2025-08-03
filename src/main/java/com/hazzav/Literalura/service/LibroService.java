package com.hazzav.Literalura.service;

import com.hazzav.Literalura.model.*;
import com.hazzav.Literalura.repositorio.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private AutorService autorService;

    private ConsumoAPI consumoAPI;
    private ConvierteDatos conversor;
    private Scanner sc = new Scanner(System.in);




}
