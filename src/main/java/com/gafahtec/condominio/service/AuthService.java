package com.gafahtec.condominio.service;

import com.gafahtec.condominio.dto.AuthenticationResponse;
import com.gafahtec.condominio.dto.LoginRequest;
import com.gafahtec.condominio.dto.RefreshTokenRequest;
import com.gafahtec.condominio.dto.RegisterRequest;
import com.gafahtec.condominio.exception.CondominioException;
import com.gafahtec.condominio.model.NotificationMail;
import com.gafahtec.condominio.model.Usuario;
import com.gafahtec.condominio.model.VerificationToken;
import com.gafahtec.condominio.repository.RefreshTokenRepository;
import com.gafahtec.condominio.repository.UsuarioRepository;
import com.gafahtec.condominio.repository.VerificationTokenRepository;
import com.gafahtec.condominio.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {
    private  final PasswordEncoder passwordEncoder;
    private  final UsuarioRepository usuarioRepository;
    private  VerificationTokenRepository verificationTokenRepository;
    private  MailService mailService;
    private  AuthenticationManager authenticationManager;
    private  JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;


    @Transactional
    public void signup(RegisterRequest registerRequest){
        Usuario usuario = new Usuario();
        usuario.setLogin(registerRequest.getUsuario());
        usuario.setEmail(registerRequest.getEmail());
        usuario.setPassword(passwordEncoder.encode(registerRequest.getEmail()));
        usuario.setFechaCreacion(Instant.now());
        usuario.setHabilitado(false);

        usuarioRepository.save(usuario);



        String token = generateVerificationToken(usuario);
        mailService.sendMail(new NotificationMail("Please Activate your Account",
                usuario.getEmail(), "Thank you for signing up to Spring Reddit, " +
                "please click on the below url to activate your account : " +
                "http://localhost:8080/api/auth/accountVerification/" + token));
    }

    @Transactional(readOnly = true)
    public Usuario getCurrentUser(){
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return usuarioRepository.findByLogin(principal.getUsername()).orElseThrow(() -> new CondominioException("Usuario no encontrado "+ principal.getUsername()));

    }

    private void fetchUserAndEnable(VerificationToken verificationToken){
        String username = verificationToken.getUsuario().getLogin();
        Usuario usuario = usuarioRepository.findByLogin(username).orElseThrow(() -> new CondominioException("Usuario no encontrado"));
        usuario.setHabilitado(true);
        usuarioRepository.save(usuario);
    }

    private String generateVerificationToken(Usuario usuario){
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUsuario(usuario);

        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public void verifyAccount(String token){
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        fetchUserAndEnable(verificationToken.orElseThrow(() -> new CondominioException("token invalido")));
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(loginRequest.getUsername())
                .build();
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUsuario());
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(refreshTokenRequest.getUsuario())
                .build();
    }

    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }
}
