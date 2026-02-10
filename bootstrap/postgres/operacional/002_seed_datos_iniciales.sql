BEGIN;

-- =========================================================
-- SEMILLAS INICIALES SMARTFIN
-- =========================================================
-- Este script carga los datos mínimos necesarios para que
-- el sistema pueda operar desde el primer arranque.
-- =========================================================


-- ---------------------------------------------------------
-- ROLES DEL SISTEMA
-- ---------------------------------------------------------
INSERT INTO smartfin.rol (nombre, descripcion)
VALUES
    ('ADMINISTRADOR', 'Acceso total al sistema'),
    ('FINANCIERO', 'Usuario experto en análisis financiero'),
    ('REPORTERIA', 'Acceso solo lectura a reportes')
    ON CONFLICT (nombre) DO NOTHING;


-- ---------------------------------------------------------
-- EMPRESA DEMO
-- ---------------------------------------------------------
INSERT INTO smartfin.empresa (
    identificacion,
    nombre,
    sector,
    moneda_codigo,
    activo
)
VALUES (
           '9999999999',
           'Empresa Demo SmartFin',
           'DEMO',
           'USD',
           TRUE
       )
    ON CONFLICT (identificacion) DO NOTHING;


-- ---------------------------------------------------------
-- USUARIO ADMINISTRADOR
-- Clave: Admin123$
-- Hash BCrypt generado previamente (uso académico)
-- ---------------------------------------------------------
INSERT INTO smartfin.usuario (
    correo,
    nombre_completo,
    clave_hash,
    activo
)
VALUES (
           'admin@smartfin.com',
           'Administrador SmartFin',
           '$2a$10$Yw8g9b2Qm3h7b1R7Qp4Y9e2rKJmXc2o7I0xI5o4dFf0tV7kq9kM9K',
           TRUE
       )
    ON CONFLICT (correo) DO NOTHING;


-- ---------------------------------------------------------
-- ASIGNACIÓN DE ROL ADMINISTRADOR AL USUARIO ADMIN
-- ---------------------------------------------------------
INSERT INTO smartfin.usuario_rol (usuario_id, rol_id)
SELECT
    u.usuario_id,
    r.rol_id
FROM smartfin.usuario u
         JOIN smartfin.rol r
              ON r.nombre = 'ADMINISTRADOR'
WHERE u.correo = 'admin@smartfin.com'
    ON CONFLICT (usuario_id, rol_id) DO NOTHING;


COMMIT;
