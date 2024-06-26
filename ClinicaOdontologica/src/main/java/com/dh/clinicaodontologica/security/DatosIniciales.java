package com.dh.clinicaodontologica.security;

import com.dh.clinicaodontologica.entity.Usuario;
import com.dh.clinicaodontologica.entity.UsuarioRole;
import com.dh.clinicaodontologica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatosIniciales implements ApplicationRunner {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String passSinCifrar = "user";
        String passCifrado = passwordEncoder.encode(passSinCifrar);
        Usuario user = new Usuario("John Doe", "user", "john.doe@moonlight.com", passCifrado, UsuarioRole.ROLE_USER);
        usuarioRepository.save(user);


        String passSinCifrarA = "admin";
        String passCifradoA = passwordEncoder.encode(passSinCifrarA);
        Usuario jorgito = new Usuario("Jorge Pereyra", "jpreyra", "jorge.pereyra@digitalhouse.com", passCifradoA,
                UsuarioRole.ROLE_ADMIN);
        Usuario valeria = new Usuario("Valeria Muñoz", "vmunoz", "vmunoz@freshdent.com", passCifradoA, UsuarioRole.ROLE_ADMIN);
        Usuario jeremias = new Usuario("Jeremias Ibarra", "jearcode", "jearcode@freshdent.com", passCifradoA, UsuarioRole.ROLE_ADMIN);
        usuarioRepository.save(valeria);
        usuarioRepository.save(jeremias);
        usuarioRepository.save(jorgito);

    }
}
