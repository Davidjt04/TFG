package com.david.tfg.entities;

public record LoginResponse(
        String token,
        String username,
        String rol,
        Integer idUsuario
) {}
