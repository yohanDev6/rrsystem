package br.com.yohandevmeia.rrsystem.services;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.yohandevmeia.rrsystem.models.RefreshToken;
import br.com.yohandevmeia.rrsystem.repositories.ClientRepository;
import br.com.yohandevmeia.rrsystem.repositories.RefreshTokenRepository;

@Service
public class RefreshTokenService extends GlobalValidationService {

	@Value("${app.jwt.refresh-expiration}")
    private long refreshTokenDurationMs;

    private final RefreshTokenRepository refreshTokenRepository;
    private final ClientRepository clientRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, ClientRepository clientRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.clientRepository = clientRepository;
    }

    public RefreshToken createRefreshToken(Long clientId) {
    	verifyId(clientId);
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setClient(clientRepository.findById(clientId).orElseThrow());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setRevoked(false);
        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verifyToken(String token) {
        return refreshTokenRepository.findByToken(token)
            .filter(t -> !t.isRevoked() && t.getExpiryDate().isAfter(Instant.now()))
            .orElseThrow(() -> new RuntimeException("Invalid or expired refresh token"));
    }

    public void revokeToken(String token) {
        refreshTokenRepository.findByToken(token).ifPresent(refreshToken -> {
            refreshToken.setRevoked(true);
            refreshTokenRepository.save(refreshToken);
        });
    }
}
