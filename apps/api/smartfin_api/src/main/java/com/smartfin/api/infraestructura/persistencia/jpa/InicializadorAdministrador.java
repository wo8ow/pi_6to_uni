package com.smartfin.api.infraestructura.persistencia.jpa;

import com.smartfin.api.infraestructura.persistencia.repositorio_jpa.RolRepositorioJpa;
import com.smartfin.api.infraestructura.persistencia.repositorio_jpa.UsuarioRepositorioJpa;
import com.smartfin.api.infraestructura.persistencia.repositorio_jpa.UsuarioRolRepositorioJpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.UUID;

@Component
public class InicializadorAdministrador implements CommandLineRunner {

    private final UsuarioRepositorioJpa usuarioRepositorioJpa;
    private final RolRepositorioJpa rolRepositorioJpa;
    private final UsuarioRolRepositorioJpa usuarioRolRepositorioJpa;
    private final EntityManager entityManager;

    public InicializadorAdministrador(
            UsuarioRepositorioJpa usuarioRepositorioJpa,
            RolRepositorioJpa rolRepositorioJpa,
            UsuarioRolRepositorioJpa usuarioRolRepositorioJpa,
            EntityManager entityManager
    ) {
        this.usuarioRepositorioJpa = usuarioRepositorioJpa;
        this.rolRepositorioJpa = rolRepositorioJpa;
        this.usuarioRolRepositorioJpa = usuarioRolRepositorioJpa;
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void run(String... args) {

        String correoAdmin = System.getenv().getOrDefault("SMARTFIN_ADMIN_CORREO", "admin@smartfin.com");
        String nombreAdmin = System.getenv().getOrDefault("SMARTFIN_ADMIN_NOMBRE", "Administrador SmartFin");
        String claveAdmin = System.getenv().getOrDefault("SMARTFIN_ADMIN_CLAVE", "Admin123*");

        RolEntidadJpa rolAdmin = rolRepositorioJpa.findByNombre("ADMINISTRADOR")
                .orElseThrow(() -> new IllegalStateException("No existe el rol ADMINISTRADOR. Ejecuta semillas de base de datos."));

        UsuarioEntidadJpa usuario = usuarioRepositorioJpa.findByCorreo(correoAdmin).orElse(null);

        if (usuario == null) {
            usuario = new UsuarioEntidadJpa();
            usuario.setUsuarioId(UUID.randomUUID());
            usuario.setCorreo(correoAdmin);
            usuario.setNombreCompleto(nombreAdmin);
            usuario.setActivo(true);

            OffsetDateTime ahora = OffsetDateTime.now();
            usuario.setCreadoEn(ahora);
            usuario.setActualizadoEn(ahora);

            usuarioRepositorioJpa.save(usuario);
        }

        Query q = entityManager.createNativeQuery("""
            update smartfin.usuario
            set clave_hash = crypt(?1, gen_salt('bf')),
                actualizado_en = now()
            where correo = ?2
        """);
        q.setParameter(1, claveAdmin);
        q.setParameter(2, correoAdmin);
        q.executeUpdate();

        UUID usuarioId = usuario.getUsuarioId();
        UUID rolId = rolAdmin.getRolId();

        boolean yaAsignado = usuarioRolRepositorioJpa.findByUsuarioIdAndRolId(usuarioId, rolId).isPresent();
        if (!yaAsignado) {
            UsuarioRolEntidadJpa relacion = new UsuarioRolEntidadJpa();
            relacion.setUsuarioRolId(UUID.randomUUID());
            relacion.setUsuarioId(usuarioId);
            relacion.setRolId(rolId);

            OffsetDateTime ahora = OffsetDateTime.now();
            relacion.setCreadoEn(ahora);
            relacion.setActualizadoEn(ahora);

            usuarioRolRepositorioJpa.save(relacion);
        }
    }
}
